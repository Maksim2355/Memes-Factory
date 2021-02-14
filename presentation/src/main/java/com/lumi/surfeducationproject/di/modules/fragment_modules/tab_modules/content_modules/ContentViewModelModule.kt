package com.lumi.surfeducationproject.di.modules.fragment_modules.tab_modules.content_modules

import androidx.lifecycle.ViewModel
import com.lumi.surfeducationproject.di.keys.ViewModelKey
import com.lumi.surfeducationproject.vm.AddMemeViewModel
import com.lumi.surfeducationproject.vm.MemeDetailsViewModel
import com.lumi.surfeducationproject.vm.MemeFeedViewModel
import com.lumi.surfeducationproject.vm.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
interface ContentViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AddMemeViewModel::class)
    fun bindAddMemeViewModel(viewModel: AddMemeViewModel): AddMemeViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MemeDetailsViewModel::class)
    fun bindMemeDetailsViewModel(viewModel: MemeDetailsViewModel): MemeDetailsViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MemeFeedViewModel::class)
    fun bindMemeFeedViewModel(viewModel: MemeFeedViewModel): MemeFeedViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    fun bindProfileViewModel(viewModel: ProfileViewModel): ProfileViewModel

}