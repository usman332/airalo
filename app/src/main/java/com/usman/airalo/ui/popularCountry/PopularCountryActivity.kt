package com.usman.airalo.ui.popularCountry

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.usman.airalo.Constant
import com.usman.airalo.databinding.ActivityPopularCountryBinding
import com.usman.airalo.ui.base.BaseActivity
import com.usman.airalo.ui.popularCountryPackages.PopularCountryPackageActivity
import com.usman.common.entities.country.CountryList
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularCountryActivity : BaseActivity<ActivityPopularCountryBinding>() {

    val TAG = PopularCountryActivity::class.java.simpleName

    private val viewModel: PopularCountryViewModel by viewModels()

    lateinit var popularCountryAdapter: PopularCountryAdapter
    /*
        private val movieAdapter by lazy { PopularCountryAdapter() }
    */

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
                    setupRecyclerView(it.countryList)
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

    private fun setupRecyclerView(countryList: CountryList) {
        popularCountryAdapter =
            PopularCountryAdapter(countryList, onCountryClick = ::onCountryClicked)
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rvCountry.layoutManager = linearLayoutManager
        binding.rvCountry.adapter = popularCountryAdapter

    }

    private fun onCountryClicked(countryId: String) {

        start(this@PopularCountryActivity, countryId)
    }

    companion object {
        fun start(context: Context, countryId: String) {
            val starter = Intent(context, PopularCountryPackageActivity::class.java)
            starter.putExtra(Constant.EXTRA_INTENT_COUNTRY_ID, countryId)
            context.startActivity(starter)
        }
    }

}