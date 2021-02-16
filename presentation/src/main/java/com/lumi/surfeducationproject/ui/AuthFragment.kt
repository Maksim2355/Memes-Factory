package com.lumi.surfeducationproject.ui

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.lumi.surfeducationproject.R
import com.lumi.surfeducationproject.common.EmptyFields
import com.lumi.surfeducationproject.common.FocusAuthFields
import com.lumi.surfeducationproject.databinding.FragmentAuthBinding
import com.lumi.surfeducationproject.di.injection_extension.injectViewModel
import com.lumi.surfeducationproject.di.named.ACTIVITY_NAVIGATION
import com.lumi.surfeducationproject.navigation.NavigationDestination
import com.lumi.surfeducationproject.ui.extension.activity_extension.showSnackBarMessage
import com.lumi.surfeducationproject.vm.AuthViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject
import javax.inject.Named


class AuthFragment : DaggerFragment() {

    companion object {
        const val LENGTH_PASSWORD = 6
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var authViewModel: AuthViewModel

    private lateinit var binding: FragmentAuthBinding

    @Inject
    @Named(ACTIVITY_NAVIGATION)
    lateinit var navigationDestination: NavigationDestination

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthBinding.inflate(inflater, container, false)
        authViewModel = injectViewModel(viewModelFactory)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeViews()
    }

    private fun initView() {
        with(binding) {
            inputLoginFb.setSimpleTextChangeWatcher { theNewText, _ ->
                authViewModel.updateLogin(theNewText)
            }
            inputPasswordFb.setSimpleTextChangeWatcher { theNewText, _ ->
                authViewModel.updatePassword(theNewText)
            }
            passwordEt.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    authViewModel.changeFocus(FocusAuthFields.PASSWORD)
                } else {
                    authViewModel.changeFocus(null)
                }
            }
            inputPasswordFb.endIconImageButton.setOnClickListener {
                authViewModel.changePasswordVisible()
            }
            authUserBtn.setOnClickListener { authViewModel.authUser() }
        }

    }

    private fun observeViews() {
        with(authViewModel) {
            login.observe(viewLifecycleOwner) {
                binding.loginInputEt.setText(it)
            }
            password.observe(viewLifecycleOwner) {
                binding.passwordEt.setText(it)
            }
            isLoadingAuthorizationUser.observe(viewLifecycleOwner) {
                if (it) {
                    binding.authUserBtn.visibility = View.GONE
                    binding.authPb.visibility = View.VISIBLE
                } else {
                    binding.authPb.visibility = View.GONE
                    binding.authUserBtn.visibility = View.VISIBLE
                }
            }
            isShowPasswordHelper.observe(viewLifecycleOwner) {
                if (it) {
                    binding.inputPasswordFb.helperText =
                        "Длина пароля должна состоять из $LENGTH_PASSWORD символов"
                } else {
                    binding.inputPasswordFb.helperText = null
                }
            }
            messageStateField.observe(viewLifecycleOwner) {
                if (it != null) {
                    when (it) {
                        EmptyFields.ALL -> {
                            binding.inputPasswordFb.setError(
                                "Поля не должны быть пустыми",
                                false
                            )
                            binding.inputLoginFb.setError(
                                "Поля не должны быть пустыми",
                                false
                            )
                        }
                        EmptyFields.LOGIN -> {
                            binding.inputLoginFb.setError(
                                "Поля не должны быть пустыми",
                                false
                            )
                        }
                        EmptyFields.PASSWORD -> {
                            binding.inputPasswordFb.setError(
                                "Поля не должны быть пустыми",
                                false
                            )
                        }
                    }
                } else {
                    binding.inputPasswordFb.removeError()
                    binding.inputLoginFb.removeError()
                }
            }
            isPasswordVisibleBtnActive.observe(viewLifecycleOwner) {
                if (it) {
                    binding.inputPasswordFb.setEndIcon(R.drawable.ic_eye_on)
                } else {
                    binding.inputPasswordFb.setEndIcon(R.drawable.ic_eye_off)
                }
            }
            isPasswordVisibleText.observe(viewLifecycleOwner) {
                if (it) {
                    binding.passwordEt.transformationMethod =
                        PasswordTransformationMethod.getInstance()
                } else {
                    binding.passwordEt.transformationMethod =
                        HideReturnsTransformationMethod.getInstance()
                }
            }
            showErrorSnackBar.observe(viewLifecycleOwner) {
                it.getContentIfNotHandled()
                    ?.let { message ->
                        requireActivity().showSnackBarMessage(
                            message,
                            binding.rootLayoutAuth
                        )
                    }
            }
            focusFiled.observe(viewLifecycleOwner) {
                when (it) {
                    FocusAuthFields.PASSWORD -> {
                        binding.passwordEt.isFocusableInTouchMode = true
                    }
                    FocusAuthFields.LOGIN -> {
                        binding.loginInputEt.isFocusableInTouchMode = true
                    }
                }
            }
            navigateToContentTab.observe(viewLifecycleOwner) {
                it.getContentIfNotHandled()?.let { it1 -> navigationDestination.navigateTo(it1) }
            }
        }
    }

}