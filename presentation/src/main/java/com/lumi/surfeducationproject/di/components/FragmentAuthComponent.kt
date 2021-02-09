package com.lumi.surfeducationproject.di.components

import com.lumi.surfeducationproject.di.scopes.FragmentAuthScope
import com.lumi.surfeducationproject.ui.AuthFragment
import com.lumi.surfeducationproject.ui.SplashFragment
import dagger.Subcomponent

@FragmentAuthScope
@Subcomponent(modules = [PresenterAuthModule::class])
interface FragmentAuthComponent {

    fun inject(authFragment: AuthFragment)

    fun inject(splashFragment: SplashFragment)

}