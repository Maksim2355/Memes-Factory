package com.lumi.surfeducationproject.data.services.network

import com.lumi.surfeducationproject.data.dto.AuthInfoDto
import com.lumi.surfeducationproject.data.dto.LoginUserRequestDto
import com.lumi.surfeducationproject.domain.services.AuthService
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

class AuthServiceImpl: AuthService {

    private val api = NetworkServiceImpl.getApi()

    override fun loginIn(userRequestDtoData: LoginUserRequestDto): Single<AuthInfoDto>? {
        return api.authorizationUser(userRequestDtoData).map { it.authInfoInfoDto }
    }

    override fun logout(): Maybe<Throwable> {
        return api.logout()
    }

}