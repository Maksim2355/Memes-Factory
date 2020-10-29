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
    fun provideSplashPresenter(userRepository: UserRepository): SplashPresenter{
        return SplashPresenter(userRepository)
    }

    @Provides
    @FragmentScope
    fun provideAuthPresenter(userRepository: UserRepository): AuthPresenter{
        return AuthPresenter(userRepository)
    }

    @Provides
    @FragmentScope
    fun provideMemeFeedPresenter(memeRepository: MemeRepository): MemesFeedPresenter{
        return MemesFeedPresenter(memeRepository)
    }

    @Provides
    @FragmentScope
    fun provideAddMemePresenter(userRepository: UserRepository,
                                memeRepository: MemeRepository): AddMemePresenter{
        return AddMemePresenter(memeRepository, userRepository)
    }

    @Provides
    @FragmentScope
    fun provideProfilePresenter(userRepository: UserRepository,
                                memeRepository: MemeRepository): ProfilePresenter{
        return ProfilePresenter(userRepository, memeRepository)
    }

    @Provides
    @FragmentScope
    fun provideMemeDetailsPresenter(userRepository: UserRepository,
                                    memeRepository: MemeRepository): MemeDetailsPresenter{
        return MemeDetailsPresenter(userRepository, memeRepository)
    }

}