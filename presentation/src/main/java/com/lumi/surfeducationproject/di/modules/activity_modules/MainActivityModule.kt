package com.lumi.surfeducationproject.di.modules.activity_modules

import androidx.fragment.app.Fragment
import com.lumi.surfeducationproject.di.modules.fragment_modules.auth_modules.AuthViewModelModule
import com.lumi.surfeducationproject.di.modules.fragment_modules.tab_modules.MainTabModule
import com.lumi.surfeducationproject.di.modules.fragment_modules.tab_modules.content_modules.AdapterUtilsModule
import com.lumi.surfeducationproject.di.modules.fragment_modules.tab_modules.content_modules.RepositoryContentModule
import com.lumi.surfeducationproject.di.modules.fragment_modules.tab_modules.TabModule
import com.lumi.surfeducationproject.di.scopes.AuthFragmentScope
import com.lumi.surfeducationproject.di.scopes.TabFragmentScope
import com.lumi.surfeducationproject.ui.TabFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
interface MainActivityModule {

    @TabFragmentScope
    @ContributesAndroidInjector(
        modules = [MainTabModule::class, TabModule::class]
    )
    fun tabFragmentInjector(): TabFragment

    @AuthFragmentScope
    @ContributesAndroidInjector(
        modules = [AuthViewModelModule::class]
    )
    fun authFragmentInjector(): Fragment

}
