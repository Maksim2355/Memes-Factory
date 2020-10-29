package com.lumi.surfeducationproject.presenters

import com.lumi.surfeducationproject.domain.repository.UserRepository
import com.lumi.surfeducationproject.views.SplashView
import moxy.MvpPresenter
import javax.inject.Inject


class SplashPresenter @Inject constructor(
    private val userRepository: UserRepository
) : MvpPresenter<SplashView>() {

    fun startApp() {
        viewState.startAnimation()
        userRepository.getUser().subscribe({
            if (it != null){
                viewState.startApp(true)
            }else{
                viewState.startApp(false)
            }
        }, {
            //Todo добавить обарботку ошибки
            viewState.startApp(false)
        })

    }

}