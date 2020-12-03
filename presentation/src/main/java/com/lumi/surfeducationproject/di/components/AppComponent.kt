package com.lumi.surfeducationproject.di.components

import com.example.data.di.NetworkModule
import com.example.data.di.StorageModule
import com.lumi.surfeducationproject.di.modules.activity_modules.ActivityModule
import com.lumi.surfeducationproject.di.modules.activity_modules.NavigationMainModule
import com.lumi.surfeducationproject.di.modules.app_modules.*
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [AppModule::class, NetworkModule::class,
        ServiceModule::class, StorageModule::class, RepositoryModule::class]
)
interface AppComponent {
    fun addActivityComponent(navigationMainModule: NavigationMainModule,
                             activityModule: ActivityModule
    ): ActivityComponent
}