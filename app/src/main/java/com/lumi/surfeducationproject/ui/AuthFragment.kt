package com.lumi.surfeducationproject.ui

import android.content.Context
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import com.lumi.surfeducationproject.App
import com.lumi.surfeducationproject.R
import com.lumi.surfeducationproject.common.base_view.BaseFragment
import com.lumi.surfeducationproject.common.EmptyFields
import com.lumi.surfeducationproject.common.managers.SnackBarManager
import com.lumi.surfeducationproject.navigation.NavigationContent
import com.lumi.surfeducationproject.presenters.AuthPresenter
import com.lumi.surfeducationproject.views.AuthView
import moxy.ktx.moxyPresenter
import studio.carbonylgroup.textfieldboxes.ExtendedEditText
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes
import javax.inject.Inject
import javax.inject.Provider


class AuthFragment : BaseFragment(), AuthView, View.OnFocusChangeListener {

    @Inject
    lateinit var presenterProvider: Provider<AuthPresenter>
    private val presenter by moxyPresenter {
        presenterProvider.get()
    }

    @Inject
    lateinit var snackBarManager: SnackBarManager

    private lateinit var loginInputTfb: TextFieldBoxes
    private lateinit var loginEditEt: ExtendedEditText

    private lateinit var passwordInputTfb: TextFieldBoxes
    private lateinit var passwordEt: ExtendedEditText

    private lateinit var authUserBtn: TextView;
    private lateinit var authPb: ProgressBar;

    private var isPasswordVisible: Boolean = false
    private var isEnablePasswordBtnVisible: Boolean = false

    @Inject
    lateinit var navigation: NavigationContent

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.instance.getFragmentAuthComponentOrCreateIfNull().inject(this)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_auth, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginInputTfb = view.findViewById(R.id.input_login_fb)
        loginEditEt = view.findViewById(R.id.login_input_et)
        passwordInputTfb = view.findViewById(R.id.input_password_fb)
        passwordEt = view.findViewById(R.id.password_et)
        passwordEt.onFocusChangeListener = this
        authUserBtn = view.findViewById(R.id.auth_user_btn)
        authUserBtn.setOnClickListener { authUser() }
        authPb = view.findViewById(R.id.auth_pb)
    }

    private fun authUser() {
        presenter.authUser(getInputLogin(), getInputPassword())
    }

    override fun enableIconEye() {
        isEnablePasswordBtnVisible = true
        if (isPasswordVisible) {
            passwordInputTfb.setEndIcon(R.drawable.ic_eye_on)
        } else {
            passwordInputTfb.setEndIcon(R.drawable.ic_eye_off)
        }
        passwordInputTfb.endIconImageButton.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            if (isPasswordVisible) {
                presenter.enableVisiblePassword()
            } else {
                presenter.disableVisiblePassword()
            }
        }
    }

    override fun disableIconEye() {
        isEnablePasswordBtnVisible = false
        passwordInputTfb.removeEndIcon()
    }

    override fun openContentFragment() {
        navigation.startContentScreen();
    }

    override fun showPassword() {
        passwordInputTfb.setEndIcon(R.drawable.ic_eye_on)
        passwordEt.transformationMethod = HideReturnsTransformationMethod.getInstance()
    }

    override fun hidePassword() {
        passwordInputTfb.setEndIcon(R.drawable.ic_eye_off)
        passwordEt.transformationMethod = PasswordTransformationMethod.getInstance()
    }

    override fun showErrorSnackbar(messageError: String) {
        snackBarManager.showErrorMessage(messageError)
    }

    override fun showMessageErrorInputField(emptyFields: EmptyFields, messageError: String) {
        when (emptyFields) {
            EmptyFields.LOGIN -> {
                loginInputTfb.setError(messageError, false)
            }
            EmptyFields.PASSWORD -> {
                passwordInputTfb.setError(messageError, false)
            }
            EmptyFields.ALL -> {
                loginInputTfb.setError(messageError, false)
                passwordInputTfb.setError(messageError, false)
            }
        }
    }

    override fun showPasswordHelper(lengthPassword: Int) {
        passwordInputTfb.helperText = "Длина пароля должна состоять из $lengthPassword символов"
    }

    override fun hidePasswordHelper() {
        passwordInputTfb.helperText = null
    }

    override fun showProgressBar() {
        authUserBtn.visibility = View.GONE
        authPb.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        authUserBtn.visibility = View.VISIBLE
        authPb.visibility = View.GONE
    }

    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        v?.let {
            when (v.id) {
                R.id.password_et -> {
                    if (hasFocus) {
                        presenter.enableCheckPasswordField()
                    } else {
                        presenter.disableCheckPasswordField(getInputPassword())
                    }
                }
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        App.instance.clearFragmentAuthComponent()
    }

    private fun getInputPassword() = passwordEt.text.toString()

    private fun getInputLogin() = loginEditEt.text.toString()

    override fun getActionBar(): ActionBar? = null

}