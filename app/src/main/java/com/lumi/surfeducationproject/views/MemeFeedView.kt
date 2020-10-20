package com.lumi.surfeducationproject.views

import com.lumi.surfeducationproject.data.model.Meme
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import moxy.viewstate.strategy.alias.Skip

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface MemeFeedView: MvpView {

    fun showMemes(memesList: List<Meme>)

    fun showErrorState()

    fun showRefresh()

    fun hideRefresh()

    fun showLoadState()

    fun hideLoadState()

    fun showErrorSnackbar(message: String)

    @Skip
    fun openMemeDetails(data: Meme)

}