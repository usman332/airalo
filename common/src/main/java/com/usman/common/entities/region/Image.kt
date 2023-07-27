package com.usman.common.entities.region


import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("height")
    var height: Int,
    @SerializedName("url")
    var url: String,
    @SerializedName("width")
    var width: Int
)