package com.lumi.surfeducationproject.views

import com.lumi.surfeducationproject.common.base_view.BaseMemeListView
import com.lumi.surfeducationproject.domain.model.Meme
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType
import moxy.viewstate.strategy.alias.*

@StateStrategyType(value = AddToEndStrategy::class)
interface MemeFeedView: BaseMemeListView {

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
    fun showErrorSnackbar(message: String)

}
