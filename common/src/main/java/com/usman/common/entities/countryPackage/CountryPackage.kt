package com.usman.common.entities.countryPackage


import com.google.gson.annotations.SerializedName

data class CountryPackage(
    @SerializedName("id")
    var id: Int,
    @SerializedName("image")
    var image: Image,
    @SerializedName("packages")
    var packages: List<Package>,
    @SerializedName("seo")
    var seo: Any,
    @SerializedName("slug")
    var slug: String,
    @SerializedName("title")
    var title: String
)