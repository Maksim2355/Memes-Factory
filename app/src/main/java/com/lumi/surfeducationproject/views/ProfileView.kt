package com.lumi.surfeducationproject.views

import com.lumi.surfeducationproject.data.dto.MemDto
import com.lumi.surfeducationproject.data.dto.AuthInfoDto
import moxy.MvpView

interface ProfileView: MvpView {

    fun showMemes(memDtos: List<MemDto>)

    fun exitAccount()

    fun showProfile(authInfoDto: AuthInfoDto)

}
