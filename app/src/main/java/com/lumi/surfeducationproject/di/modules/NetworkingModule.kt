package com.lumi.surfeducationproject.di.modules

import com.lumi.surfeducationproject.data.services.network.NetworkServiceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class NetworkingModule {

    @Provides
    @Singleton
    fun provideApi(): NetworkServiceImpl {
        return NetworkServiceImpl
    }

}