package com.lumi.surfeducationproject.views

import com.lumi.surfeducationproject.common.base_view.BaseMemeListView
import com.lumi.surfeducationproject.domain.model.Meme
import com.lumi.surfeducationproject.domain.model.User
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType
import moxy.viewstate.strategy.alias.*

@StateStrategyType(value = AddToEndStrategy::class)
interface ProfileView: BaseMemeListView {

    fun showDialog()

    fun showProfile(user: User)

    @Skip
    fun exitAccount()

    @Skip
    fun showErrorSnackBarDownloadProfile(message: String)

}
