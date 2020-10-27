package com.lumi.surfeducationproject.data.services.network

import com.lumi.surfeducationproject.data.dto.network.NetworkUserResponse
import com.lumi.surfeducationproject.data.dto.network.NetworkLoginUserRequest
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

class AuthServiceImpl: AuthService {

    private val api = NetworkServiceImpl.getAuthApi()

    override fun loginIn(userRequestDataNetwork: NetworkLoginUserRequest): Single<NetworkUserResponse>? {
        return api.loginIn(userRequestDataNetwork)
    }

    override fun logout(): Maybe<Throwable> {
        return api.logout()
    }

}