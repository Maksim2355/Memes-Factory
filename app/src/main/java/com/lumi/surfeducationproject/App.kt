package com.lumi.surfeducationproject

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.lumi.surfeducationproject.di.components.AppComponent
import com.lumi.surfeducationproject.services.local.SharedPrefService
import com.lumi.surfeducationproject.services.local.SharedPrefServiceImpl

class App : Application() {

    private val USER_PREFERENCES = "USER_PREFERENCES"
    lateinit var sharedPref: SharedPreferences

    companion object {
        lateinit var instance: App;
    }

    override fun onCreate() {
        super.onCreate()
        instance = this;
        sharedPref = getSharedPreferences(USER_PREFERENCES, Context.MODE_PRIVATE)
    }


}