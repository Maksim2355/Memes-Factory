package com.lumi.surfeducationproject.data.api

import com.lumi.surfeducationproject.data.dto.AuthInfoDto
import com.lumi.surfeducationproject.data.dto.MemDto
import com.lumi.surfeducationproject.data.dto.LoginUserRequestDto
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MemesApi {

    @POST("auth/login")
    fun authorizationUser(@Body userRequestDtoAuth: LoginUserRequestDto): Single<AuthInfoDto>

    @GET("memes")
    fun getMemes(): Single<List<MemDto>>

    @POST("auth/logout")
    fun logout(): Maybe<Throwable>

}