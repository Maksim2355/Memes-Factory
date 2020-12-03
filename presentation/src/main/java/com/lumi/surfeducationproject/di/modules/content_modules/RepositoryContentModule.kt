package com.lumi.surfeducationproject.di.modules.content_modules

import com.example.data.api.MemesApi
import com.example.data.dto.mappers.meme.MemeDataMapper
import com.example.data.dto.network.NetworkMeme
import com.example.data.repository.MemeRepositoryImpl
import com.example.data.storage.Storage
import com.example.domain.repository.MemeRepository
import com.lumi.surfeducationproject.di.scopes.FragmentContentScope

import dagger.Module
import dagger.Provides


@Module
class RepositoryContentModule {

    @Provides
    @FragmentContentScope
    fun provideMemeRepository(
        memesApi: MemesApi,
        mapperNetwork: MemeDataMapper<NetworkMeme>,
        storage: Storage
    ): MemeRepository = MemeRepositoryImpl(memesApi, mapperNetwork, storage)

}