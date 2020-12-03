package com.lumi.surfeducationproject.di.modules.activity_modules

import android.content.Context
import com.lumi.surfeducationproject.common.managers.*
import com.lumi.surfeducationproject.di.scopes.ActivityScope
import dagger.Module
import dagger.Provides


@Module
class ActivityModule(private val activity: Context) {

    @Provides
    @ActivityScope
    fun provideStyleManager() = activity as StyleManager

    @Provides
    @ActivityScope
    fun provideSnackBarManager() = activity as SnackBarManager

    @Provides
    @ActivityScope
    fun providePermissionManager() = activity as PermissionManager

    @Provides
    @ActivityScope
    fun provideFileManager() = activity as FileManager

    @Provides
    @ActivityScope
    fun provideInputModeManager() = activity as InputModeManager

}
