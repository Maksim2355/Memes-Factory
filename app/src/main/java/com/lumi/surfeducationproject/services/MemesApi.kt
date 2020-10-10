package com.lumi.surfeducationproject.services

import com.lumi.surfeducationproject.model.UserAuth
import com.lumi.surfeducationproject.model.response.UserResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface MemesApi {

    @POST("auth/login")
    fun authorizationUser(@Body userAuth: UserAuth): Single<UserResponse>

}