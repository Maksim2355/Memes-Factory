package com.lumi.surfeducationproject.di.modules.fragment_modules.tab_modules

import com.lumi.surfeducationproject.di.modules.fragment_modules.tab_modules.content_modules.AdapterUtilsModule
import com.lumi.surfeducationproject.di.scopes.ContentFragmentScope
import com.lumi.surfeducationproject.ui.AddMemeFragment
import com.lumi.surfeducationproject.ui.MemeDetailsFragment
import com.lumi.surfeducationproject.ui.MemeFeedFragment
import com.lumi.surfeducationproject.ui.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
interface MainTabModule {

    @ContentFragmentScope
    @ContributesAndroidInjector(
        modules = [AdapterUtilsModule::class]
    )
    fun memeDetailsFragmentInjector(): MemeDetailsFragment

    @ContentFragmentScope
    @ContributesAndroidInjector
    fun addMemeFragmentInjector(): AddMemeFragment

    @ContentFragmentScope
    @ContributesAndroidInjector
    fun memeFeedFragmentInjector(): MemeFeedFragment

    @ContentFragmentScope
    @ContributesAndroidInjector
    fun profileFragmentInjector(): ProfileFragment

}
