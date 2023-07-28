package com.usman.airalo.di.core.module

import com.usman.data.api.ApiService
import com.usman.data.source.country.CountryDataSource
import com.usman.data.source.country.CountryRemoteDataSource
import com.usman.data.source.country.CountryRepositoryImpl
import com.usman.data.util.DispatchersProvider
import com.usman.domain.repository.CountryRepository
import com.usman.domain.usecase.GetCountryPackage
import com.usman.domain.usecase.GetPopularCountry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * Dagger module responsible for providing data-related dependencies in the application.
 *
 * This module is installed in the [SingletonComponent] which ensures that the provided
 * dependencies are available throughout the entire application's lifecycle.
 */
@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    //region Country

    /**
     * Provides an instance of [CountryRepository] which serves as the repository for country-related data.
     *
     * @param walletRemote The remote data source for country information, implementing [CountryDataSource.Remote].
     * @return An instance of [CountryRepository] to manage country-related data.
     */
    @Provides
    @Singleton
    fun provideCountryRepository(
        walletRemote: CountryDataSource.Remote,
    ): CountryRepository {
        return CountryRepositoryImpl(walletRemote)
    }

    /**
     * Provides an instance of [CountryDataSource.Remote] to handle country data retrieval from remote sources.
     *
     * @param apiService The service responsible for making API calls, implementing [ApiService].
     * @param dispatchers The provider for coroutines' dispatchers, implementing [DispatchersProvider].
     * @return An instance of [CountryDataSource.Remote] used for retrieving country data from remote sources.
     */
    @Provides
    @Singleton
    fun provideWalletRemoteDataSource(
        apiService: ApiService,
        dispatchers: DispatchersProvider
    ): CountryDataSource.Remote {
        return CountryRemoteDataSource(apiService, dispatchers)
    }

    /**
     * Provides an instance of [GetPopularCountry] use case which uses [CountryRepository] for getting popular countries.
     *
     * @param countryRepository The repository providing access to country-related data, implementing [CountryRepository].
     * @return An instance of [GetPopularCountry] use case to get popular countries.
     */
    @Provides
    fun provideGetPopularCountryUseCase(countryRepository: CountryRepository): GetPopularCountry {
        return GetPopularCountry(countryRepository)
    }

    /**
     * Provides an instance of [GetCountryPackage] use case which uses [CountryRepository] for getting country packages.
     *
     * @param countryRepository The repository providing access to country-related data, implementing [CountryRepository].
     * @return An instance of [GetCountryPackage] use case to get country packages.
     */
    @Provides
    fun provideGetCountryPackageUseCase(countryRepository: CountryRepository): GetCountryPackage {
        return GetCountryPackage(countryRepository)
    }

    //endregion
}
