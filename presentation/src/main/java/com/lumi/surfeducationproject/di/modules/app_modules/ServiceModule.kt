package com.lumi.surfeducationproject.di.modules.app_modules

import android.content.SharedPreferences
import com.example.data.api.AuthApi
import com.example.data.services.local.SharedPreferenceService
import com.example.data.services.local.SharedPreferenceServiceImpl
import com.example.data.services.network.AuthService
import com.example.data.services.network.AuthServiceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
interface ServiceModule {

    @Binds
    @Singleton
    fun bindSharedPreferenceService(
        sharedPreferenceService: SharedPreferenceServiceImpl): SharedPreferenceService

    @Binds
    @Singleton
    fun bindAuthService(authService: AuthServiceImpl): AuthService

}