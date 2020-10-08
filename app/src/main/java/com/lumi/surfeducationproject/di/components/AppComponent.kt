package com.lumi.surfeducationproject.di.components

import com.lumi.surfeducationproject.di.modules.AppModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

}