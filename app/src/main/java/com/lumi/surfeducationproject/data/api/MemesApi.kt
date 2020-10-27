package com.lumi.surfeducationproject.data.api

import com.lumi.surfeducationproject.data.dto.network.NetworkMeme
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface MemesApi {

    @GET("memes")
    fun getMemes(): Single<List<NetworkMeme>>

}