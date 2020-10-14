package com.lumi.surfeducationproject.services.authentication

import com.lumi.surfeducationproject.data.request_body.ReqBodyUser
import com.lumi.surfeducationproject.data.response.UserResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface AuthService {

    fun loginIn(userData: ReqBodyUser): UserResponse?

    fun logout(): Completable

}