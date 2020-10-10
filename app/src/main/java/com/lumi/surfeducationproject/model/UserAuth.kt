package com.lumi.surfeducationproject.model

import com.google.gson.annotations.SerializedName

data class UserAuth(
    val login: String,
    val password: String)
