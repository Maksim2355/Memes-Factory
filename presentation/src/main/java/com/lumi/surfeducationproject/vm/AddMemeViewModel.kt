package com.lumi.surfeducationproject.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.Meme
import com.example.domain.repository.MemeRepository
import com.lumi.surfeducationproject.utils.getCreatedData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import java.security.SecureRandom
import javax.inject.Inject

class AddMemeViewModel @Inject constructor(
    private val memeRepository: MemeRepository
) : ViewModel() {

    private val _imgUrl: MutableLiveData<String> = MutableLiveData(null)
    val imgUrl: LiveData<String?>
        get() = _imgUrl

    private val _isActiveCreateMemeButton: MutableLiveData<Boolean> = MutableLiveData(false)
    val isActiveCreateMemeButton: LiveData<Boolean>
        get() = _isActiveCreateMemeButton


    private val _titleMeme: MutableLiveData<String> = MutableLiveData("")
    val titleMeme: LiveData<String>
        get() = _titleMeme

    private val _descriptionMeme: MutableLiveData<String> = MutableLiveData("")
    val descriptionMeme: LiveData<String>
        get() = _descriptionMeme

    private val _isShowDownloadImgDialog: MutableLiveData<Boolean> = MutableLiveData(false)
    val isShowDownloadImgDialog: LiveData<Boolean>
        get() = _isShowDownloadImgDialog


    fun downloadImgUrl(url: String) {
        _imgUrl.value = url
    }

    fun removeImg() {
        _imgUrl.value = null
    }

    fun updateTitleMeme(title: String) {
        _titleMeme.value = title
        checkValidDataCreateMemeButton()
    }

    fun updateDescriptionMeme(description: String) {
        _descriptionMeme.value = description
        checkValidDataCreateMemeButton()
    }

    fun createMeme() {
        val rnd = SecureRandom()
        memeRepository.addMeme(
            Meme(
                id = rnd.nextInt(),
                title = _titleMeme.value!!,
                createdDate = getCreatedData(),
                description = _descriptionMeme.value!!,
                photoUrl = _imgUrl.value!!,
                isFavorite = rnd.nextBoolean()
            )
        ).observeOn(AndroidSchedulers.mainThread()).subscribe( {
            clearAllFiled()
        }, {
            //Тут показываем снакбар
        })
        clearAllFiled()
    }

    private fun clearAllFiled() {
        _titleMeme.value = ""
        _descriptionMeme.value = ""
        _isActiveCreateMemeButton.value = false
        _imgUrl.value = null
    }

    private fun checkValidDataCreateMemeButton() {
        _isActiveCreateMemeButton.value = checkValidDescriptionInput() && checkValidTitleInput()
    }

    private fun checkValidTitleInput(): Boolean = _titleMeme.value?.length in 5..100

    private fun checkValidDescriptionInput(): Boolean = _descriptionMeme.value?.length in 10..999

}