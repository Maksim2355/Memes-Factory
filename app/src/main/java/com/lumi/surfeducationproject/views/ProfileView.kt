package com.lumi.surfeducationproject.views

import com.lumi.surfeducationproject.data.dto.network.NetworkMeme
import com.lumi.surfeducationproject.data.dto.network.NetworkUserResponse
import moxy.MvpView

interface ProfileView: MvpView {

    fun showMemes(networkMemes: List<NetworkMeme>)

    fun exitAccount()

    fun showProfile(networkUserResponse: NetworkUserResponse)

}
