package com.lumi.surfeducationproject.views

import com.lumi.surfeducationproject.domain.model.User
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface MemeDetailsView: MvpView {

    @AddToEndSingle
    fun showUserInfoToolbar(user: User)

    @AddToEndSingle
    fun showErrorStateUserInfoToolbar()

}