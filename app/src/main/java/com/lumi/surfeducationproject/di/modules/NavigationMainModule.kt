package com.lumi.surfeducationproject.di.modules

import android.content.Context
import com.lumi.surfeducationproject.di.scopes.ActivityScope
import com.lumi.surfeducationproject.navigation.*
import dagger.Module
import dagger.Provides

@Module
class NavigationMainModule(private val activity: Context) {

    @Provides
    @ActivityScope
    fun provideNavigationAuth() = activity as NavigationAuth

    @Provides
    @ActivityScope
    fun provideNavigationBackPressed() = activity as NavigationBackPressed

    @Provides
    @ActivityScope
    fun provideNavigationContent() = activity as NavigationContent

    @Provides
    @ActivityScope
    fun provideNavigationMemeDetails() = activity as NavigationMemeDetails

    @Provides
    @ActivityScope
    fun provideNavigationStartApp() = activity as NavigationStartApp

}