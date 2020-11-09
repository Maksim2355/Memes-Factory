package com.lumi.surfeducationproject.presenters

import com.lumi.surfeducationproject.common.base_view.BasePresenter
import com.lumi.surfeducationproject.common.EmptyFields
import com.lumi.surfeducationproject.data.dto.network.LoginUserRequest
import com.lumi.surfeducationproject.domain.repository.UserRepository
import com.lumi.surfeducationproject.common.exceptions.NETWORK_EXCEPTIONS
import com.lumi.surfeducationproject.views.AuthView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class AuthPresenter @Inject constructor(
    private val userRepository: UserRepository
): BasePresenter<AuthView>() {

    companion object{
        private val LENGTH_PASSWORD = 6
    }


    //Авторизуем юзера
    fun authUser(login: String, password: String) {
        if (checkFields(login, password)) {
            val userAuth = LoginUserRequest(login, password)
            compositeDisposable.add(userRepository.getUser(userAuth)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { viewState.showProgressBar() }
                .doFinally { viewState.hideProgressBar() }
                .subscribe({
                    viewState.openContentFragment()
                },{
                    if (NETWORK_EXCEPTIONS.contains(it.javaClass)) {
                        viewState.showErrorSnackbar("Отсутствует подключение к интернету \nПодключитесь к сети и попробуйте снова")
                    }else {
                        viewState.showErrorSnackbar("Вы ввели неверные данные.\nПопробуйте еще раз")
                    }
                })
            )

        }
    }

    fun enableCheckPasswordField() {
        viewState.showPasswordHelper(LENGTH_PASSWORD)
        viewState.enableIconEye()
    }


    fun disableCheckPasswordField(password: String) {
        if (password.isEmpty()) viewState.disableIconEye()
        if (password.length >= LENGTH_PASSWORD) viewState.hidePasswordHelper()
    }

    //Проверяем наши введенные данные
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
