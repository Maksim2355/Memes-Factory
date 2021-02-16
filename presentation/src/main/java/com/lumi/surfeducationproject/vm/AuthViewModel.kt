package com.lumi.surfeducationproject.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.AuthorizationData
import com.example.domain.repository.UserRepository
import com.lumi.surfeducationproject.common.EmptyFields
import com.lumi.surfeducationproject.common.Event
import com.lumi.surfeducationproject.common.FocusAuthFields
import com.lumi.surfeducationproject.common.exceptions.NETWORK_EXCEPTIONS
import com.lumi.surfeducationproject.navigation.navigation.ActionRoute
import com.lumi.surfeducationproject.navigation.navigation.Route
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    companion object {
        const val LENGTH_PASSWORD = 6
    }

    private val _login: MutableLiveData<String> = MutableLiveData("")
    val login: LiveData<String>
        get() = _login

    private val _password: MutableLiveData<String> = MutableLiveData("")
    val password: LiveData<String>
        get() = _password

    private val _isLoadingAuthorizationUser: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoadingAuthorizationUser: LiveData<Boolean>
        get() = _isLoadingAuthorizationUser

    private val _isShowPasswordHelper: MutableLiveData<Boolean> = MutableLiveData(false)
    val isShowPasswordHelper: LiveData<Boolean>
        get() = _isShowPasswordHelper

    private val _messageStateField: MutableLiveData<EmptyFields> = MutableLiveData(null)
    val messageStateField: LiveData<EmptyFields?>
        get() = _messageStateField

    private val _isPasswordBtnActive: MutableLiveData<Boolean> = MutableLiveData(false)
    val isPasswordVisibleBtnActive: LiveData<Boolean>
        get() = _isPasswordBtnActive

    private val _isPasswordVisibleText: MutableLiveData<Boolean> = MutableLiveData(false)
    val isPasswordVisibleText: LiveData<Boolean>
        get() = _isPasswordVisibleText

    private val _showErrorSnackBar: MutableLiveData<Event<String>> = MutableLiveData()
    val showErrorSnackBar: LiveData<Event<String>>
        get() = _showErrorSnackBar

    private val _focusFiled: MutableLiveData<FocusAuthFields> = MutableLiveData()
    val focusFiled: LiveData<FocusAuthFields>
        get() = _focusFiled

    private val _navigateToContentTab: MutableLiveData<Event<Route>> = MutableLiveData()
    val navigateToContentTab: LiveData<Event<Route>>
        get() = _navigateToContentTab

    fun navigateToContentScreen() {
        _navigateToContentTab.value = Event(
            Route(
                actionRoute = ActionRoute.AUTH_FRAGMENT_TO_TAB_FRAGMENT
            )
        )
    }

    fun changePasswordVisible(){
        val isPasswordVisible: Boolean = _isPasswordVisibleText.value ?: false
        _isPasswordVisibleText.value = !isPasswordVisible
    }

    fun updateLogin(loginUser: String) {
        _login.value = loginUser
        checkAndUpdateEmptyField()
    }

    fun updatePassword(passwordUser: String) {
        _password.value = passwordUser
        checkAndUpdateEmptyField()
        _isShowPasswordHelper.value = passwordUser.length < LENGTH_PASSWORD
    }

    fun changeFocus(stateFields: FocusAuthFields?) {
        when (stateFields) {
            FocusAuthFields.LOGIN -> {
                _focusFiled.value = FocusAuthFields.LOGIN
            }
            FocusAuthFields.PASSWORD -> {
                _focusFiled.value = FocusAuthFields.PASSWORD
                _isPasswordBtnActive.value = true
            }
            else -> {
                _focusFiled.value = null
            }
        }
        if (_focusFiled.value != FocusAuthFields.PASSWORD && (_password.value ?: "").isEmpty()) {
            _isPasswordBtnActive.value = false
        }
    }

    fun authUser() {
        val loginUser = _login.value ?: ""
        val passwordUser = _password.value ?: ""
        if (loginUser.isNotEmpty() && passwordUser.isNotEmpty()) {
            val user = AuthorizationData(loginUser, passwordUser)
            userRepository.getUser(user)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    _isLoadingAuthorizationUser.value = true
                }
                .doFinally {
                    _isLoadingAuthorizationUser.value = false
                }
                .subscribe({
                    //Тут переходим на другой фрагмент
                }, {
                    if (NETWORK_EXCEPTIONS.contains(it.javaClass)) {
                        _showErrorSnackBar.value = Event(
                            "Отсутствует подключение к интернету " +
                                    "\nПодключитесь к сети и попробуйте снова"
                        )
                    } else {
                        _showErrorSnackBar.value = Event(
                            "Вы ввели неверные данные." +
                                    "\nПопробуйте еще раз"
                        )
                    }
                })
        } else {
            when {
                loginUser.isEmpty() && passwordUser.isEmpty() -> {
                    _messageStateField.value = EmptyFields.ALL
                }
                loginUser.isEmpty() -> {
                    _messageStateField.value = EmptyFields.LOGIN
                }
                passwordUser.isEmpty() -> {
                    _messageStateField.value = EmptyFields.PASSWORD
                }
            }
        }
    }

    private fun checkAndUpdateEmptyField() {
        val loginUser = _login.value ?: ""
        val passwordUser = _password.value ?: ""
        when {
            loginUser.isEmpty() && passwordUser.isEmpty() -> {
                _messageStateField.value = EmptyFields.ALL
            }
            loginUser.isEmpty() -> {
                _messageStateField.value = EmptyFields.LOGIN
            }
            passwordUser.isEmpty() -> {
                _messageStateField.value = EmptyFields.PASSWORD
            }
            else -> _messageStateField.value = null
        }
    }


}