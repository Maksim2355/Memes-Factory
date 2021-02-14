package com.lumi.surfeducationproject.di.modules.fragment_modules.tab_modules.content_modules

import com.lumi.surfeducationproject.ui.controllers.MemeController
import dagger.Binds
import dagger.Module
import ru.surfstudio.android.easyadapter.EasyAdapter


@Module
interface AdapterUtilsModule {

    @Binds
    fun bindEasyAdapter(easyAdapter: EasyAdapter): EasyAdapter

    @Binds
    fun bindMemeController(memeController: MemeController): MemeController

}
