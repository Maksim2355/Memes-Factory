package com.lumi.surfeducationproject.views

import com.lumi.surfeducationproject.data.dto.MemDto
import moxy.MvpView
import moxy.viewstate.strategy.alias.*

interface MemeFeedView: MvpView {

    @SingleState
    fun showMemes(memesList: List<MemDto>)

    @AddToEndSingle
    fun showErrorState()

    @AddToEndSingle
    fun showRefresh()

    @AddToEndSingle
    fun hideRefresh()

    @AddToEndSingle
    fun showLoadState()

    @AddToEndSingle
    fun hideLoadState()

    @Skip
    fun showErrorSnackbar(message: String)

    @OneExecution
    fun openMemeDetails(data: MemDto)

}