package com.lumi.surfeducationproject.domain.repository

import com.lumi.surfeducationproject.data.dto.LoginUserRequestDto
import io.reactivex.rxjava3.core.Completable


interface UserRepository {

    fun authorizationUser(userRequestRequestDto: LoginUserRequestDto): Completable

    fun deleteUser(): Completable

}