package com.lumi.surfeducationproject.presenters

import com.lumi.surfeducationproject.common.base_view.BasePresenter
import com.lumi.surfeducationproject.domain.model.Meme
import com.lumi.surfeducationproject.domain.repository.UserRepository
import com.lumi.surfeducationproject.views.MemeDetailsView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class MemeDetailsPresenter @Inject constructor(
    private val userRepository: UserRepository
) : BasePresenter<MemeDetailsView>() {

    var meme: Meme? = null

    fun bindUserInfoToolbar() {
        userRepository.getUser()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it?.let {
                    viewState.showUserInfoToolbar(it)
                }
            }, {
                viewState.showErrorStateUserInfoToolbar()
            })
    }

    fun bindMeme() {
        meme?.let {
            viewState.showMeme(it)
        }
    }

    fun shareMeme() {
        meme?.let {
            viewState.shareMeme(it)
        }
    }


}