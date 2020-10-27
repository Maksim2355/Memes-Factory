package com.lumi.surfeducationproject.data.repository.users

import com.lumi.surfeducationproject.data.dto.mappers.UserDtoDataMapper
import com.lumi.surfeducationproject.data.dto.network.NetworkLoginUserRequest
import com.lumi.surfeducationproject.domain.repository.UserRepository
import com.lumi.surfeducationproject.data.services.network.AuthService
import com.lumi.surfeducationproject.data.services.network.AuthServiceImpl
import com.lumi.surfeducationproject.data.services.local.SharedPreferenceService
import com.lumi.surfeducationproject.data.services.local.SharedPreferenceServiceImpl
import com.lumi.surfeducationproject.domain.model.User
import com.lumi.surfeducationproject.exceptions.NETWORK_EXCEPTIONS
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class UserRepositoryImpl : UserRepository {

    private val sharedPreferenceService: SharedPreferenceService = SharedPreferenceServiceImpl()
    private val authService: AuthService = AuthServiceImpl()
    private val mapper: UserDtoDataMapper = UserDtoDataMapper()

    override fun getUser(userRequestRequestNetwork: NetworkLoginUserRequest?): Single<User> {
        return authService.loginIn(userRequestRequestNetwork).map { mapper.transform(it) }
            .observeOn(Schedulers.io())
            .doOnSuccess { sharedPreferenceService.saveUser(it) }
            .onErrorReturn {
                if (NETWORK_EXCEPTIONS.contains(it.javaClass)) {
                    sharedPreferenceService.readUser()
                } else {
                    null
                }
            }


    }

    override fun deleteUser(): Completable {
        return Completable.fromAction { sharedPreferenceService.deleteUser() }
    }


}