package com.lumi.surfeducationproject.data.repository

import com.lumi.surfeducationproject.data.dto.mappers.UserDtoDataMapper
import com.lumi.surfeducationproject.data.dto.network.NetworkLoginUserRequest
import com.lumi.surfeducationproject.data.services.local.SharedPreferenceService
import com.lumi.surfeducationproject.data.services.local.SharedPreferenceServiceImpl
import com.lumi.surfeducationproject.data.services.network.AuthService
import com.lumi.surfeducationproject.data.services.network.AuthServiceImpl
import com.lumi.surfeducationproject.domain.model.User
import com.lumi.surfeducationproject.domain.repository.UserRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class UserRepositoryImpl : UserRepository {

    private val sharedPreferenceService: SharedPreferenceService = SharedPreferenceServiceImpl()
    private val authService: AuthService = AuthServiceImpl()
    private val mapper: UserDtoDataMapper = UserDtoDataMapper()

    override fun getUser(userRequestRequestNetwork: NetworkLoginUserRequest?): Single<User> {
        return authService.loginIn(userRequestRequestNetwork)
            .map { mapper.transform(it) }
            .doOnSuccess { sharedPreferenceService.saveUser(it) }
    }

    override fun getUser(): Single<User?> {
        return Single.fromCallable { sharedPreferenceService.readUser() }
    }

    override fun deleteUser(): Completable {
        return authService.logout()
            .doFinally { sharedPreferenceService.deleteUser() }
    }


}