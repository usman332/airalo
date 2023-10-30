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

/**
 * ViewModel class for the PopularCountryActivity.
 *
 * @param getPopularCountry The use case responsible for retrieving the list of popular countries.
 * @param dispatchers The [DispatchersProvider] instance providing coroutines dispatchers for the ViewModel.
 */
@HiltViewModel
class PopularCountryViewModel @Inject constructor(
    private val getPopularCountry: GetPopularCountry,
    dispatchers: DispatchersProvider
) : BaseViewModel(dispatchers) {

    val post :String by lazy {

        getPostData()
    }

    fun getPostData():String{
        return  "Dummy Post data"
    }
    /**
     * Sealed class representing different UI states of the [PopularCountryViewModel].
     */
    sealed class UiState {
        /**
         * UI state representing a successful retrieval of the list of popular countries.
         *
         * @param countryList The list of popular countries.
         */
        data class GetPopularCountryUiState(val countryList: CountryList) : UiState()

        data class GetPopularUiState(val countryList: Any) : UiState()

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
    private var countryJob: Job? = null

    /**
     * Returns the [LiveData] representing the current UI state of the ViewModel.
     *
     * @return The [LiveData] instance containing the [UiState].
     */
    fun getUiState(): LiveData<UiState> = uiState

    /**
     * Initializes the ViewModel by calling [onInitialState].
     */
    init {
        onInitialState()
    }

    /**
     * Calls [getPopularCountry] to fetch the initial data for the ViewModel.
     */
    private fun onInitialState() = launchOnMainImmediate {
        getPopularCountry()
    }

    /**
     * Retrieves the list of popular countries using the [getPopularCountry] use case.
     */
    private fun getPopularCountry() {
        countryJob?.cancel()

        countryJob = launchOnIO {
            uiState.postValue(UiState.Loading)
            getPopularCountry.execute().onSuccess { countryList ->
                uiState.postValue(UiState.NotLoading)
                uiState.postValue(UiState.GetPopularCountryUiState(countryList))
            }.onError { error ->
                uiState.postValue(UiState.NotLoading)
                uiState.postValue(UiState.Error(error.message))
            }
        }
    }
}
