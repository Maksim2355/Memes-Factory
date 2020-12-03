package com.example.data.services.local

import com.example.domain.model.User


interface SharedPreferenceService {

    fun saveUser(user: User)

    fun readUser(): User?

    fun deleteUser()

}