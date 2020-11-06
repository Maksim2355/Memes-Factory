package com.lumi.surfeducationproject.di.modules.app_modules

import com.lumi.surfeducationproject.data.dto.mappers.UserDtoDataMapper
import com.lumi.surfeducationproject.data.dto.mappers.meme.MemeDataMapper
import com.lumi.surfeducationproject.data.dto.mappers.meme.MemeNetworkDataMapper
import com.lumi.surfeducationproject.data.dto.network.NetworkMeme
import com.lumi.surfeducationproject.data.repository.UserRepositoryImpl
import com.lumi.surfeducationproject.data.services.local.SharedPreferenceService
import com.lumi.surfeducationproject.data.services.network.AuthService
import com.lumi.surfeducationproject.domain.repository.UserRepository
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