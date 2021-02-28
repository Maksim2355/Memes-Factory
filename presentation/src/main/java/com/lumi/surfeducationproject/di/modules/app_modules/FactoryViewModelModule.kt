package com.lumi.surfeducationproject.di.modules.app_modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lumi.surfeducationproject.di.keys.ViewModelKey
import com.lumi.surfeducationproject.vm.*
import com.lumi.surfeducationproject.vm.factory.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class FactoryViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}