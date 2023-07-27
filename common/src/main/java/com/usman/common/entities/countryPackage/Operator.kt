package com.usman.common.entities.countryPackage


import com.google.gson.annotations.SerializedName

data class Operator(
    @SerializedName("activation_policy")
    var activationPolicy: String,
    @SerializedName("apn")
    var apn: Any,
    @SerializedName("apn_single")
    var apnSingle: String,
    @SerializedName("apn_type")
    var apnType: String,
    @SerializedName("apn_type_android")
    var apnTypeAndroid: String,
    @SerializedName("apn_type_ios")
    var apnTypeIos: String,
    @SerializedName("countries")
    var countries: List<Country>,
    @SerializedName("data_roaming")
    var dataRoaming: Boolean,
    @SerializedName("gradient_end")
    var gradientEnd: String,
    @SerializedName("gradient_start")
    var gradientStart: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("image")
    var image: Image,
    @SerializedName("info")
    var info: List<String>,
    @SerializedName("is_kyc_verify")
    var isKycVerify: Int,
    @SerializedName("is_multi_package")
    var isMultiPackage: Boolean,
    @SerializedName("is_prepaid")
    var isPrepaid: Boolean,
    @SerializedName("kyc_type")
    var kycType: Any,
    @SerializedName("networks")
    var networks: List<Network>,
    @SerializedName("operator_legal_name")
    var operatorLegalName: Any,
    @SerializedName("other_info")
    var otherInfo: Any,
    @SerializedName("plan_type")
    var planType: String,
    @SerializedName("privacy_policy_url")
    var privacyPolicyUrl: Any,
    @SerializedName("rechargeability")
    var rechargeability: Boolean,
    @SerializedName("style")
    var style: String,
    @SerializedName("title")
    var title: String,
    @SerializedName("type")
    var type: String
)