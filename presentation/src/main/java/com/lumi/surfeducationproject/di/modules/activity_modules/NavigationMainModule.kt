package com.lumi.surfeducationproject.di.modules.activity_modules

import com.lumi.surfeducationproject.AppActivity
import com.lumi.surfeducationproject.di.named.ACTIVITY_NAVIGATION
import com.lumi.surfeducationproject.di.scopes.ActivityScope
import com.lumi.surfeducationproject.navigation.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class NavigationMainModule {

    @Provides
    @ActivityScope
    @Named(ACTIVITY_NAVIGATION)
    fun provideNavigationAuth(activity: AppActivity) : NavigationDestination {
        return activity
    }

}