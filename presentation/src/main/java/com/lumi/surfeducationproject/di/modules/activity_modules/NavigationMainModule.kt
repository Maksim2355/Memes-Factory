package com.lumi.surfeducationproject.di.modules.activity_modules

import android.app.Activity
import android.content.Context
import com.lumi.surfeducationproject.AppActivity
import com.lumi.surfeducationproject.di.scopes.ActivityScope
import com.lumi.surfeducationproject.navigation.*
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface NavigationMainModule {

    @Binds
    @ActivityScope
    fun bindNavigationAuth(activity: AppActivity) : NavigationAuth

    @Binds
    @ActivityScope
    fun bindNavigationContent(activity: AppActivity): NavigationContent

    @Binds
    @ActivityScope
    fun bindNavigationStartApp(activity: AppActivity): NavigationStartApp

}