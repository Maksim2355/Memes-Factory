package com.lumi.surfeducationproject.presenters

import android.os.Handler
import com.lumi.surfeducationproject.enums.EmptyFields
import com.lumi.surfeducationproject.views.AuthView
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
            //Todo Обработка сетевого запроса и последующая автризация
            viewState.showErrorSnackbar()
        }
        viewState.hideProgressBar()
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
