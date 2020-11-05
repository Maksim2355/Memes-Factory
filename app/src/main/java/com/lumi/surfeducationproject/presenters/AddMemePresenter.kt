package com.lumi.surfeducationproject.presenters

import com.lumi.surfeducationproject.common.BasePresenter
import com.lumi.surfeducationproject.domain.model.Meme
import com.lumi.surfeducationproject.domain.repository.MemeRepository
import com.lumi.surfeducationproject.domain.repository.UserRepository
import com.lumi.surfeducationproject.views.AddMemeView
import io.reactivex.rxjava3.core.Observable
import moxy.MvpPresenter
import javax.inject.Inject

class AddMemePresenter @Inject constructor(
    memeRepository: MemeRepository,
    userRepository: UserRepository
) : BasePresenter<AddMemeView>() {

    var observableTitleMeme: Observable<String>? = null
    var observableDescriptionMeme: Observable<String>? = null
    var photoMemeUrl: String? = null

    private val createdDate by lazy { getCreatedData() }


    private fun getCreatedData(): String {
        TODO("Not yet implemented")
    }

    fun addMeme() {
        viewState.showAddImgDialog()
    }

    fun deleteImg() {
        photoMemeUrl = null
        viewState.hideImg()
    }

    //Создание мема из данных и обновления бд
    fun createMeme() {

    }

    private fun checkFieldsAndImg(title: String, description: String) {
        if (photoMemeUrl != null &&
            checkTitleInput(title) &&
            checkDescriptionInput(description)
        ) {
            createMeme()
        }
    }

    private fun checkTitleInput(title: String): Boolean = (5 < title.length && title.length > 140)

    private fun checkDescriptionInput(description: String): Boolean =
        (20 < description.length && description.length > 1000)


}