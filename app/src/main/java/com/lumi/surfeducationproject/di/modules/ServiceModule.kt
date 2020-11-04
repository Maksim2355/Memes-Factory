package com.lumi.surfeducationproject.di.modules

import android.content.SharedPreferences
import com.lumi.surfeducationproject.data.api.AuthApi
import com.lumi.surfeducationproject.data.services.local.SharedPreferenceService
import com.lumi.surfeducationproject.data.services.local.SharedPreferenceServiceImpl
import com.lumi.surfeducationproject.data.services.network.AuthService
import com.lumi.surfeducationproject.data.services.network.AuthServiceImpl
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