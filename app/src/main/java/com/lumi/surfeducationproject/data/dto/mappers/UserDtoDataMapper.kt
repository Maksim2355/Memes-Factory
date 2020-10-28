package com.lumi.surfeducationproject.data.dto.mappers

import com.lumi.surfeducationproject.data.dto.network.NetworkUserResponse
import com.lumi.surfeducationproject.domain.model.User

class UserDtoDataMapper {


    fun transform(userResponse: NetworkUserResponse): User{
        val userInfo = userResponse.userInfo
        return User(userInfo.id,
            userInfo.username,
            userInfo.firstName,
            userInfo.lastName,
            userInfo.userDescription)
    }

}