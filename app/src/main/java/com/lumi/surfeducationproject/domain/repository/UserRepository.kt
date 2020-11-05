package com.lumi.surfeducationproject.domain.repository

import com.lumi.surfeducationproject.data.dto.network.LoginUserRequest
import com.lumi.surfeducationproject.domain.model.User
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single


interface UserRepository {

    fun getUser(userRequest: LoginUserRequest?): Single<User>

    fun getUser(): Single<User?>

    fun deleteUser(): Completable

}