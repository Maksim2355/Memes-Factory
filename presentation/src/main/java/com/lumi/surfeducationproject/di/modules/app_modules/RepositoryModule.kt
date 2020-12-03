package com.lumi.surfeducationproject.di.modules.app_modules

import com.example.data.dto.mappers.UserDtoDataMapper
import com.example.data.dto.mappers.meme.MemeDataMapper
import com.example.data.dto.mappers.meme.MemeNetworkDataMapper
import com.example.data.dto.network.NetworkMeme
import com.example.data.repository.UserRepositoryImpl
import com.example.data.services.local.SharedPreferenceService
import com.example.data.services.network.AuthService
import com.example.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideUserMapper(): UserDtoDataMapper = UserDtoDataMapper()

    @Provides
    @Singleton
    fun provideNetworkMemeMapper(): MemeDataMapper<NetworkMeme> = MemeNetworkDataMapper()

    @Provides
    @Singleton
    fun provideUserRepository(
        sharedPreferences: SharedPreferenceService,
        authService: AuthService,
        mapper: UserDtoDataMapper
    ): UserRepository = UserRepositoryImpl(sharedPreferences, authService, mapper)

}