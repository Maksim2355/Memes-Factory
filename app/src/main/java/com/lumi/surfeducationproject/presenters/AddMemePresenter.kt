package com.lumi.surfeducationproject.presenters

import com.lumi.surfeducationproject.domain.repository.MemeRepository
import com.lumi.surfeducationproject.domain.repository.UserRepository
import com.lumi.surfeducationproject.views.AddMemeView
import moxy.MvpPresenter
import javax.inject.Inject

class AddMemePresenter @Inject constructor(
    memeRepository: MemeRepository,
    userRepository: UserRepository
) : MvpPresenter<AddMemeView>() {



}