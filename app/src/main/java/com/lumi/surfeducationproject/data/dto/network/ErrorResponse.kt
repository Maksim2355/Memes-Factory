package com.lumi.surfeducationproject.data.dto.network

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("code")
    val code: String,

    @SerializedName("errorMessage")
    val errorMessage: String
)