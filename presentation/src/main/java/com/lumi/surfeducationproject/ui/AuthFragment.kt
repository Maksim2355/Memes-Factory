package com.lumi.surfeducationproject.ui

import android.content.Context
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import com.lumi.surfeducationproject.App
import com.lumi.surfeducationproject.R
import com.lumi.surfeducationproject.common.base_view.BaseFragment
import com.lumi.surfeducationproject.common.StateFields
import com.lumi.surfeducationproject.navigation.NavigationContent
import com.lumi.surfeducationproject.presenters.AuthPresenter
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_auth.*
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider


class AuthFragment : DaggerFragment(), AuthView {

    @Inject
    lateinit var navigation: NavigationContent

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_auth, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        input_login_fb.setSimpleTextChangeWatcher { theNewText, _ ->
            presenter.updateLogin(theNewText)
        }

        input_password_fb.setSimpleTextChangeWatcher { theNewText, _ ->
            presenter.updatePassword(theNewText)
        }

        password_et.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                presenter.enableCheckPasswordField()
            } else {
                presenter.disableCheckPasswordField()
            }
        }

        auth_user_btn.setOnClickListener { authUser() }
        input_password_fb.endIconImageButton.setOnClickListener {
            presenter.changeVisiblePassword()
        }
    }

    private fun authUser() {
        presenter.authUser()
    }

    override fun enablePasswordField(isPasswordVisible: Boolean) {
        if (isPasswordVisible) {
            input_password_fb.setEndIcon(R.drawable.ic_eye_on)
        } else {
            input_password_fb.setEndIcon(R.drawable.ic_eye_off)
        }
    }

    override fun disablePasswordField() {
        input_password_fb.removeEndIcon()
    }

    override fun openContentFragment() {
        navigation.startContentScreen();
    }

    override fun showPassword() {
        input_password_fb.setEndIcon(R.drawable.ic_eye_on)
        password_et.transformationMethod = HideReturnsTransformationMethod.getInstance()
    }

    override fun hidePassword() {
        input_password_fb.setEndIcon(R.drawable.ic_eye_off)
        password_et.transformationMethod = PasswordTransformationMethod.getInstance()
    }

    override fun showErrorSnackBar(messageError: String) {
        snackBarManager.showErrorMessage(messageError)
    }

    override fun showMessageErrorInputField(stateFields: StateFields, messageError: String) {
        when (stateFields) {
            StateFields.LOGIN -> {
                input_login_fb.setError(messageError, false)
            }
            StateFields.PASSWORD -> {
                input_password_fb.setError(messageError, false)
            }
            StateFields.ALL -> {
                input_login_fb.setError(messageError, false)
                input_password_fb.setError(messageError, false)
            }
        }
    }

    override fun hideMessageErrorInputField(stateFields: StateFields) {
        when (stateFields) {
            StateFields.LOGIN -> {
                input_login_fb.removeError()
            }
            StateFields.PASSWORD -> {
                input_password_fb.removeError()
            }
            StateFields.ALL -> {
                input_login_fb.removeError()
                input_password_fb.removeError()
            }
        }
    }

    override fun showPasswordHelper(lengthPassword: Int) {
        input_password_fb.helperText = "Длина пароля должна состоять из $lengthPassword символов"
    }

    override fun hidePasswordHelper() {
        input_password_fb.helperText = null
    }

    override fun showAuthProgressBar() {
        auth_user_btn.visibility = View.GONE
        auth_pb.visibility = View.VISIBLE
    }

    override fun hideAuthProgressBar() {
        auth_user_btn.visibility = View.VISIBLE
        auth_pb.visibility = View.GONE
    }

    override fun getActionBar(): ActionBar? = null

}