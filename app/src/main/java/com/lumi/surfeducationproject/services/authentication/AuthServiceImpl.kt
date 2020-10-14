package com.lumi.surfeducationproject.services.authentication

import com.lumi.surfeducationproject.data.request_body.ReqBodyUser
import com.lumi.surfeducationproject.data.response.UserResponse
import com.lumi.surfeducationproject.services.network.NetworkServiceImpl
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.Callable

object AuthServiceImpl: AuthService {

    override fun loginIn(userData: ReqBodyUser): Single<UserResponse>? {
        TODO("Not yet implemented")
    }

    override fun logout(): Completable {
        TODO("Not yet implemented")
    }
}