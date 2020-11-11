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


class AuthFragment : BaseFragment(), AuthView {

    @Inject
    lateinit var presenterProvider: Provider<AuthPresenter>
    private val presenter by moxyPresenter {
        presenterProvider.get()
    }

    private lateinit var loginInputTfb: TextFieldBoxes
    private lateinit var loginEditEt: ExtendedEditText
    private lateinit var passwordInputTfb: TextFieldBoxes
    private lateinit var passwordEt: ExtendedEditText
    private lateinit var authUserBtn: TextView;
    private lateinit var authPb: ProgressBar;

    @Inject
    lateinit var snackBarManager: SnackBarManager

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
        loginInputTfb.setSimpleTextChangeWatcher { theNewText, isError ->
            presenter.updateLogin(theNewText)
        }

        passwordInputTfb = view.findViewById(R.id.input_password_fb)
        passwordInputTfb.setSimpleTextChangeWatcher { theNewText, isError ->
            presenter.updatePassword(theNewText)
        }
        passwordEt = view.findViewById(R.id.password_et)
        passwordEt.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus){
                presenter.enableCheckPasswordField()
            }else{
                presenter.disableCheckPasswordField()
            }
        }

        authUserBtn = view.findViewById(R.id.auth_user_btn)
        authUserBtn.setOnClickListener { authUser() }
        authPb = view.findViewById(R.id.auth_pb)
        passwordInputTfb.endIconImageButton.setOnClickListener {
            presenter.changeVisiblePassword()
        }
    }

    private fun authUser() {
        presenter.authUser()
    }

    override fun enablePasswordField(isPasswordVisible: Boolean) {
        if (isPasswordVisible) {
            passwordInputTfb.setEndIcon(R.drawable.ic_eye_on)
        } else {
            passwordInputTfb.setEndIcon(R.drawable.ic_eye_off)
        }
    }

    override fun disablePasswordField() {
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

    override fun showErrorSnackBar(messageError: String) {
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

    override fun hideMessageErrorInputField(emptyFields: EmptyFields) {
        when (emptyFields) {
            EmptyFields.LOGIN -> {
                loginInputTfb.removeError()
            }
            EmptyFields.PASSWORD -> {
                passwordInputTfb.removeError()
            }
            EmptyFields.ALL -> {
                loginInputTfb.removeError()
                passwordInputTfb.removeError()
            }
        }
    }

    override fun showPasswordHelper(lengthPassword: Int) {
        passwordInputTfb.helperText = "Длина пароля должна состоять из $lengthPassword символов"
    }

    override fun hidePasswordHelper() {
        passwordInputTfb.helperText = null
    }

    override fun showAuthProgressBar() {
        authUserBtn.visibility = View.GONE
        authPb.visibility = View.VISIBLE
    }

    override fun hideAuthProgressBar() {
        authUserBtn.visibility = View.VISIBLE
        authPb.visibility = View.GONE
    }

    override fun onDetach() {
        super.onDetach()
        App.instance.clearFragmentAuthComponent()
    }

    override fun getActionBar(): ActionBar? = null

}