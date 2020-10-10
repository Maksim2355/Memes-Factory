package com.lumi.surfeducationproject.presenters

import com.lumi.surfeducationproject.Utils.NetworkExceptions
import com.lumi.surfeducationproject.enums.EmptyFields
import com.lumi.surfeducationproject.model.User
import com.lumi.surfeducationproject.model.UserAuth
import com.lumi.surfeducationproject.model.response.UserResponse
import com.lumi.surfeducationproject.services.NetworkService
import com.lumi.surfeducationproject.views.AuthView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class AuthPresenter: MvpPresenter<AuthView>() {

    private val LENGTH_PASSWORD = 6

    fun enableCheckPasswordField() {
        viewState.showPasswordHelper(LENGTH_PASSWORD)
        viewState.enableIconEye()
    }

    fun disableCheckPasswordField(password: String) {
        if (password.isEmpty()) viewState.disableIconEye()
        if (password.length >= LENGTH_PASSWORD) viewState.hidePasswordHelper()
    }

    fun authUser(login: String, password: String) {
        viewState.showProgressBar()
        if (checkFields(login, password)) {
            val userAuth = UserAuth(login, password)
            NetworkService.getApiService().authorizationUser(userAuth)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { viewState.showProgressBar() }
                .doFinally { viewState.hideProgressBar() }
                .subscribe({
                    saveUserData(it.userInfo)
                },{
                    if (NetworkExceptions.NETWORK_EXCEPTIONS.contains(it.javaClass)) {
                        viewState.showErrorSnackbar("Отсутствует подключение к интернету \nПодключитесь к сети и попробуйте снова")
                    }else {
                        viewState.showErrorSnackbar("Вы ввели неверные данные.\nПопробуйте еще раз")
                    }
                })
        }
    }

    private fun saveUserData(user: User) {
        
    }

    private fun checkFields(login: String, password: String): Boolean {
        if (login.isEmpty() || password.isEmpty()) {
            val messageError = "Поля не должны быть пустыми"
            if (login.isEmpty() && password.isEmpty()) {
                viewState.showMessageErrorInputField(EmptyFields.ALL, messageError)
            } else {
                if (login.isEmpty()) {
                    viewState.showMessageErrorInputField(EmptyFields.LOGIN, messageError)
                } else {
                    viewState.showMessageErrorInputField(EmptyFields.PASSWORD, messageError)
                }
            }
            return false
        }
        if (password.length < LENGTH_PASSWORD) {
            viewState.showMessageErrorInputField(
                EmptyFields.PASSWORD,
                "Пароль должен быть более чем из $LENGTH_PASSWORD символов"
            )
            return false
        }

        return true
    }
}
