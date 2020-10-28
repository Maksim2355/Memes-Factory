package com.lumi.surfeducationproject.presenters

import com.lumi.surfeducationproject.data.repository.MemeRepositoryImpl
import com.lumi.surfeducationproject.domain.model.Meme
import com.lumi.surfeducationproject.domain.repository.MemeRepository
import com.lumi.surfeducationproject.exceptions.NETWORK_EXCEPTIONS
import com.lumi.surfeducationproject.views.MemeFeedView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class MemesFeedPresenter: MvpPresenter<MemeFeedView>() {

    private val memeRepository: MemeRepository = MemeRepositoryImpl()

    fun loadMemes(){
        memeRepository.getMemes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showLoadState() }
            .doFinally { viewState.hideLoadState() }
            .subscribe({
                showMemes(it)
            }, {
                errorProcessing(it)
            })
    }

    fun updateMemes(){
        memeRepository.getMemes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showRefresh() }
            .doFinally { viewState.hideRefresh() }
            .subscribe({
                showMemes(it)
            }, {
                errorProcessing(it)
            })
    }

    private fun showMemes(memeList: List<Meme>){
        viewState.showMemes(memeList)
    }

    private fun errorProcessing(throwable: Throwable){
        if (NETWORK_EXCEPTIONS.contains(throwable.javaClass)) {
            viewState.showErrorSnackbar("Отсутствует подключение к интернету \nПодключитесь к сети и попробуйте снова")
        }else {
            viewState.showErrorState()
        }
    }

    fun shareMeme(it: Meme) {

    }

    fun openDetails(it: Meme) {
        viewState.openMemeDetails(it)
    }

    fun filterMemeList(searchText: String) {
        viewState.showErrorSnackbar(searchText)
    }


}