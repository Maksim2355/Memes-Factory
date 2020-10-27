package com.lumi.surfeducationproject.domain.services

import com.lumi.surfeducationproject.data.api.MemesApi

interface NetworkService {
    fun getApi(): MemesApi
}