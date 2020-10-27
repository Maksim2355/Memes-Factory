package com.lumi.surfeducationproject.data.dto.network

import com.google.gson.annotations.SerializedName


data class NetworkUserResponse(
    @SerializedName("accessToken")
    val accessToken: String,

    @SerializedName("userInfo")
    val userInfo: NetworkUser
)
