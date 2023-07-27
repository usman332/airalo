package com.usman.data.source.country

import com.usman.common.entities.country.CountryList
import com.usman.common.entities.countryPackage.CountryPackage
import com.usman.domain.repository.CountryRepository
import com.usman.domain.util.Result

class CountryRepositoryImpl constructor(
    private val remote: CountryDataSource.Remote
    /*   , private val local: CountryDataSource.Local,
        private val cache: CountryDataSource.Cache*/
) : CountryRepository {

    override suspend fun getPopularCountry(): Result<CountryList> = remote.getPopularCountry()

    override suspend fun getCountryPackage(countryId: String): Result<CountryPackage> =
        remote.getCountryPackage(countryId)
}