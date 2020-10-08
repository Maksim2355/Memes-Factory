package com.lumi.surfeducationproject.views

import com.lumi.surfeducationproject.enums.EmptyFields
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface AuthView: MvpView {

    fun showPassword()

    fun showProgressBar()

    fun showMessageErrorInputField(emptyFields: EmptyFields, messageError: String)

    fun showPasswordHelper(lengthPassword: Int)

    fun hidePassword()

    fun showErrorSnackbar()

    fun hidePasswordHelper()

    fun hideProgressBar()

    fun enableIconEye()

    fun disableIconEye()


}