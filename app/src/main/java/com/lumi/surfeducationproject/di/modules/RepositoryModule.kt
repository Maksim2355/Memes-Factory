package com.lumi.surfeducationproject.di.modules

import com.lumi.surfeducationproject.data.api.MemesApi
import com.lumi.surfeducationproject.data.dto.mappers.MemeDtoDataMapper
import com.lumi.surfeducationproject.data.dto.mappers.UserDtoDataMapper
import com.lumi.surfeducationproject.data.repository.MemeRepositoryImpl
import com.lumi.surfeducationproject.data.repository.UserRepositoryImpl
import com.lumi.surfeducationproject.data.services.local.SharedPreferenceService
import com.lumi.surfeducationproject.data.services.network.AuthService
import com.lumi.surfeducationproject.domain.repository.MemeRepository
import com.lumi.surfeducationproject.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {


    @Provides
    @Singleton
    fun provideUserMapper(): UserDtoDataMapper{
        return UserDtoDataMapper()
    }

    @Provides
    @Singleton
    fun provideMemeMapper(): MemeDtoDataMapper{
        return MemeDtoDataMapper()
    }


    @Provides
    @Singleton
    fun provideUserRepository(
        sharedPreferences: SharedPreferenceService,
        authService: AuthService,
        mapper: UserDtoDataMapper
        ): UserRepository {
        return UserRepositoryImpl(sharedPreferences, authService, mapper)
    }


    @Provides
    @Singleton
    fun provideMemeRepository(
        memesApi: MemesApi,
        mapper: MemeDtoDataMapper
    ): MemeRepository {
        return MemeRepositoryImpl(memesApi, mapper)
    }


}