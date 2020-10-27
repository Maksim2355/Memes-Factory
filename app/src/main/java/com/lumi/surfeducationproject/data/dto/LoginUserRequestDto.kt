package com.lumi.surfeducationproject.data.dto

import com.google.gson.annotations.SerializedName

data class LoginUserRequestDto(
    val login: String,
    val password: String)
