package com.lumi.surfeducationproject.di.components

import com.lumi.surfeducationproject.di.modules.content_modules.AdapterUtilsModule
import com.lumi.surfeducationproject.di.modules.content_modules.RepositoryContentModule
import com.lumi.surfeducationproject.di.modules.content_modules.TabModule
import com.lumi.surfeducationproject.di.scopes.FragmentContentScope
import com.lumi.surfeducationproject.ui.*
import dagger.Subcomponent


@FragmentContentScope
@Subcomponent(
    modules = [PresenterContentModule::class, AdapterUtilsModule::class,
        RepositoryContentModule::class, TabModule::class]
)
interface FragmentContentComponent {

    fun inject(addMemeFragment: AddMemeFragment)

    fun inject(memeDetailsFragment: MemeDetailsFragment)

    fun inject(memeFeedFragment: MemeFeedFragment)

    fun inject(profileFragment: ProfileFragment)

}