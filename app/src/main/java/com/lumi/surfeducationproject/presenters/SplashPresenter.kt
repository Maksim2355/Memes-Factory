package com.lumi.surfeducationproject.presenters

import com.lumi.surfeducationproject.views.SplashView
import moxy.MvpPresenter


class SplashPresenter: MvpPresenter<SplashView>() {

    fun startApp(){
        viewState.startAnimation()
        //TODO Добавление логики старта правильного фрагмента
        viewState.startApp()
    }

}