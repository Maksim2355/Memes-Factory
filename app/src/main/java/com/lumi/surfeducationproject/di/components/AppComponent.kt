package com.lumi.surfeducationproject.di.components

import com.lumi.surfeducationproject.di.modules.*
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [AppModule::class, NetworkModule::class,
        ServiceModule::class, StorageModule::class, RepositoryModule::class]
)
interface AppComponent {
    fun addActivityComponent(navigationMainModule: NavigationMainModule,
                             managerModule: ManagerModule): ActivityComponent
}