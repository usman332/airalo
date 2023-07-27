package com.usman.domain.usecase

import com.usman.common.entities.country.CountryList
import com.usman.domain.repository.CountryRepository
import com.usman.domain.util.Result

open class GetPopularCountry(private val country: CountryRepository) {

    suspend fun execute(): Result<CountryList> = country.getPopularCountry()
}