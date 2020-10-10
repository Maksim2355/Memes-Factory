package com.lumi.surfeducationproject.ui

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.text.InputType
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.lumi.surfeducationproject.R
import com.lumi.surfeducationproject.enums.EmptyFields
import com.lumi.surfeducationproject.presenters.AuthPresenter
import com.lumi.surfeducationproject.views.AuthView
import kotlinx.android.synthetic.main.fragment_auth.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import studio.carbonylgroup.textfieldboxes.ExtendedEditText
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes


class AuthFragment : MvpAppCompatFragment(), AuthView, View.OnFocusChangeListener {

    private val presenter by moxyPresenter { AuthPresenter() }

    private lateinit var loginInputField: TextFieldBoxes
    private lateinit var loginEditText: ExtendedEditText

    private lateinit var passwordInputField: TextFieldBoxes
    private lateinit var passwordEditText: ExtendedEditText

    private lateinit var authUserBtn: TextView;
    private lateinit var progressAuth: ProgressBar;

    private var isPasswordVisible: Boolean = false
    private var isEnablePasswordBtnVisible: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { return inflater.inflate(R.layout.fragment_auth, container, false) }


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
        if (isPasswordVisible){
            passwordInputField.setEndIcon(R.drawable.ic_eye_on)
        }else {
            passwordInputField.setEndIcon(R.drawable.ic_eye_off)
        }
        passwordInputField.endIconImageButton.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            if (isPasswordVisible){
                passwordInputField.setEndIcon(R.drawable.ic_eye_on)
                showPassword()
            }else{
                passwordInputField.setEndIcon(R.drawable.ic_eye_off)
                hidePassword()
            }
        }
    }

    override fun disableIconEye() {
        isEnablePasswordBtnVisible = false
        passwordInputField.removeEndIcon()
    }

    override fun showPassword() {
        passwordEditText.transformationMethod = HideReturnsTransformationMethod.getInstance();
    }

    override fun hidePassword() {
        passwordEditText.transformationMethod = PasswordTransformationMethod.getInstance();
    }

    override fun showErrorSnackbar(messageError: String) {
        val snackbar = Snackbar.make(root_layout, messageError,
            Snackbar.LENGTH_LONG)
        val snackbarView = snackbar.view
        snackbarView.setBackgroundColor(Color.parseColor("#FF575D"))
        val textView =
            snackbarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
        textView.setTextColor(Color.WHITE)
        textView.textSize = 16f
        snackbar.show()
    }

    override fun showMessageErrorInputField(emptyFields: EmptyFields, messageError: String) {
        when(emptyFields){
            EmptyFields.LOGIN -> {
                loginInputField.setError(messageError, false)
            }
            EmptyFields.PASSWORD-> {
                passwordInputField.setError(messageError, false)
            }
            EmptyFields.ALL-> {
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
            when(v.id){
                R.id.password_edit_text -> {
                    if (hasFocus){
                        presenter.enableCheckPasswordField()
                    }else{
                        presenter.disableCheckPasswordField(getInputPassword())
                    }
                }
            }
        }
    }

    private fun getInputPassword() = passwordEditText.text.toString()
    private fun getInputLogin() = loginEditText.text.toString()

}