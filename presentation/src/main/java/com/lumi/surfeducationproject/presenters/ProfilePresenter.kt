package com.lumi.surfeducationproject.presenters

import com.example.domain.model.Meme
import com.example.domain.repository.MemeRepository
import com.example.domain.repository.UserRepository
import com.lumi.surfeducationproject.common.base_view.BasePresenter

import com.lumi.surfeducationproject.views.ProfileView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class ProfilePresenter @Inject constructor(
    private val userRepository: UserRepository,
    private val memeRepository: MemeRepository
) : BasePresenter<ProfileView>() {

    fun bindProfile() {
        userRepository.getUser()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it != null) {
                    viewState.showProfile(it)
                } else {
                    viewState.showErrorSnackBarDownloadProfile("Ошибка иннициализации профиля")
                }
            }, {
                viewState.showErrorSnackBarDownloadProfile("Повторите попытку")
            })
    }

    fun loadMemes() {
        memeRepository.getUserMemes()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showLoadState() }
            .doFinally { viewState.hideLoadState() }
            .subscribe({
                viewState.showMemes(it)
            }, {
                viewState.showErrorSnackBarDownloadProfile("Ошибка загрузки профиля")
            })
    }

    fun startLogoutDialog() {
        viewState.showDialog()
    }

    fun logout() {
        userRepository
            .deleteUser()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.exitAccount()
            }, {
                viewState.showErrorSnackBarDownloadProfile("Ошибка выхода из аккаунта. Попробуйте еще раз")
            })
    }

    fun openDetails(meme: Meme) {
        viewState.openMemeDetails(meme)
    }

    fun shareMemeInSocialNetwork(meme: Meme) {
        viewState.shareMeme(meme)
    }


}