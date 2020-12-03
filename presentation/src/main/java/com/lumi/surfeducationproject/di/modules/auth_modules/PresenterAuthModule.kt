package com.lumi.surfeducationproject.di.modules.auth_modules

import com.example.domain.repository.UserRepository
import com.lumi.surfeducationproject.di.scopes.FragmentAuthScope
import com.lumi.surfeducationproject.presenters.AuthPresenter
import com.lumi.surfeducationproject.presenters.SplashPresenter
import dagger.Module
import dagger.Provides


@Module
class PresenterAuthModule {

    @Provides
    @FragmentAuthScope
    fun provideSplashPresenter(userRepository: UserRepository) = SplashPresenter(userRepository)

    @Provides
    @FragmentAuthScope
    fun provideAuthPresenter(userRepository: UserRepository) = AuthPresenter(userRepository)


}