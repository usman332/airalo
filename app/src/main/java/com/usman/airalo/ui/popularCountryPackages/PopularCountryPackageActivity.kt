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

@AndroidEntryPoint
class PopularCountryPackageActivity : BaseActivity<ActivityPopularCountryPackageBinding>() {

    val TAG = PopularCountryPackageActivity::class.java.simpleName

    private val viewModel: PopularCountryPackagesViewModel by viewModels()

    private lateinit var countryPackageAdapter: CountryPackageAdapter


    override fun inflateViewBinding(inflater: LayoutInflater): ActivityPopularCountryPackageBinding =
        ActivityPopularCountryPackageBinding.inflate(inflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setToolbarText()
        setBackButtonListener()
        setUpObservers()
        callCountryPackageAPI(intent.getStringExtra(Constant.EXTRA_INTENT_COUNTRY_ID)?:"singapore")
    }

    private fun setToolbarText() {
        if (intent != null && intent.hasExtra(Constant.EXTRA_INTENT_COUNTRY_ID)) {
            binding.txtPopularCountry.text =
                intent.getStringExtra(Constant.EXTRA_INTENT_COUNTRY_ID)?.capitalize()
        } else {
            finish()
        }
    }

    private fun callCountryPackageAPI(countryId:String) {
        viewModel.getCountryPackage("europe");
    }

    private fun setBackButtonListener() {
        binding.imgBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

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

                else -> {
                    errorMessageSnackBar(getString(R.string.something_went_wrong))

                }
            }
        }
    }

    private fun setupRecyclerView(packageInfo: ArrayList<PackageInfo>) {
        countryPackageAdapter =
            CountryPackageAdapter(packageInfo)
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rvPackages.layoutManager = linearLayoutManager
        binding.rvPackages.adapter = countryPackageAdapter

    }

}