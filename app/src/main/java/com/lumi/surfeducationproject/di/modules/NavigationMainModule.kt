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
    fun provideNavigationAuth(): NavigationAuth{
        return activity as NavigationAuth
    }

    @Provides
    @ActivityScope
    fun provideNavigationBackPressed(): NavigationBackPressed{
        return activity as NavigationBackPressed
    }

    @Provides
    @ActivityScope
    fun provideNavigationContent(): NavigationContent{
        return activity as NavigationContent
    }

    @Provides
    @ActivityScope
    fun provideNavigationMemeDetails(): NavigationMemeDetails{
        return activity as NavigationMemeDetails
    }

    @Provides
    @ActivityScope
    fun provideNavigationStartApp(): NavigationStartApp{
        return activity as NavigationStartApp
    }


}