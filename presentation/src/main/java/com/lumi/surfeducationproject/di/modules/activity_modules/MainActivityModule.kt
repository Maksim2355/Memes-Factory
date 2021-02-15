package com.lumi.surfeducationproject.di.modules.activity_modules

import com.lumi.surfeducationproject.di.modules.fragment_modules.auth_modules.AuthViewModelModule
import com.lumi.surfeducationproject.di.modules.fragment_modules.tab_modules.*
import com.lumi.surfeducationproject.di.scopes.AuthFragmentScope
import com.lumi.surfeducationproject.di.scopes.TabFragmentScope
import com.lumi.surfeducationproject.ui.AuthFragment
import com.lumi.surfeducationproject.ui.SplashFragment
import com.lumi.surfeducationproject.ui.TabFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
interface MainActivityModule {

    @TabFragmentScope
    @ContributesAndroidInjector(
        modules = [MainTabModule::class, TabModule::class,
            ContentViewModelModule::class, RepositoryContentModule::class,
            TabBottomNavigationModule::class]
    )
    fun tabFragmentInjector(): TabFragment

    @AuthFragmentScope
    @ContributesAndroidInjector(
        modules = [AuthViewModelModule::class]
    )
    fun authFragmentInjector(): AuthFragment

    @AuthFragmentScope
    @ContributesAndroidInjector(
        modules = [AuthViewModelModule::class]
    )
    fun splashFragmentInjector(): SplashFragment

}
