package com.lumi.surfeducationproject.di.modules

import com.lumi.surfeducationproject.di.scopes.FragmentScope
import com.lumi.surfeducationproject.domain.repository.MemeRepository
import com.lumi.surfeducationproject.domain.repository.UserRepository
import com.lumi.surfeducationproject.presenters.*
import dagger.Module
import dagger.Provides

@Module
class PresenterModule {

    @Provides
    @FragmentScope
    fun provideSplashPresenter(userRepository: UserRepository) = SplashPresenter(userRepository)

    @Provides
    @FragmentScope
    fun provideAuthPresenter(userRepository: UserRepository) = AuthPresenter(userRepository)

    @Provides
    @FragmentScope
    fun provideMemeFeedPresenter(memeRepository: MemeRepository) =
        MemesFeedPresenter(memeRepository)

    @Provides
    @FragmentScope
    fun provideAddMemePresenter(
        memeRepository: MemeRepository
    ) = AddMemePresenter(memeRepository)

    @Provides
    @FragmentScope
    fun provideProfilePresenter(
        userRepository: UserRepository,
        memeRepository: MemeRepository
    ) = ProfilePresenter(userRepository, memeRepository)

    @Provides
    @FragmentScope
    fun provideMemeDetailsPresenter(
        userRepository: UserRepository,
        memeRepository: MemeRepository
    ) = MemeDetailsPresenter(userRepository, memeRepository)

}