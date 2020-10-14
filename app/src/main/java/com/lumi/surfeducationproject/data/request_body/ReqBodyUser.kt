package com.lumi.surfeducationproject.data.request_body

import com.google.gson.annotations.SerializedName

data class ReqBodyUser(
    val login: String,
    val password: String)
