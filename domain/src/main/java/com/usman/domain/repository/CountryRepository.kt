package com.usman.domain.repository
import com.usman.domain.util.Result

import com.usman.common.entities.country.CountryList
import com.usman.common.entities.countryPackage.CountryPackage

interface CountryRepository {

    suspend fun getPopularCountry(): Result<CountryList>

    suspend fun getCountryPackage(countryId: String): Result<CountryPackage>
}