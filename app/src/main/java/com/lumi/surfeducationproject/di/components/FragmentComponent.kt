package com.lumi.surfeducationproject.di.components

import com.lumi.surfeducationproject.di.modules.AdapterUtilsModule
import com.lumi.surfeducationproject.di.modules.PresenterModule
import com.lumi.surfeducationproject.di.scopes.FragmentScope
import com.lumi.surfeducationproject.ui.*
import dagger.Subcomponent


@FragmentScope
@Subcomponent(modules = [PresenterModule::class, AdapterUtilsModule::class])
interface FragmentComponent {

    fun inject(addMemeFragment: AddMemeFragment)

    fun inject(authFragment: AuthFragment)

    fun inject(memeDetailsFragment: MemeDetailsFragment)

    fun inject(memeFeedFragment: MemeFeedFragment)

    fun inject(profileFragment: ProfileFragment)

    fun inject(splashFragment: SplashFragment)

}