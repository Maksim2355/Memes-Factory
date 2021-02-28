package com.lumi.surfeducationproject.di.modules.fragment_modules.tab_modules.content_modules

import com.lumi.surfeducationproject.ui.controllers.MemeController
import dagger.Binds
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
