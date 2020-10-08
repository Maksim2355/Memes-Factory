package com.lumi.surfeducationproject.views

import moxy.MvpView
import moxy.viewstate.strategy.alias.Skip

interface SplashView: MvpView {

    @Skip
    fun startAnimation()

    @Skip
    fun startApp()

}