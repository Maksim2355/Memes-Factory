package com.lumi.surfeducationproject.di.modules

import com.lumi.surfeducationproject.services.NetworkService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class NetworkingModule {

    @Provides
    @Singleton
    fun provideApi(): NetworkService {
        return NetworkService
    }

}