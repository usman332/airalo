package com.usman.airalo.ui.popularCountry

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.usman.airalo.ui.base.BaseViewModel
import com.usman.common.entities.country.CountryList
import com.usman.data.util.DispatchersProvider
import com.usman.domain.usecase.GetPopularCountry
import com.usman.domain.util.onError
import com.usman.domain.util.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import javax.inject.Inject

@HiltViewModel
class PopularCountryViewModel @Inject constructor(
    private val getPopularCountry: GetPopularCountry,
    dispatchers: DispatchersProvider
) : BaseViewModel(dispatchers) {

    sealed class UiState {
        data class GetPopularCountryUiState(val countryList: CountryList) :
            UiState()

        data class Error(val message: String?) : UiState()
        object Loading : UiState()
        object NotLoading : UiState()
    }

    private val uiState: MutableLiveData<UiState> = MutableLiveData()
    private var countryJob: Job? = null

    fun getUiState(): LiveData<UiState> = uiState


    init {
        onInitialState()
    }

    private fun onInitialState() = launchOnMainImmediate {
        getPopularCountry()
    }

    private fun getPopularCountry() {
        countryJob?.cancel()

        countryJob = launchOnIO {
            uiState.postValue(UiState.Loading)
            getPopularCountry.execute().onSuccess {
                uiState.postValue(UiState.NotLoading)
                uiState.postValue(UiState.GetPopularCountryUiState(it))

            }.onError { error ->
                uiState.postValue(UiState.NotLoading)
                uiState.postValue(
                    UiState.Error(error.message)
                )
            }
        }

    }

}