package com.lumi.surfeducationproject.di.modules.fragment_modules.tab_modules

import androidx.fragment.app.Fragment
import com.lumi.surfeducationproject.di.modules.fragment_modules.tab_modules.content_modules.AdapterUtilsModule
import com.lumi.surfeducationproject.di.modules.fragment_modules.tab_modules.content_modules.ContentViewModelModule
import com.lumi.surfeducationproject.di.modules.fragment_modules.tab_modules.content_modules.RepositoryContentModule
import com.lumi.surfeducationproject.di.scopes.ContentFragmentScope
import com.lumi.surfeducationproject.di.scopes.TabFragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
interface MainTabModule {

    @ContentFragmentScope
    @ContributesAndroidInjector(
        modules = [AdapterUtilsModule::class,
            ContentViewModelModule::class,
            RepositoryContentModule::class]
    )
    fun contentFragmentInjector(): Fragment
}
