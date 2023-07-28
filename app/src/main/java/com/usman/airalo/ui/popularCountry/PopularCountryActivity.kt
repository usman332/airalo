package com.usman.airalo.ui.popularCountry

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.usman.airalo.Constant
import com.usman.airalo.R
import com.usman.airalo.databinding.ActivityPopularCountryBinding
import com.usman.airalo.ui.base.BaseActivity
import com.usman.airalo.ui.popularCountryPackages.PopularCountryPackageActivity
import com.usman.common.entities.country.CountryList
import dagger.hilt.android.AndroidEntryPoint

/**
 * An activity that displays a list of popular countries and handles user interactions.
 *
 * This activity extends [BaseActivity] and implements the necessary [inflateViewBinding] function
 * to provide the appropriate [ViewBinding] instance for the activity.
 */
@AndroidEntryPoint
class PopularCountryActivity : BaseActivity<ActivityPopularCountryBinding>() {

    val TAG = PopularCountryActivity::class.java.simpleName

    private val viewModel: PopularCountryViewModel by viewModels()

    lateinit var popularCountryAdapter: PopularCountryAdapter

    /**
     * Inflates the [ActivityPopularCountryBinding] for this activity.
     *
     * @param inflater The [LayoutInflater] to inflate the view using the ViewBinding.
     * @return An instance of [ActivityPopularCountryBinding] for this activity.
     */
    override fun inflateViewBinding(inflater: LayoutInflater): ActivityPopularCountryBinding =
        ActivityPopularCountryBinding.inflate(inflater)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpObservers()
    }

    /**
     * Sets up observers for the [PopularCountryViewModel] to handle UI state changes.
     */
    private fun setUpObservers() = with(viewModel) {
        getUiState().observe(this@PopularCountryActivity) {
            when (it) {
                is PopularCountryViewModel.UiState.GetPopularCountryUiState -> {
                    setupRecyclerView(it.countryList)
                }

                is PopularCountryViewModel.UiState.Loading -> {
                    showView(binding.loader)
                }

                is PopularCountryViewModel.UiState.NotLoading -> {
                    hideView(binding.loader)
                }

                is Error -> {
                    errorMessageSnackBar(it.message ?: getString(R.string.something_went_wrong))
                }


                else -> {
                    errorMessageSnackBar(getString(R.string.something_went_wrong))

                }
            }
        }
    }


    /** Sets up the RecyclerView to display the list of popular countries.
     *
     * @param countryList The list of popular countries to display.
     */
    private fun setupRecyclerView(countryList: CountryList) {
        popularCountryAdapter =
            PopularCountryAdapter(countryList, onCountryClick = ::onCountryClicked)
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rvCountry.layoutManager = linearLayoutManager
        binding.rvCountry.adapter = popularCountryAdapter

    }

    /**
     * Callback function when a country item is clicked in the RecyclerView.
     *
     * @param countryId The ID of the clicked country.
     */
    private fun onCountryClicked(countryId: String) {

        start(this@PopularCountryActivity, countryId)
    }

    companion object {
        /**
        * Starts the [PopularCountryPackageActivity] with the given [countryId].
        *
        * @param context The context from which the activity is started.
        * @param countryId The ID of the country to be passed to [PopularCountryPackageActivity].
        */
        fun start(context: Context, countryId: String) {
            val starter = Intent(context, PopularCountryPackageActivity::class.java)
            starter.putExtra(Constant.EXTRA_INTENT_COUNTRY_ID, countryId)
            context.startActivity(starter)
        }
    }

}