package com.lumi.surfeducationproject.di.modules.content_modules

import com.lumi.surfeducationproject.data.api.MemesApi
import com.lumi.surfeducationproject.data.dto.mappers.meme.MemeDataMapper
import com.lumi.surfeducationproject.data.dto.network.NetworkMeme
import com.lumi.surfeducationproject.data.repository.MemeRepositoryImpl
import com.lumi.surfeducationproject.data.storage.Storage
import com.lumi.surfeducationproject.di.scopes.FragmentContentScope
import com.lumi.surfeducationproject.domain.repository.MemeRepository
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