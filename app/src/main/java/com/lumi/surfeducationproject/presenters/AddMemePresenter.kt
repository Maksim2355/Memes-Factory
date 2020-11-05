package com.lumi.surfeducationproject.presenters

import com.lumi.surfeducationproject.domain.model.Meme
import com.lumi.surfeducationproject.domain.repository.MemeRepository
import com.lumi.surfeducationproject.domain.repository.UserRepository
import com.lumi.surfeducationproject.views.AddMemeView
import moxy.MvpPresenter
import javax.inject.Inject

class AddMemePresenter @Inject constructor(
    memeRepository: MemeRepository,
    userRepository: UserRepository
) : MvpPresenter<AddMemeView>() {

    private var titleMeme: String? = null
    private var descriptionMeme: String? = null
    private var photoMemeUrl: String? = null

    private val createdDate by lazy { getCreatedData() }

    fun closeImg() {
        TODO("Not yet implemented")
    }


    private fun getCreatedData(): String {
        TODO("Not yet implemented")
    }

}