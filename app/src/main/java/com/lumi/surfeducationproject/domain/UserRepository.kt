package com.lumi.surfeducationproject.domain

import com.lumi.surfeducationproject.data.model.User
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single


interface UserRepository {

    fun getUser(): Single<User?>


    fun addUser(user: User): Completable

}