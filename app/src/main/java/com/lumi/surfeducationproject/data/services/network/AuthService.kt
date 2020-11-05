package com.lumi.surfeducationproject.data.services.network

import com.lumi.surfeducationproject.data.dto.network.UserResponse
import com.lumi.surfeducationproject.data.dto.network.LoginUserRequest
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface AuthService {

    fun loginIn(userRequestData: LoginUserRequest?): Single<UserResponse>

    fun logout(): Completable

}