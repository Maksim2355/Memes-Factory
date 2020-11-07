package com.lumi.surfeducationproject.views

import com.lumi.surfeducationproject.domain.model.Meme
import com.lumi.surfeducationproject.domain.model.User
import moxy.MvpView
import moxy.viewstate.strategy.StateStrategyType
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution
import moxy.viewstate.strategy.alias.SingleState
import moxy.viewstate.strategy.alias.Skip

interface ProfileView: MvpView {

    @SingleState
    fun showMemes(memeList: List<Meme>)

    @Skip
    fun exitAccount()

    @AddToEndSingle
    fun showProfile(user: User)

    @AddToEndSingle
    fun showDialog()

    @Skip
    fun showErrorSnackBarDownloadProfile(message: String)

    @Skip
    fun showLoadState()

    @Skip
    fun hideLoadState()

    @OneExecution
    fun openMemeDetails(data: Meme)
}
