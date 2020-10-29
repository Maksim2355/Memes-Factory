package com.lumi.surfeducationproject.data.services.network

import com.lumi.surfeducationproject.data.api.AuthApi
import com.lumi.surfeducationproject.data.dto.network.NetworkLoginUserRequest
import com.lumi.surfeducationproject.data.dto.network.NetworkUserResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class AuthServiceImpl @Inject constructor(
    private val api: AuthApi
): AuthService {

    override fun loginIn(userRequestDataNetwork: NetworkLoginUserRequest?): Single<NetworkUserResponse> {
        return api.loginIn(userRequestDataNetwork)
    }

    //TODO прийти к единому вызову Completable or Maybe<>
    override fun logout(): Completable {
        return api.logout().ignoreElement()
    }

}