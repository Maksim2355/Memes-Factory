package com.lumi.surfeducationproject.presenters

import com.lumi.surfeducationproject.domain.repository.MemeRepository
import com.lumi.surfeducationproject.domain.repository.UserRepository
import com.lumi.surfeducationproject.views.MemeDetailsView
import moxy.MvpPresenter
import javax.inject.Inject

class MemeDetailsPresenter @Inject constructor(
    private val userRepository: UserRepository,
    private val memeRepository: MemeRepository
): MvpPresenter<MemeDetailsView>() {

}