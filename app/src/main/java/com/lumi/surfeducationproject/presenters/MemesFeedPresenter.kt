package com.lumi.surfeducationproject.presenters

import com.lumi.surfeducationproject.Utils.Exceptions
import com.lumi.surfeducationproject.data.model.Meme
import com.lumi.surfeducationproject.services.network.NetworkServiceImpl
import com.lumi.surfeducationproject.views.MemeFeedView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class MemesFeedPresenter: MvpPresenter<MemeFeedView>() {

    fun loadMemes(){
        NetworkServiceImpl.getApi().getMemes()
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
        NetworkServiceImpl.getApi().getMemes()
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

    private fun showMemes(memes: List<Meme>){
        viewState.showMemes(memes)
    }

    private fun errorProcessing(throwable: Throwable){
        if (Exceptions.NETWORK_EXCEPTIONS.contains(throwable.javaClass)) {
            viewState.showErrorSnackbar("Отсутствует подключение к интернету \nПодключитесь к сети и попробуйте снова")
        }else {
            viewState.showErrorState()
        }
    }


}