package com.usman.common.entities.countryPackage


import com.google.gson.annotations.SerializedName

data class Network(
    @SerializedName("network")
    var network: String,
    @SerializedName("service_type")
    var serviceType: String,
    @SerializedName("status")
    var status: Boolean
)