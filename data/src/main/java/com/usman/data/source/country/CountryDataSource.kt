package com.usman.data.source.country

import com.usman.common.entities.country.CountryList
import com.usman.common.entities.countryPackage.CountryPackage
import com.usman.domain.util.Result

class CountryDataSource {

    interface Remote {

        suspend fun getPopularCountry(): Result<CountryList>

        suspend fun getCountryPackage(countryId: String): Result<CountryPackage>

    }

    interface Local {

        suspend fun getPopularCountry(): Result<CountryList>

        suspend fun getCountryPackage(countryId: String): Result<CountryPackage>
    }

    interface Cache : Local
}