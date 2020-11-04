package com.lumi.surfeducationproject.presenters

import com.lumi.surfeducationproject.data.exceptions.EmptyMemesDatabaseException
import com.lumi.surfeducationproject.domain.model.Meme
import com.lumi.surfeducationproject.domain.repository.MemeRepository
import com.lumi.surfeducationproject.exceptions.NETWORK_EXCEPTIONS
import com.lumi.surfeducationproject.views.MemeFeedView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpPresenter
import javax.inject.Inject

class MemesFeedPresenter @Inject constructor(
    private val memeRepository: MemeRepository
) : MvpPresenter<MemeFeedView>() {

    fun loadMemes() {
        memeRepository.getMemes()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showLoadState() }
            .doFinally { viewState.hideLoadState() }
            .subscribe({
                showMemes(it)
            }, {
                errorProcessing(it)
            })
    }

    fun updateMemes() {
        memeRepository.getMemes()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showRefresh() }
            .doFinally { viewState.hideRefresh() }
            .subscribe({
                println(it)
                showMemes(it)
            }, {
                println(it.javaClass)
                errorProcessing(it)
            })
    }

    private fun showMemes(memeList: List<Meme>) {
        if (memeList.isNotEmpty()){
            viewState.showMemes(memeList)
        }else {
            errorProcessing(EmptyMemesDatabaseException())
        }
    }

    fun shareMeme(meme: Meme) {

    }

    fun openDetails(meme: Meme) {
        viewState.openMemeDetails(meme)
    }

    private fun errorProcessing(throwable: Throwable) {
        if (NETWORK_EXCEPTIONS.contains(throwable.javaClass)) {
            viewState.showErrorSnackbar("Отсутствует подключение к интернету \nПодключитесь к сети и попробуйте снова")
            viewState.showErrorState()
        } else {
            viewState.showErrorState()
        }
    }


}