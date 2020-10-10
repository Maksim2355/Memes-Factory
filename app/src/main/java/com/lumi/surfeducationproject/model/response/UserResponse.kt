package com.lumi.surfeducationproject.model.response

import com.google.gson.annotations.SerializedName
import com.lumi.surfeducationproject.model.User

data class UserResponse(
    val accessToken: String,
    val userInfo: User
)