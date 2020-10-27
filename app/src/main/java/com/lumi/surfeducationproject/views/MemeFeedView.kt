package com.lumi.surfeducationproject.views

import com.lumi.surfeducationproject.data.dto.network.NetworkMeme
import moxy.MvpView
import moxy.viewstate.strategy.alias.*

interface MemeFeedView: MvpView {

    @SingleState
    fun showMemes(memesList: List<NetworkMeme>)

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
    fun openMemeDetails(data: NetworkMeme)

}