package com.usman.common.entities.country


import com.google.gson.annotations.SerializedName

data class CountryItem(
    @SerializedName("id")
    var id: Int,
    @SerializedName("image")
    var image: Image,
    @SerializedName("package_count")
    var packageCount: Int,
    @SerializedName("seo")
    var seo: Seo,
    @SerializedName("slug")
    var slug: String,
    @SerializedName("title")
    var title: String
)