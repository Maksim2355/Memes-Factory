package com.lumi.surfeducationproject.views

import com.lumi.surfeducationproject.common.EmptyFields
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import moxy.viewstate.strategy.alias.AddToEnd
import moxy.viewstate.strategy.alias.OneExecution
import moxy.viewstate.strategy.alias.Skip

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface AuthView: MvpView {

    @AddToEnd
    fun showPassword()

    @AddToEnd
    fun hidePassword()

    @AddToEnd
    fun showProgressBar()

    @AddToEnd
    fun showMessageErrorInputField(emptyFields: EmptyFields, messageError: String)

    @AddToEnd
    fun showPasswordHelper(lengthPassword: Int)

    @OneExecution
    fun showErrorSnackbar(messageError: String)

    @AddToEnd
    fun hidePasswordHelper()

    fun hideProgressBar()

    fun enableIconEye()

    fun disableIconEye()

    @Skip
    fun openContentFragment()


}