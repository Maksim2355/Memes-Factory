package com.lumi.surfeducationproject.presenters

import com.lumi.surfeducationproject.data.repository.UserRepositoryImpl
import com.lumi.surfeducationproject.domain.repository.UserRepository
import com.lumi.surfeducationproject.views.SplashView
import moxy.MvpPresenter


class SplashPresenter : MvpPresenter<SplashView>() {

    private val authRepository: UserRepository = UserRepositoryImpl()

    fun startApp() {
        viewState.startAnimation()
        authRepository.getUser().subscribe({
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