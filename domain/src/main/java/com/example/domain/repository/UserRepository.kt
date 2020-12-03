package com.example.domain.repository


import com.example.domain.model.AuthorizationData
import com.example.domain.model.User
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single


interface UserRepository {

    fun getUser(authorizationData: AuthorizationData): Single<User>

    fun getUser(): Single<User?>

    fun deleteUser(): Completable

}