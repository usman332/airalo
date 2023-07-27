package com.usman.data.source.country

import com.usman.common.entities.country.CountryList
import com.usman.common.entities.countryPackage.CountryPackage
import com.usman.domain.util.Result
import com.usman.data.api.ApiService
import com.usman.data.util.DispatchersProvider
import kotlinx.coroutines.withContext

class CountryRemoteDataSource(
    private val apiService: ApiService,
    private val dispatchers: DispatchersProvider
) : CountryDataSource.Remote {
    override suspend fun getPopularCountry(): Result<CountryList> =
        withContext(dispatchers.getIO()) {
            return@withContext try {
                val result = apiService.getPopularCountry()
                Result.Success(result)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

    override suspend fun getCountryPackage(countryId: String): Result<CountryPackage> =
        withContext(dispatchers.getIO()) {
            return@withContext try {
                val result = apiService.getCountryPackage(countryId)
                Result.Success(result)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
}