package com.example.data.dto.network

import com.google.gson.annotations.SerializedName


data class UserResponse(
    @SerializedName("accessToken")
    val accessToken: String,

    @SerializedName("userInfo")
    val userInfo: NetworkUser
)
