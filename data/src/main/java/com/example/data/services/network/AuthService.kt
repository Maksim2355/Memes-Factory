package com.example.data.services.network

import com.example.data.dto.network.LoginUserRequest
import com.example.data.dto.network.UserResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface AuthService {

    fun loginIn(loginUserRequest: LoginUserRequest): Single<UserResponse>

    fun logout(): Completable

}