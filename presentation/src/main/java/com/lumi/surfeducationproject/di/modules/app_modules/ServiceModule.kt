package com.lumi.surfeducationproject.di.modules.app_modules

import android.content.SharedPreferences
import com.example.data.api.AuthApi
import com.example.data.services.local.SharedPreferenceService
import com.example.data.services.local.SharedPreferenceServiceImpl
import com.example.data.services.network.AuthService
import com.example.data.services.network.AuthServiceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ServiceModule {

    @Provides
    @Singleton
    fun provideSharedPreferenceService(sharedPreference: SharedPreferences): SharedPreferenceService =
        SharedPreferenceServiceImpl(sharedPreference)

    @Provides
    @Singleton
    fun provideAuthService(authApi: AuthApi): AuthService = AuthServiceImpl(authApi)

}