package com.lumi.surfeducationproject.di.components

import com.lumi.surfeducationproject.di.modules.*
import dagger.Component
import dagger.Module


@Component(modules = [AppModule::class, NetworkModule::class, ServiceModule::class, StorageModule::class])
interface AppComponent {
    
}