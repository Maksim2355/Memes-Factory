package com.lumi.surfeducationproject.presenters

import com.lumi.surfeducationproject.common.BasePresenter
import com.lumi.surfeducationproject.domain.model.Meme
import com.lumi.surfeducationproject.domain.repository.MemeRepository
import com.lumi.surfeducationproject.views.AddMemeView
import java.security.SecureRandom
import java.util.*
import javax.inject.Inject

class AddMemePresenter @Inject constructor(
    private val memeRepository: MemeRepository
) : BasePresenter<AddMemeView>() {

    private var titleMeme: String? = null
    private var descriptionMeme: String? = null
    private var photoMemeUrl: String? = null


    /*
    Observable отслеживает изменение заголовка и описания и дергает методы в презентере
    Мы устанавливаем значения в презентере, проверяем все поля и активируем кнопку
    */
    fun updateTitle(title: String) {
        titleMeme = title
        checkFieldsAndImg()
    }

    fun updateDescription(description: String) {
        descriptionMeme = description
        checkFieldsAndImg()
    }

    fun updateImg(url: String){
        photoMemeUrl = url
        viewState.showImg(url)
        checkFieldsAndImg()
    }

    private fun checkFieldsAndImg() {
        if (photoMemeUrl != null &&
            checkValidTitleInput(titleMeme) &&
            checkValidDescriptionInput(descriptionMeme)
        ) {
            println(titleMeme)
            println(photoMemeUrl)
            println(descriptionMeme)
            viewState.enableCreateMemeBtn()
        } else {
            viewState.disableCreateMemeBtn()
        }
    }


    //Создание мема из данных и обновления бд
    fun createMeme() {
        val meme = Meme(
            SecureRandom().nextInt(),
            titleMeme!!,
            getCreatedData(),
            descriptionMeme!!,
            true,
            photoMemeUrl!!
        )
        memeRepository.addMeme(meme)
        clearData()
    }

    fun deleteImg() {
        photoMemeUrl = null
        viewState.hideImg()
    }

    private fun clearData(){
        titleMeme = null
        descriptionMeme = null
        photoMemeUrl = null
        viewState.hideImg()
        viewState.clearFieldsAndImg()
    }

    private fun getCreatedData(): Int {
        return Random().nextInt(1000000)
    }

    private fun checkValidTitleInput(title: String?): Boolean =
        if (title != null) (title.length in 5..100)
        else false

    private fun checkValidDescriptionInput(description: String?): Boolean =
        if (description != null) (description.length in 20..999)
        else false


}