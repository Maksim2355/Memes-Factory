package com.lumi.surfeducationproject.presenters

import com.lumi.surfeducationproject.data.repository.UserRepositoryImpl
import com.lumi.surfeducationproject.domain.repository.UserRepository
import com.lumi.surfeducationproject.views.ProfileView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class ProfilePresenter: MvpPresenter<ProfileView>() {

    private val userRepository: UserRepository = UserRepositoryImpl()

    fun loadProfile(){
        userRepository.getUser().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it != null){
                    viewState.showProfile(it)
                }else{
                    viewState.showErrorSnackBarDownloadProfile("Ошибка иннициализации профиля")
                }
            },{
                viewState.showErrorSnackBarDownloadProfile("Повторите попытку")
            })
    }

    fun logout(){
        userRepository
            .deleteUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { viewState.exitAccount() }
    }

    fun loadMemes(){
        //Todo Добавление и загрузка мемов с БД
    }

}
