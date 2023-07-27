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


@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    //region Country
    @Provides
    @Singleton
    fun provideCountryRepository(
        walletRemote: CountryDataSource.Remote,
        ): CountryRepository {
        return CountryRepositoryImpl(walletRemote)
    }


    @Provides
    @Singleton
    fun provideWalletRemoteDataSource(
        apiService: ApiService,
        dispatchers: DispatchersProvider
    ): CountryDataSource.Remote {
        return CountryRemoteDataSource(apiService, dispatchers)
    }

    @Provides
    fun provideGetPopularCountryUseCase(countryRepository: CountryRepository): GetPopularCountry {
        return GetPopularCountry(countryRepository)
    }

    @Provides
    fun provideGetCountryPackageUseCase(countryRepository: CountryRepository): GetCountryPackage {
        return GetCountryPackage(countryRepository)
    }


    //endregion
}