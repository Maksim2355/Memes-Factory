package com.lumi.surfeducationproject.presenters

import com.lumi.surfeducationproject.domain.repository.MemeRepository
import com.lumi.surfeducationproject.domain.repository.UserRepository
import com.lumi.surfeducationproject.views.ProfileView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import javax.inject.Inject

class ProfilePresenter @Inject constructor(
    private val userRepository: UserRepository,
    private val memeRepository: MemeRepository
): MvpPresenter<ProfileView>() {


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
            .subscribe({
                viewState.exitAccount()
            }, {
                viewState.showErrorSnackBarDownloadProfile("Ошибка выхода из аккаунта. Попробуйте еще раз")
            })
    }

    fun loadMemes(){
        //Todo Добавление и загрузка мемов с БД
    }

}
