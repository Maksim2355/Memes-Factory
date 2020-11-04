package com.lumi.surfeducationproject.di.modules

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideContext() = context


    @Provides
    @Singleton
    fun provideSharedPreference(): SharedPreferences =
        context.getSharedPreferences("UserPreference", Context.MODE_PRIVATE)


}