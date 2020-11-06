package com.lumi.surfeducationproject.di.components

import com.lumi.surfeducationproject.di.modules.AdapterUtilsModule
import com.lumi.surfeducationproject.di.modules.NavigationMainModule
import com.lumi.surfeducationproject.di.modules.PresenterModule
import com.lumi.surfeducationproject.di.modules.ManagerModule
import com.lumi.surfeducationproject.di.scopes.ActivityScope
import dagger.Subcomponent


@ActivityScope
@Subcomponent(modules = [NavigationMainModule::class, ManagerModule::class])
interface ActivityComponent {

    fun addFragmentComponent(presenterModule: PresenterModule,
                             adapterUtilsModule: AdapterUtilsModule): FragmentComponent



}