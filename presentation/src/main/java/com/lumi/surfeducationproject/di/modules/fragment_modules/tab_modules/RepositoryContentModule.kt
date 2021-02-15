package com.lumi.surfeducationproject.di.modules.fragment_modules.tab_modules

import com.example.data.repository.MemeRepositoryImpl
import com.example.domain.repository.MemeRepository
import com.lumi.surfeducationproject.di.scopes.TabFragmentScope
import dagger.Binds

import dagger.Module


@Module
interface RepositoryContentModule {

    @Binds
    @TabFragmentScope
    fun provideMemeRepository(memeRepositoryImpl: MemeRepositoryImpl): MemeRepository

}