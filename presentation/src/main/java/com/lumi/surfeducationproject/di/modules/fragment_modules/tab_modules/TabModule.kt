package com.lumi.surfeducationproject.di.modules.fragment_modules.tab_modules

import com.lumi.surfeducationproject.common.managers.BottomBarVisible
import com.lumi.surfeducationproject.di.scopes.TabFragmentScope
import com.lumi.surfeducationproject.navigation.NavigationBackPressed
import com.lumi.surfeducationproject.ui.TabFragment
import dagger.Binds
import dagger.Module


@Module
interface TabModule {

    @Binds
    @TabFragmentScope
    fun bindBottomBarVisible(tabFragment: TabFragment): BottomBarVisible

    @Binds
    @TabFragmentScope
    fun bindNavigationBackPressed(tabFragment: TabFragment): NavigationBackPressed

}