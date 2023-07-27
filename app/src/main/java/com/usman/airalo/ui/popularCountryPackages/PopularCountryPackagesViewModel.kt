package com.usman.airalo.ui.popularCountryPackages

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.usman.airalo.ui.base.BaseViewModel
import com.usman.common.entities.countryPackage.CountryPackage
import com.usman.data.util.DispatchersProvider
import com.usman.domain.usecase.GetCountryPackage
import com.usman.domain.util.onError
import com.usman.domain.util.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import javax.inject.Inject

@HiltViewModel
class PopularCountryPackagesViewModel @Inject constructor(

    private val getCountryPackage: GetCountryPackage,
    dispatchers: DispatchersProvider
) : BaseViewModel(dispatchers) {

    sealed class UiState {
        data class GetPopularCountryPackageUiState(val countryList: CountryPackage) :
            UiState()

        data class Error(val message: String?) : UiState()
        object Loading : UiState()
        object NotLoading : UiState()
    }

    private val uiState: MutableLiveData<UiState> = MutableLiveData()
    private var job: Job? = null

    fun getUiState(): LiveData<UiState> = uiState

     fun getCountryPackage(countryId: String) {
        job?.cancel()

        job = launchOnMainImmediate {
            uiState.postValue(UiState.Loading)
            getCountryPackage.getCountryPackage(countryId).onSuccess {
                uiState.postValue(UiState.NotLoading)
                uiState.postValue(UiState.GetPopularCountryPackageUiState(it))

            }.onError { error ->
                uiState.postValue(UiState.NotLoading)
                uiState.postValue(
                    UiState.Error(error.message)
                )
            }
        }

    }

}