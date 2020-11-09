package com.lumi.surfeducationproject.presenters

import com.lumi.surfeducationproject.common.base_view.BasePresenter
import com.lumi.surfeducationproject.domain.model.Meme
import com.lumi.surfeducationproject.domain.repository.MemeRepository
import com.lumi.surfeducationproject.domain.repository.UserRepository
import com.lumi.surfeducationproject.views.MemeDetailsView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MemeDetailsPresenter @Inject constructor(
    private val userRepository: UserRepository,
    private val memeRepository: MemeRepository
) : BasePresenter<MemeDetailsView>() {

    var meme: Meme? = null

    fun initProfile() {
        compositeDisposable.add(
            userRepository.getUser().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it?.let {
                        viewState.showUserInfoToolbar(it)
                    }
                }, {
                    viewState.showErrorStateUserInfoToolbar()
                })
        )

    }

    fun initMemes() {
        meme?.let {
            viewState.showMemes(it)
        }
    }

    fun share() {
        meme?.let {
            viewState.shareMemes(it)
        }
    }


}