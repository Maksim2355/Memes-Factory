package com.lumi.surfeducationproject.services.network

import com.lumi.surfeducationproject.data.request_body.ReqBodyUser
import com.lumi.surfeducationproject.data.response.UserResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface MemesApi {

    @POST("auth/login")
    fun authorizationUser(@Body userAuth: ReqBodyUser): Single<UserResponse>


}