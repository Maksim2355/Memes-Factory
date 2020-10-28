package com.lumi.surfeducationproject.data.api

import com.lumi.surfeducationproject.data.dto.network.NetworkErrorResponse
import com.lumi.surfeducationproject.data.dto.network.NetworkLoginUserRequest
import com.lumi.surfeducationproject.data.dto.network.NetworkUserResponse
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("auth/login")
    fun loginIn(@Body user: NetworkLoginUserRequest?): Single<NetworkUserResponse>

    @POST("auth/logout")
    fun logout(): Maybe<NetworkErrorResponse>
}
