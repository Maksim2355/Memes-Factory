package com.lumi.surfeducationproject.di.modules.content_modules

import com.lumi.surfeducationproject.di.scopes.FragmentContentScope
import com.lumi.surfeducationproject.domain.repository.MemeRepository
import com.lumi.surfeducationproject.domain.repository.UserRepository
import com.lumi.surfeducationproject.presenters.*
import dagger.Module
import dagger.Provides

@Module
class PresenterContentModule {

    @Provides
    @FragmentContentScope
    fun provideMemeFeedPresenter(memeRepository: MemeRepository) =
        MemesFeedPresenter(memeRepository)

    @Provides
    @FragmentContentScope
    fun provideAddMemePresenter(
        memeRepository: MemeRepository
    ) = AddMemePresenter(memeRepository)

    @Provides
    @FragmentContentScope
    fun provideProfilePresenter(
        userRepository: UserRepository,
        memeRepository: MemeRepository
    ) = ProfilePresenter(userRepository, memeRepository)

    @Provides
    @FragmentContentScope
    fun provideMemeDetailsPresenter(
        userRepository: UserRepository
    ) = MemeDetailsPresenter(userRepository)

}