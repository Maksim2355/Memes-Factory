package com.lumi.surfeducationproject.data.api

import com.lumi.surfeducationproject.data.dto.network.ErrorResponse
import com.lumi.surfeducationproject.data.dto.network.LoginUserRequest
import com.lumi.surfeducationproject.data.dto.network.UserResponse
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("auth/login")
    fun loginIn(@Body user: LoginUserRequest?): Single<UserResponse>

    @POST("auth/logout")
    fun logout(): Maybe<ErrorResponse>
}
