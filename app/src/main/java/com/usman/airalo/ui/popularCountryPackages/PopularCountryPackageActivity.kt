package com.usman.airalo.ui.popularCountryPackages

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.usman.airalo.Constant
import com.usman.airalo.R
import com.usman.airalo.databinding.ActivityPopularCountryPackageBinding
import com.usman.airalo.ui.base.BaseActivity
import com.usman.common.entities.countryPackage.PackageInfo
import dagger.hilt.android.AndroidEntryPoint

/**
 * Activity class that displays popular country packages and handles user interactions.
 *
 * This activity is annotated with [@AndroidEntryPoint](https://developer.android.com/training/dependency-injection/hilt-android#activities)
 * to enable Hilt dependency injection for the activity.
 *
 * The activity extends [BaseActivity] and implements the necessary [inflateViewBinding] function
 * to provide the appropriate [ViewBinding] instance for the activity.
 */
@AndroidEntryPoint
class PopularCountryPackageActivity : BaseActivity<ActivityPopularCountryPackageBinding>() {

    private val viewModel: PopularCountryPackagesViewModel by viewModels()

    private lateinit var countryPackageAdapter: CountryPackageAdapter

    /**
     * Inflates the [ActivityPopularCountryPackageBinding] for this activity.
     *
     * @param inflater The [LayoutInflater] to inflate the view using the ViewBinding.
     * @return An instance of [ActivityPopularCountryPackageBinding] for this activity.
     */
    override fun inflateViewBinding(inflater: LayoutInflater): ActivityPopularCountryPackageBinding =
        ActivityPopularCountryPackageBinding.inflate(inflater)

    /**
     * Called when the activity is being created. This is where you can initialize and set up the activity.
     *
     * @param savedInstanceState The saved state of the activity if it was previously destroyed.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setToolbarText()
        setBackButtonListener()
        setUpObservers()
        callCountryPackageAPI(intent.getStringExtra(Constant.EXTRA_INTENT_COUNTRY_ID) ?: "singapore")
    }

    /**
     * Sets the toolbar text based on the country ID received from the intent.
     * If no country ID is provided, the activity is finished.
     */
    private fun setToolbarText() {
        if (intent != null && intent.hasExtra(Constant.EXTRA_INTENT_COUNTRY_ID)) {
            binding.txtPopularCountry.text =
                intent.getStringExtra(Constant.EXTRA_INTENT_COUNTRY_ID)?.capitalize()
        } else {
            finish()
        }
    }

    /**
     * Calls the country package API to fetch the packages for the given [countryId].
     *
     * @param countryId The ID of the country for which packages will be fetched.
     */
    private fun callCountryPackageAPI(countryId: String) {
        viewModel.getCountryPackage("europe")
    }

    /**
     * Sets a click listener for the back button to handle the navigation back to the previous screen.
     */
    private fun setBackButtonListener() {
        binding.imgBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    /**
     * Sets up observers for the [PopularCountryPackagesViewModel] to handle UI state changes.
     */
    private fun setUpObservers() = with(viewModel) {
        getUiState().observe(this@PopularCountryPackageActivity) {
            when (it) {
                is PopularCountryPackagesViewModel.UiState.GetPopularCountryPackageUiState -> {
                    hideView(binding.loaderPackage)
                    setupRecyclerView(it.countryPackage.packages)
                }

                is PopularCountryPackagesViewModel.UiState.Loading -> {
                    showView(binding.loaderPackage)
                }

                is PopularCountryPackagesViewModel.UiState.NotLoading -> {
                    hideView(binding.loaderPackage)
                }

                is Error ->
                    errorMessageSnackBar(it.message ?: getString(R.string.something_went_wrong))

                // Handle other UI states if needed

                else -> {
                    errorMessageSnackBar(getString(R.string.something_went_wrong))
                }
            }
        }
    }

    /**
     * Sets up the RecyclerView to display the list of country packages.
     *
     * @param packageInfo The list of [PackageInfo] representing the country packages to display.
     */
    private fun setupRecyclerView(packageInfo: ArrayList<PackageInfo>) {
        countryPackageAdapter =
            CountryPackageAdapter(packageInfo)
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rvPackages.layoutManager = linearLayoutManager
        binding.rvPackages.adapter = countryPackageAdapter
    }
}
