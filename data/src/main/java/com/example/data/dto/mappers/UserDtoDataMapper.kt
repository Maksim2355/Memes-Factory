package com.example.data.dto.mappers

import com.example.data.dto.network.LoginUserRequest
import com.example.data.dto.network.UserResponse
import com.example.domain.model.AuthorizationData
import com.example.domain.model.User
import javax.inject.Inject


class UserDtoDataMapper {

    fun transform(userResponse: UserResponse): User {
        val userInfo = userResponse.userInfo
        return User(
            userInfo.id,
            userInfo.username,
            userInfo.firstName,
            userInfo.lastName,
            userInfo.userDescription
        )
    }

    fun transformAuthData(authorizationData: AuthorizationData): LoginUserRequest =
        LoginUserRequest(authorizationData.login, authorizationData.password)
}