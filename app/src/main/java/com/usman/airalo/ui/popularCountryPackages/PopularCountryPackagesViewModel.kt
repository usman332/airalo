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

/**
 * ViewModel class for handling popular country packages data and UI state.
 *
 * This ViewModel is annotated with [@HiltViewModel](https://developer.android.com/training/dependency-injection/hilt-jetpack#hiltviewmodel)
 * to enable Hilt dependency injection for the ViewModel.
 *
 * The ViewModel extends [BaseViewModel] to inherit common functionality related to coroutines dispatchers.
 *
 * @param getCountryPackage The use case responsible for retrieving country packages data.
 * @param dispatchers The [DispatchersProvider] instance providing coroutines dispatchers for the ViewModel.
 */
@HiltViewModel
class PopularCountryPackagesViewModel @Inject constructor(
    private val getCountryPackage: GetCountryPackage,
    dispatchers: DispatchersProvider
) : BaseViewModel(dispatchers) {

    /**
     * Sealed class representing different UI states of the [PopularCountryPackagesViewModel].
     */
    sealed class UiState {
        /**
         * UI state representing a successful retrieval of the country packages data.
         *
         * @param countryPackage The [CountryPackage] containing the country packages data.
         */
        data class GetPopularCountryPackageUiState(val countryPackage: CountryPackage) : UiState()

        /**
         * UI state representing an error with an optional [message].
         *
         * @param message The error message associated with the error state.
         */
        data class Error(val message: String?) : UiState()

        /**
         * UI state representing that the ViewModel is currently in a loading state.
         */
        object Loading : UiState()

        /**
         * UI state representing that the ViewModel is not in a loading state.
         */
        object NotLoading : UiState()
    }

    private val uiState: MutableLiveData<UiState> = MutableLiveData()
    private var job: Job? = null

    /**
     * Returns the [LiveData] representing the current UI state of the ViewModel.
     *
     * @return The [LiveData] instance containing the [UiState].
     */
    fun getUiState(): LiveData<UiState> = uiState

    /**
     * Fetches the country packages data for the given [countryId].
     *
     * @param countryId The ID of the country for which packages will be fetched.
     */
    fun getCountryPackage(countryId: String) {
        job?.cancel()

        job = launchOnMainImmediate {
            uiState.postValue(UiState.Loading)
            getCountryPackage.getCountryPackage(countryId).onSuccess { countryPackage ->
                uiState.postValue(UiState.NotLoading)
                uiState.postValue(UiState.GetPopularCountryPackageUiState(countryPackage))
            }.onError { error ->
                uiState.postValue(UiState.NotLoading)
                uiState.postValue(UiState.Error(error.message))
            }
        }
    }
}
