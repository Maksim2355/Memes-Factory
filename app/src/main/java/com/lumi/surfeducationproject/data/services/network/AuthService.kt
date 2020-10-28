package com.lumi.surfeducationproject.data.services.network

import com.lumi.surfeducationproject.data.dto.network.NetworkUserResponse
import com.lumi.surfeducationproject.data.dto.network.NetworkLoginUserRequest
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface AuthService {

    fun loginIn(userRequestDataNetwork: NetworkLoginUserRequest?): Single<NetworkUserResponse>

    fun logout(): Completable

}