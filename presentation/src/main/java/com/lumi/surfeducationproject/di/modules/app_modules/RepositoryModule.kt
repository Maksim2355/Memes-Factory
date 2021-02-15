package com.lumi.surfeducationproject.di.modules.app_modules

import com.example.data.dto.mappers.UserDtoDataMapper
import com.example.data.dto.mappers.meme.MemeDataMapper
import com.example.data.dto.mappers.meme.MemeNetworkDataMapper
import com.example.data.dto.network.NetworkMeme
import com.example.data.repository.UserRepositoryImpl
import com.example.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindsUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

}