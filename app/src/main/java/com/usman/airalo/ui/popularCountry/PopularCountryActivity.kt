package com.usman.airalo.ui.popularCountry

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import com.usman.airalo.databinding.ActivityPopularCountryBinding
import com.usman.airalo.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularCountryActivity : BaseActivity<ActivityPopularCountryBinding>() {

    private val viewModel: PopularCountryViewModel by viewModels()

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityPopularCountryBinding =
        ActivityPopularCountryBinding.inflate(inflater)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpObservers()
    }

    private fun setUpObservers() = with(viewModel) {
        getUiState().observe(this@PopularCountryActivity) {
            when (it) {
                is PopularCountryViewModel.UiState.GetPopularCountryUiState -> {
                    Log.d("Model", it.countryList.toString())
                }

                is PopularCountryViewModel.UiState.Loading -> {
                    //showProgress()
                }

                is PopularCountryViewModel.UiState.NotLoading -> {
                    // hideProgress()
                }

                is Error -> Toast.makeText(
                    applicationContext, it.message, Toast.LENGTH_LONG
                ).show()

                else -> {}
            }
        }
    }

}