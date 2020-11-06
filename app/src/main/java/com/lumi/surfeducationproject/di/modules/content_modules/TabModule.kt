package com.lumi.surfeducationproject.di.modules.content_modules

import com.lumi.surfeducationproject.common.BottomBarVisible
import com.lumi.surfeducationproject.di.scopes.FragmentContentScope
import com.lumi.surfeducationproject.navigation.NavigationBackPressed
import com.lumi.surfeducationproject.navigation.NavigationMemeDetails
import com.lumi.surfeducationproject.ui.TabFragment
import dagger.Module
import dagger.Provides


@Module
class TabModule(private val tabFragment: TabFragment) {

    @Provides
    @FragmentContentScope
    fun provideBottomBarVisible(): BottomBarVisible = tabFragment

    @Provides
    @FragmentContentScope
    fun provideNavigationBackPressed(): NavigationBackPressed = tabFragment

    @Provides
    @FragmentContentScope
    fun provideNavigationMemeDetails(): NavigationMemeDetails = tabFragment
}