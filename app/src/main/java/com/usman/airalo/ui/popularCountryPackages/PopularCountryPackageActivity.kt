package com.usman.airalo.ui.popularCountryPackages

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.usman.airalo.databinding.ActivityPopularCountryPackageBinding
import com.usman.airalo.ui.base.BaseActivity
import com.usman.airalo.ui.popularCountry.PopularCountryActivity
import com.usman.airalo.ui.popularCountry.PopularCountryAdapter
import com.usman.common.entities.country.CountryList
import com.usman.common.entities.countryPackage.PackageInfo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularCountryPackageActivity : BaseActivity<ActivityPopularCountryPackageBinding>() {

    val TAG = PopularCountryPackageActivity::class.java.simpleName

    private val viewModel: PopularCountryPackagesViewModel by viewModels()

    lateinit var countryPackageAdapter: CountryPackageAdapter


    override fun inflateViewBinding(inflater: LayoutInflater): ActivityPopularCountryPackageBinding =
        ActivityPopularCountryPackageBinding.inflate(inflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpObservers()
        viewModel.getCountryPackage("europe");
    }

    private fun setUpObservers() = with(viewModel) {
        getUiState().observe(this@PopularCountryPackageActivity) {
            when (it) {
                is PopularCountryPackagesViewModel.UiState.GetPopularCountryPackageUiState -> {

                    setupRecyclerView(it.countryPackage.packages)

                }

                is PopularCountryPackagesViewModel.UiState.Loading -> {
                    //showProgress()
                }

                is PopularCountryPackagesViewModel.UiState.NotLoading -> {
                    // hideProgress()
                }

                is Error ->
                    Toast.makeText(
                        applicationContext, it.message, Toast.LENGTH_LONG
                    ).show()

                else -> {}
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