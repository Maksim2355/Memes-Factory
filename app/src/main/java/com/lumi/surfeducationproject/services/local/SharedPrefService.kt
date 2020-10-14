package com.lumi.surfeducationproject.services.local

import com.lumi.surfeducationproject.data.model.User

interface SharedPrefService {

    fun saveUser(user: User)

    fun readUser(): User

}