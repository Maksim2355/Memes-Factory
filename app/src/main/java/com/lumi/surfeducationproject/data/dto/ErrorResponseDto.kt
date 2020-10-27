package com.lumi.surfeducationproject.data.dto

import com.google.gson.annotations.SerializedName

data class ErrorResponseDto(
    @SerializedName("code")
    val codeError: String,
    @SerializedName("errorMessage")
    val errorMessage: String
)