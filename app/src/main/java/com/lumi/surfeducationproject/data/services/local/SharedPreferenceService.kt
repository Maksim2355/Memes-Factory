package com.lumi.surfeducationproject.data.services.local

import com.lumi.surfeducationproject.domain.model.User

interface SharedPreferenceService {

    fun saveUser(user: User)

    fun readUser(): User?

    fun deleteUser()

}