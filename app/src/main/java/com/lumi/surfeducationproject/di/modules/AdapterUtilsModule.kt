package com.lumi.surfeducationproject.di.modules

import com.lumi.surfeducationproject.controllers.MemeController
import dagger.Module
import dagger.Provides
import ru.surfstudio.android.easyadapter.EasyAdapter


@Module
class AdapterUtilsModule {

    @Provides
    fun provideEasyAdapter(): EasyAdapter{
        return EasyAdapter()
    }

    @Provides
    fun provideMemeController(): MemeController{
        return MemeController()
    }

}
