package com.lumi.surfeducationproject.data.services.local

import android.content.SharedPreferences
import com.lumi.surfeducationproject.App
import com.lumi.surfeducationproject.data.dto.UserInfo
import com.lumi.surfeducationproject.domain.services.SharedPreferenceService

class SharedPreferenceServiceImpl : SharedPreferenceService {

    private val ID = "ID"
    private val USERNAME = "USERNAME"
    private val FIRST_NAME = "FIRST_NAME"
    private val LAST_NAME = "LAST_NAME"
    private val USER_DESCRIPTION = "USER_DESCRIPTION"

    private val sharedPref: SharedPreferences = App.instance.sharedPref

    override fun saveUser(authInfoDto: UserInfo) {
        sharedPref.edit().apply() {
            this.putInt(ID, authInfoDto.id)
            this.putString(USERNAME, authInfoDto.username)
            this.putString(FIRST_NAME, authInfoDto.firstName)
            this.putString(LAST_NAME, authInfoDto.lastName)
            this.putString(USER_DESCRIPTION, authInfoDto.description)
        }.apply()
    }

    override fun readUser(): UserInfo? {
        if (sharedPref.contains(ID)){
            val id = sharedPref.getInt(ID, 0)
            val username = sharedPref.getString(USERNAME, "")
            val firstName = sharedPref.getString(FIRST_NAME, "")
            val lastName = sharedPref.getString(LAST_NAME, "")
            val userDescription = sharedPref.getString(USER_DESCRIPTION, "")
            return UserInfo(id, username!!, firstName!!, lastName!!, userDescription!!)
        }else return null



    }

    override fun deleteUser() {
        sharedPref.edit().clear().apply()
    }
}