package com.lumi.surfeducationproject.services.network

import com.lumi.surfeducationproject.services.network.MemesApi

interface NetworkService {
    fun getApi(): MemesApi
}