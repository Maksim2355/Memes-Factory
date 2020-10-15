package com.lumi.surfeducationproject.views

import moxy.MvpView

interface MemeFeedView: MvpView {

    fun showMemes()

    fun showErrorState()

    fun showRefresh()

    fun hideRefresh()

    fun showLoadState()

    fun hideLoadState()

    fun showErrorSnackbar()


}