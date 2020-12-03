package com.example.data.repository

import com.example.data.dto.mappers.UserDtoDataMapper
import com.example.data.services.local.SharedPreferenceService
import com.example.data.services.network.AuthService
import com.example.domain.model.AuthorizationData
import com.example.domain.model.User
import com.example.domain.repository.UserRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val sharedPreferenceService: SharedPreferenceService,
    private val authService: AuthService,
    private val mapper: UserDtoDataMapper
) : UserRepository {


    override fun getUser(authorizationData: AuthorizationData): Single<User> {
        return authService.loginIn(mapper.transformAuthData(authorizationData))
            .map { mapper.transform(it) }
            .doOnSuccess { sharedPreferenceService.saveUser(it) }
    }

    override fun getUser(): Single<User?> {
        return Single.fromCallable { sharedPreferenceService.readUser() }
    }

    override fun deleteUser(): Completable {
        return authService.logout()
            .doOnComplete { sharedPreferenceService.deleteUser() }
    }


}