package com.lumi.surfeducationproject.di.components

import com.lumi.surfeducationproject.di.modules.content_modules.AdapterUtilsModule
import com.lumi.surfeducationproject.di.modules.activity_modules.NavigationMainModule
import com.lumi.surfeducationproject.di.modules.activity_modules.ActivityModule
import com.lumi.surfeducationproject.di.modules.content_modules.RepositoryContentModule
import com.lumi.surfeducationproject.di.modules.content_modules.TabModule
import com.lumi.surfeducationproject.di.scopes.ActivityScope
import dagger.Subcomponent


@ActivityScope
@Subcomponent(modules = [NavigationMainModule::class, ActivityModule::class])
interface ActivityComponent {

    fun addFragmentContentComponent(presenterContentModule: PresenterContentModule,
                                    adapterUtilsModule: AdapterUtilsModule,
                                    repositoryContentModule: RepositoryContentModule,
                                    tabModule: TabModule
    ): FragmentContentComponent

    fun addFragmentAuthComponent(
        presenterAuthModule: PresenterAuthModule
    ): FragmentAuthComponent


}