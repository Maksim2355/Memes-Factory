package com.lumi.surfeducationproject.services.local

import android.content.SharedPreferences
import com.lumi.surfeducationproject.App
import com.lumi.surfeducationproject.data.model.User

object SharedPrefServiceImpl : SharedPrefService {

    private val ID = "ID"
    private val USERNAME = "USERNAME"
    private val FIRST_NAME = "FIRST_NAME"
    private val LAST_NAME = "LAST_NAME"
    private val USER_DESCRIPTION = "USER_DESCRIPTION"

    private val sharedPref: SharedPreferences = App.instance.sharedPref

    override fun saveUser(user: User) {
        sharedPref.edit().apply() {
            this.putInt(ID, user.id)
            this.putString(USERNAME, user.username)
            this.putString(FIRST_NAME, user.firstName)
            this.putString(LAST_NAME, user.lastName)
            this.putString(USER_DESCRIPTION, user.userDescription)
        }.apply()
    }

    override fun readUser(): User {
        with(sharedPref){
            val id = getInt(ID, 0)
            val username = getString(USERNAME, "")
            val firstName = getString(FIRST_NAME, "")
            val lastName = getString(LAST_NAME, "")
            val userDescription = getString(USER_DESCRIPTION, "")
            return User(id, username!!, firstName!!, lastName!!, userDescription!!)
        }

    }
}