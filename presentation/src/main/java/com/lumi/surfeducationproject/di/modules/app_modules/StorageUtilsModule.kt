package com.lumi.surfeducationproject.di.modules.app_modules

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class StorageUtilsModule() {

    @Provides
    @Singleton
    fun provideSharedPreference(context: Context): SharedPreferences =
        context.getSharedPreferences("UserPreference", Context.MODE_PRIVATE)

}