package com.example.data.services.network


import com.example.data.api.AuthApi
import com.example.data.dto.network.LoginUserRequest
import com.example.data.dto.network.UserResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class AuthServiceImpl @Inject constructor(
    private val api: AuthApi
): AuthService {

    override fun loginIn(loginUserRequest: LoginUserRequest): Single<UserResponse> {
        return api.loginIn(loginUserRequest)
    }

    //TODO прийти к единому вызову Completable or Maybe<>
    override fun logout(): Completable {
        return api.logout().ignoreElement()
    }

}