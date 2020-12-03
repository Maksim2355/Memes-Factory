package com.example.data.api

import com.example.data.dto.network.ErrorResponse
import com.example.data.dto.network.LoginUserRequest
import com.example.data.dto.network.UserResponse
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
