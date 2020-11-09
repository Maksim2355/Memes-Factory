package com.lumi.surfeducationproject.views

import com.lumi.surfeducationproject.domain.model.Meme
import moxy.MvpView
import moxy.viewstate.strategy.alias.*

interface MemeFeedView: MvpView {

    @SingleState
    fun showMemes(memesList: List<Meme>)

    @AddToEndSingle
    fun showErrorState()

    @AddToEndSingle
    fun showEmptyFilterErrorState()

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
    fun openMemeDetails(data: Meme)

    @AddToEnd
    fun hideSearch()

}