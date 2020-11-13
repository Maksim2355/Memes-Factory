package com.lumi.surfeducationproject.views

import com.lumi.surfeducationproject.domain.model.Meme
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType
import moxy.viewstate.strategy.alias.*

@StateStrategyType(value = AddToEndStrategy::class)
interface MemeFeedView: MvpView {

    fun showMemes(memesList: List<Meme>)

    fun showErrorState()

    fun showEmptyFilterErrorState()

    @Skip
    fun showRefresh()

    @Skip
    fun hideRefresh()

    fun enableSearchView()

    @SingleState
    fun disableSearchView()

    @Skip
    fun showLoadState()

    @Skip
    fun hideLoadState()

    @Skip
    fun showErrorSnackbar(message: String)

    @Skip
    fun openMemeDetails(data: Meme)

}