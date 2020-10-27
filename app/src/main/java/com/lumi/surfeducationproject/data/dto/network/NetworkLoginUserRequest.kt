package com.lumi.surfeducationproject.data.dto.network

import com.google.gson.annotations.SerializedName

data class NetworkLoginUserRequest(
    val login: String,
    val password: String)
