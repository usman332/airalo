package com.usman.data.api

import com.usman.common.entities.country.CountryList
import com.usman.common.entities.countryPackage.CountryPackage
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("countries?type=popular")
    suspend fun getPopularCountry(): CountryList

 //   @GET("countries/{id}")
    @GET("regions/{id}")
    suspend fun getCountryPackage(@Path("id") countryId: String): CountryPackage
}