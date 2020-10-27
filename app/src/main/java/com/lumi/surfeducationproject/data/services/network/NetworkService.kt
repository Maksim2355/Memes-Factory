package com.lumi.surfeducationproject.data.services.network

import com.lumi.surfeducationproject.data.api.AuthApi
import com.lumi.surfeducationproject.data.api.MemesApi

interface NetworkService {

    fun getMemeApi(): MemesApi

    fun getAuthApi(): AuthApi

}