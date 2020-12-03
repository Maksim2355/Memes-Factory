package com.lumi.surfeducationproject.di.modules.content_modules

import com.lumi.surfeducationproject.ui.controllers.MemeController
import dagger.Module
import dagger.Provides
import ru.surfstudio.android.easyadapter.EasyAdapter


@Module
class AdapterUtilsModule {

    @Provides
    fun provideEasyAdapter() = EasyAdapter()

    @Provides
    fun provideMemeController() = MemeController()

}
