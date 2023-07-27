package com.usman.common.entities.countryPackage


import com.google.gson.annotations.SerializedName
import com.usman.common.entities.country.Seo

data class Country(
    @SerializedName("id")
    var id: Int,
    @SerializedName("image")
    var image: Image,
    @SerializedName("slug")
    var slug: String,
    @SerializedName("title")
    var title: String
)