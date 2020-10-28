package com.lumi.surfeducationproject.views

import com.lumi.surfeducationproject.domain.model.Meme
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution
import moxy.viewstate.strategy.alias.SingleState
import moxy.viewstate.strategy.alias.Skip

interface MemeFeedView: MvpView {

    @SingleState
    fun showMemes(memesList: List<Meme>)

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
    fun openMemeDetails(data: Meme)

}