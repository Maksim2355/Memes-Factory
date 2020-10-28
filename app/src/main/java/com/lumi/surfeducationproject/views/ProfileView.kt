package com.lumi.surfeducationproject.views

import com.lumi.surfeducationproject.domain.model.Meme
import com.lumi.surfeducationproject.domain.model.User
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

interface ProfileView: MvpView {

    @AddToEndSingle
    fun showMemes(memeList: List<Meme>)

    @Skip
    fun exitAccount()

    @AddToEndSingle
    fun showProfile(user: User)

    @AddToEndSingle
    fun showErrorSnackBarDownloadProfile(message: String)

}
