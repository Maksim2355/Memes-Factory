package com.lumi.surfeducationproject.data.repository.user_repository

import com.lumi.surfeducationproject.data.dto.LoginUserRequestDto
import com.lumi.surfeducationproject.domain.repository.UserRepository
import com.lumi.surfeducationproject.domain.services.AuthService
import com.lumi.surfeducationproject.data.services.network.AuthServiceImpl
import com.lumi.surfeducationproject.domain.services.SharedPreferenceService
import com.lumi.surfeducationproject.data.services.local.SharedPreferenceServiceImpl
import io.reactivex.rxjava3.core.Completable

class UserRepositoryImpl: UserRepository {

    private val sharedPreferenceService: SharedPreferenceService = SharedPreferenceServiceImpl()
    private val authService: AuthService = AuthServiceImpl()

    override fun authorizationUser(userRequestRequestDto: LoginUserRequestDto): Completable {
        TODO()
    }

    override fun deleteUser(): Completable {
        TODO("Not yet implemented")
    }


}