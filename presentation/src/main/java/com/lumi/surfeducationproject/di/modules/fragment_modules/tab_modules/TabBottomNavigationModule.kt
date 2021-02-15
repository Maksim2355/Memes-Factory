package com.lumi.surfeducationproject.di.modules.fragment_modules.tab_modules

import com.lumi.surfeducationproject.di.named.FRAGMENT_CONTENT_NAVIGATION
import com.lumi.surfeducationproject.di.scopes.TabFragmentScope
import com.lumi.surfeducationproject.navigation.NavigationDestination
import com.lumi.surfeducationproject.ui.TabFragment
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class TabBottomNavigationModule {

    @Provides
    @TabFragmentScope
    @Named(FRAGMENT_CONTENT_NAVIGATION)
    fun provideNavigationMemeDetails(tabFragment: TabFragment): NavigationDestination {
        return tabFragment
    }

}