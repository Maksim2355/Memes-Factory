package com.lumi.surfeducationproject.domain.repository

import com.lumi.surfeducationproject.data.dto.network.NetworkLoginUserRequest
import com.lumi.surfeducationproject.domain.model.User
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single


interface UserRepository {

    fun getUser(userRequestNetwork: NetworkLoginUserRequest?): Single<User>

    fun getUser(): Single<User?>

    fun deleteUser(): Completable

}