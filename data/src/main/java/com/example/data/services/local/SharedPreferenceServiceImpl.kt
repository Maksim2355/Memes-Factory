package com.example.data.services.local

import android.content.SharedPreferences
import com.example.domain.model.User
import javax.inject.Inject

class SharedPreferenceServiceImpl @Inject constructor(
    private val sharedPref: SharedPreferences
) : SharedPreferenceService {

    override fun saveUser(user: User) {
        sharedPref.edit().apply() {
            this.putInt(ID, user.id)
            this.putString(USERNAME, user.username)
            this.putString(FIRST_NAME, user.firstName)
            this.putString(LAST_NAME, user.lastName)
            this.putString(USER_DESCRIPTION, user.descriptionProfile)
        }.apply()
    }

    override fun readUser(): User? {
        return if (sharedPref.contains(ID)){
            val id = sharedPref.getInt(ID, 0)
            val username = sharedPref.getString(USERNAME, "")
            val firstName = sharedPref.getString(FIRST_NAME, "")
            val lastName = sharedPref.getString(LAST_NAME, "")
            val userDescription = sharedPref.getString(USER_DESCRIPTION, "")
            User(id, username!!, firstName!!, lastName!!, userDescription!!)
        } else null

    }

    override fun deleteUser() {
        sharedPref.edit().clear().apply()
    }

    companion object{
        private val ID = "ID"
        private val USERNAME = "USERNAME"
        private val FIRST_NAME = "FIRST_NAME"
        private val LAST_NAME = "LAST_NAME"
        private val USER_DESCRIPTION = "USER_DESCRIPTION"
    }
}