package com.usman.common.entities.region


import com.google.gson.annotations.SerializedName

data class RegionItem(
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