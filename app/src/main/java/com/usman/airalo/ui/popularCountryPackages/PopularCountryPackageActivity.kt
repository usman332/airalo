package com.usman.airalo.ui.popularCountryPackages

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import com.usman.airalo.databinding.ActivityPopularCountryPackageBinding
import com.usman.airalo.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularCountryPackageActivity : BaseActivity<ActivityPopularCountryPackageBinding>() {

    private val viewModel: PopularCountryPackagesViewModel by viewModels()

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
                    Log.d("Model", it.countryList.toString())
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

}