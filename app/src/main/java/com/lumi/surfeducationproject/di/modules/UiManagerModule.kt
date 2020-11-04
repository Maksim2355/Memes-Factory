package com.lumi.surfeducationproject.di.modules

import android.content.Context
import com.lumi.surfeducationproject.common.SnackBarManager
import com.lumi.surfeducationproject.common.StyleManager
import com.lumi.surfeducationproject.di.scopes.ActivityScope
import dagger.Module
import dagger.Provides


@Module
class UiManagerModule(private val activity: Context) {

    @Provides
    @ActivityScope
    fun provideStyleManager() = activity as StyleManager

    @Provides
    @ActivityScope
    fun provideSnackBarManager() = activity as SnackBarManager

}
