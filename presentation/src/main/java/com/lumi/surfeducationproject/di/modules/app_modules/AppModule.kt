package com.lumi.surfeducationproject.di.modules.app_modules

import com.lumi.surfeducationproject.AppActivity
import com.lumi.surfeducationproject.di.modules.activity_modules.MainActivityModule
import com.lumi.surfeducationproject.di.modules.activity_modules.NavigationMainModule
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector

import com.lumi.surfeducationproject.di.scopes.ActivityScope





@Module(includes = [AndroidInjectionModule::class])
interface AppModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class, NavigationMainModule::class])
    fun activityInjector(): AppActivity

}