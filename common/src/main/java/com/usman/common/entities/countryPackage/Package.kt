package com.usman.common.entities.countryPackage


import com.google.gson.annotations.SerializedName

data class Package(
    @SerializedName("amount")
    var amount: Int,
    @SerializedName("calling_credit")
    var callingCredit: Any,
    @SerializedName("data")
    var `data`: String,
    @SerializedName("day")
    var day: Int,
    @SerializedName("fair_usage_policy")
    var fairUsagePolicy: Any,
    @SerializedName("id")
    var id: Int,
    @SerializedName("is_stock")
    var isStock: Boolean,
    @SerializedName("is_unlimited")
    var isUnlimited: Boolean,
    @SerializedName("note")
    var note: Any,
    @SerializedName("operator")
    var `operator`: Operator,
    @SerializedName("price")
    var price: Double,
    @SerializedName("short_info")
    var shortInfo: String,
    @SerializedName("slug")
    var slug: String,
    @SerializedName("title")
    var title: String,
    @SerializedName("type")
    var type: String,
    @SerializedName("validity")
    var validity: String
)