package com.lumi.surfeducationproject.domain.services

import com.lumi.surfeducationproject.data.dto.AuthInfoDto
import com.lumi.surfeducationproject.data.dto.LoginUserRequestDto
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

interface AuthService {

    fun loginIn(userRequestDtoData: LoginUserRequestDto): Single<AuthInfoDto>?

    fun logout(): Maybe<>

}