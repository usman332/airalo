package com.usman.common.entities.country


import com.google.gson.annotations.SerializedName

data class Seo(
    @SerializedName("description")
    var description: Any,
    @SerializedName("keywords")
    var keywords: Any,
    @SerializedName("title")
    var title: Any
)