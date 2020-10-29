package com.lumi.surfeducationproject.di.components

import com.lumi.surfeducationproject.di.modules.AdapterUtilsModule
import com.lumi.surfeducationproject.di.modules.NavigationMainModule
import com.lumi.surfeducationproject.di.modules.PresenterModule
import com.lumi.surfeducationproject.di.modules.UiManagerModule
import com.lumi.surfeducationproject.di.scopes.ActivityScope
import dagger.Subcomponent


@ActivityScope
@Subcomponent(modules = [NavigationMainModule::class, UiManagerModule::class])
interface ActivityComponent {

    fun addFragmentComponent(presenterModule: PresenterModule,
                             adapterUtilsModule: AdapterUtilsModule): FragmentComponent



}