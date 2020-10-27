package com.lumi.surfeducationproject.presenters

import com.lumi.surfeducationproject.data.services.local.SharedPreferenceServiceImpl
import com.lumi.surfeducationproject.views.ProfileView
import moxy.MvpPresenter

class ProfilePresenter: MvpPresenter<ProfileView>() {

    fun initProfile(){
        //TODO проверка User na null и добавление mock результата
        viewState.showProfile(SharedPreferenceServiceImpl.readUser()!!)
    }


    fun logout(){
        //Выполнение запроса на сервера для выхода из аккаунта и очистка данных в sharedPref

        SharedPreferenceServiceImpl.deleteUser()
        viewState.exitAccount()
    }

    fun loadMemes(){
        //Todo Добавление и загрузка мемов с БД
    }

}
