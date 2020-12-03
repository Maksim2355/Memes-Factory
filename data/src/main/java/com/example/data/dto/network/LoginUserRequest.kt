package com.example.data.dto.network

import com.google.gson.annotations.SerializedName

data class LoginUserRequest(
    val login: String,
    val password: String)
