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
import com.lumi.surfeducationproject.App
import com.lumi.surfeducationproject.R
import com.lumi.surfeducationproject.common.EmptyFields
import com.lumi.surfeducationproject.common.SnackBarManager
import com.lumi.surfeducationproject.navigation.NavigationContent
import com.lumi.surfeducationproject.presenters.AuthPresenter
import com.lumi.surfeducationproject.views.AuthView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import studio.carbonylgroup.textfieldboxes.ExtendedEditText
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes
import javax.inject.Inject
import javax.inject.Provider


class AuthFragment : MvpAppCompatFragment(), AuthView, View.OnFocusChangeListener {


    @Inject
    lateinit var presenterProvider: Provider<AuthPresenter>
    private val presenter by moxyPresenter {
        presenterProvider.get()
    }

    @Inject
    lateinit var snackBarManager: SnackBarManager

    private lateinit var loginInputField: TextFieldBoxes
    private lateinit var loginEditText: ExtendedEditText

    private lateinit var passwordInputField: TextFieldBoxes
    private lateinit var passwordEditText: ExtendedEditText

    private lateinit var authUserBtn: TextView;
    private lateinit var progressAuth: ProgressBar;

    private var isPasswordVisible: Boolean = false
    private var isEnablePasswordBtnVisible: Boolean = false

    @Inject
    lateinit var navigation: NavigationContent

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.instance.startFragmentComponent().inject(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_auth, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginInputField = view.findViewById(R.id.login_field_boxes)
        loginEditText = view.findViewById(R.id.login_edit_text)

        passwordInputField = view.findViewById(R.id.password_field_boxes)
        passwordEditText = view.findViewById(R.id.password_edit_text)
        passwordEditText.onFocusChangeListener = this

        authUserBtn = view.findViewById(R.id.auth_user_btn)
        authUserBtn.setOnClickListener { authUser() }

        progressAuth = view.findViewById(R.id.progress_auth)
    }

    private fun authUser() {
        presenter.authUser(getInputLogin(), getInputPassword())
    }

    override fun enableIconEye() {
        isEnablePasswordBtnVisible = true
        if (isPasswordVisible) {
            passwordInputField.setEndIcon(R.drawable.ic_eye_on)
        } else {
            passwordInputField.setEndIcon(R.drawable.ic_eye_off)
        }
        passwordInputField.endIconImageButton.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            if (isPasswordVisible) {
                passwordInputField.setEndIcon(R.drawable.ic_eye_on)
                showPassword()
            } else {
                passwordInputField.setEndIcon(R.drawable.ic_eye_off)
                hidePassword()
            }
        }
    }

    override fun disableIconEye() {
        isEnablePasswordBtnVisible = false
        passwordInputField.removeEndIcon()
    }

    override fun openContentFragment() {
        navigation.startContentScreen();
    }

    override fun showPassword() {
        passwordEditText.transformationMethod = HideReturnsTransformationMethod.getInstance();
    }

    override fun hidePassword() {
        passwordEditText.transformationMethod = PasswordTransformationMethod.getInstance();
    }

    override fun showErrorSnackbar(messageError: String) {
        snackBarManager.showErrorMessage(messageError)
    }

    override fun showMessageErrorInputField(emptyFields: EmptyFields, messageError: String) {
        when (emptyFields) {
            EmptyFields.LOGIN -> {
                loginInputField.setError(messageError, false)
            }
            EmptyFields.PASSWORD -> {
                passwordInputField.setError(messageError, false)
            }
            EmptyFields.ALL -> {
                loginInputField.setError(messageError, false)
                passwordInputField.setError(messageError, false)
            }
        }
    }

    override fun showPasswordHelper(lengthPassword: Int) {
        passwordInputField.helperText = "Длина пароля должна состоять из $lengthPassword символов"
    }

    override fun hidePasswordHelper() {
        passwordInputField.helperText = null
    }

    override fun showProgressBar() {
        authUserBtn.visibility = View.GONE
        progressAuth.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        authUserBtn.visibility = View.VISIBLE
        progressAuth.visibility = View.GONE
    }

    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        v?.let {
            when (v.id) {
                R.id.password_edit_text -> {
                    if (hasFocus) {
                        presenter.enableCheckPasswordField()
                    } else {
                        presenter.disableCheckPasswordField(getInputPassword())
                    }
                }
            }
        }
    }

    private fun getInputPassword() = passwordEditText.text.toString()
    private fun getInputLogin() = loginEditText.text.toString()


    override fun onDetach() {
        super.onDetach()
        App.instance.clearFragmentComponent()
    }

}