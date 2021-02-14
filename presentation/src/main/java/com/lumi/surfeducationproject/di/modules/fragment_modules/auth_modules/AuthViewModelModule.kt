package com.lumi.surfeducationproject.di.modules.fragment_modules.auth_modules

import androidx.lifecycle.ViewModel
import com.lumi.surfeducationproject.di.keys.ViewModelKey
import com.lumi.surfeducationproject.vm.AuthViewModel
import com.lumi.surfeducationproject.vm.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class AuthViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindSplashViewModel(viewModel: SplashViewModel): SplashViewModel


    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindAuthViewModel(viewModel: AuthViewModel): AuthViewModel
}