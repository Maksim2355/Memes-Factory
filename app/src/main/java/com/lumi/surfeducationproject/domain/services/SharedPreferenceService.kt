package com.lumi.surfeducationproject.domain.services

import com.lumi.surfeducationproject.data.dto.UserInfo

interface SharedPreferenceService {

    fun saveUser(user: UserInfo)

    fun readUser(): UserInfo?

    fun deleteUser()

}