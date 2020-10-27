package com.lumi.surfeducationproject.data.dto

import com.google.gson.annotations.SerializedName


data class AuthInfoDto(
    @SerializedName("token")
    val token: String,
    @SerializedName("userDescription")
    val description: String
)
