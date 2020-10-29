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
    fun provideContext(): Context{
        return context
    }

    @Provides
    @Singleton
    fun provideSharedPreference(): SharedPreferences{
        return context.getSharedPreferences("UserPreference", Context.MODE_PRIVATE)
    }


}