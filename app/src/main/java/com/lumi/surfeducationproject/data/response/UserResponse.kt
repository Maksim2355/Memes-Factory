package com.lumi.surfeducationproject.data.response

import com.lumi.surfeducationproject.data.model.User

data class UserResponse(
    val accessToken: String,
    val userInfo: User
)