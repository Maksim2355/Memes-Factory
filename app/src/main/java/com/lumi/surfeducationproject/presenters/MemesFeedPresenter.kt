package com.lumi.surfeducationproject.presenters

import com.lumi.surfeducationproject.common.base_view.BasePresenter
import com.lumi.surfeducationproject.common.exceptions.EmptyMemesDatabaseException
import com.lumi.surfeducationproject.domain.model.Meme
import com.lumi.surfeducationproject.domain.repository.MemeRepository
import com.lumi.surfeducationproject.common.exceptions.NETWORK_EXCEPTIONS
import com.lumi.surfeducationproject.views.MemeFeedView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class MemesFeedPresenter @Inject constructor(
    private val memeRepository: MemeRepository
) : BasePresenter<MemeFeedView>() {

    init {
        loadMemes()
    }

    var observableFilter: Observable<String>? = null
    var filterText = ""
    private var memeList: List<Meme>? = null
    private var memeSearchingList: List<Meme> = ArrayList()


    private fun loadMemes() {
        memeRepository.getMemes()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showLoadState() }
            .doFinally { viewState.hideLoadState() }
            .subscribe({
                showAndSaveMemes(it)
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
                showAndSaveMemes(it)
            }, {
                errorProcessing(it)
            })
    }

    private fun showAndSaveMemes(memeList: List<Meme>) {
        if (memeList.isNotEmpty()) {
            this.memeList = memeList
            viewState.showMemes(memeList)
        } else {
            errorProcessing(EmptyMemesDatabaseException())
        }
    }

    fun shareMeme(meme: Meme) {

    }

    fun openDetails(meme: Meme) {
        viewState.openMemeDetails(meme)
    }

    private fun errorProcessing(throwable: Throwable) {
        println(throwable.javaClass)
        if (NETWORK_EXCEPTIONS.contains(throwable.javaClass)) {
            viewState.showErrorSnackbar("Отсутствует подключение к интернету \nПодключитесь к сети и попробуйте снова")
        }
        viewState.showErrorState()
    }

    fun startFilter() {
        observableFilter?.subscribe({
            //Вполняем фильтрацию мемов
            memeList?.let { memeList ->
                memeSearchingList =
                    memeList.filter { meme -> meme.title.toLowerCase().contains(it.toLowerCase()) }
                if (memeSearchingList.isNotEmpty()) {
                    viewState.showMemes(memeSearchingList)
                } else {
                    viewState.showEmptyFilterErrorState()
                }
            }
        }, {

        })
    }

    fun stopFilter() {
        observableFilter = null

    }


}