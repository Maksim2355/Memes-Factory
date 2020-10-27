package com.lumi.surfeducationproject.presenters

import com.lumi.surfeducationproject.exceptions.NetworkExceptions
import com.lumi.surfeducationproject.data.dto.MemDto
import com.lumi.surfeducationproject.data.services.network.NetworkServiceImpl
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

    private fun showMemes(memDtos: List<MemDto>){
        viewState.showMemes(memDtos)
    }

    private fun errorProcessing(throwable: Throwable){
        if (NetworkExceptions.NETWORK_EXCEPTIONS.contains(throwable.javaClass)) {
            viewState.showErrorSnackbar("Отсутствует подключение к интернету \nПодключитесь к сети и попробуйте снова")
        }else {
            viewState.showErrorState()
        }
    }

    fun shareMeme(it: MemDto) {

    }

    fun openDetails(it: MemDto) {
        viewState.openMemeDetails(it)
    }

    fun filterMemeList(searchText: String) {
        viewState.showErrorSnackbar(searchText)
    }


}