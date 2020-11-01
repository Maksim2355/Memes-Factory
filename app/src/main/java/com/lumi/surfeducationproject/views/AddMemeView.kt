package com.lumi.surfeducationproject.views

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEnd
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

interface AddMemeView: MvpView {

    @AddToEndSingle
    fun showAddImg()

    @AddToEnd
    fun hideAddImg()

    @AddToEnd
    fun disableCreateMemeBtn()

    @AddToEnd
    fun enableCreateMemeBtn()

    @Skip
    fun showAddImgDialog()

}