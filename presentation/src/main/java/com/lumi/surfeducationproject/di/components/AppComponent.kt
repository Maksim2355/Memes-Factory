package com.lumi.surfeducationproject.di.components

import android.content.Context
import com.example.data.di.NetworkModule
import com.example.data.di.StorageModule
import com.lumi.surfeducationproject.App
import com.lumi.surfeducationproject.di.modules.app_modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton


@Singleton
@Component(
    modules = [AndroidInjectionModule::class, AppModule::class, NetworkModule::class, ServiceModule::class,
        StorageUtilsModule::class, StorageModule::class, RepositoryModule::class,
        FactoryViewModelModule::class]
)
interface AppComponent: AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(context: Context): Builder
        fun build(): AppComponent
    }


}