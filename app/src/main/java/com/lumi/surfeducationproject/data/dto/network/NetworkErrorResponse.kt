package com.lumi.surfeducationproject.data.dto.network

import com.google.gson.annotations.SerializedName

data class NetworkErrorResponse(
    @SerializedName("code")
    val code: String,

    @SerializedName("errorMessage")
    val errorMessage: String
)