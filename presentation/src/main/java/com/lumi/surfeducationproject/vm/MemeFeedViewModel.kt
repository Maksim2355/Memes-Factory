package com.lumi.surfeducationproject.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.Meme
import com.example.domain.repository.MemeRepository
import com.lumi.surfeducationproject.common.Event
import com.lumi.surfeducationproject.common.exceptions.NETWORK_EXCEPTIONS
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import java.util.*
import javax.inject.Inject

class MemeFeedViewModel @Inject constructor(
    private val memeRepository: MemeRepository
) : ViewModel() {

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _showErrorSnackBar: MutableLiveData<Event<String>> = MutableLiveData()
    val showErrorSnackBar: LiveData<Event<String>>
        get() = _showErrorSnackBar

    private val _isEditMode: MutableLiveData<Boolean> = MutableLiveData(false)
    val isEditMode: LiveData<Boolean>
        get() = _isEditMode

    private val _query: MutableLiveData<String> = MutableLiveData()
    val query: LiveData<String>
        get() = _query

    private val _listMeme: MutableLiveData<List<Meme>> = MutableLiveData()
    private val _filterListMeme: MutableLiveData<List<Meme>> = MutableLiveData(emptyList())
    val listMeme: LiveData<List<Meme>>
        get() = if (_isEditMode.value == true) _filterListMeme else _listMeme

    init {
        downloadMemes()
    }

    fun updateMemes() {
        downloadMemes()
    }

    private fun downloadMemes(){
        memeRepository.getMemes().observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _isLoading.value = true }
            .doFinally { _isLoading.value = false }
            .subscribe({
                _listMeme.value = it
            }, {
                if (NETWORK_EXCEPTIONS.contains(it.javaClass)) {
                    _showErrorSnackBar.value =
                        Event(
                            "Отсутствует подключение к интернету " +
                                    "\nПодключитесь к сети и попробуйте снова"
                        )
                }
                _listMeme.value = null
            })
    }


    fun enableEditMode() {
        _isEditMode.value = true
    }

    fun disableEditMode() {
        _isEditMode.value = false
        _filterListMeme.value = emptyList()
        _query.value = ""
    }

    fun filterMemes(query: String) {
        _query.value = query
        _filterListMeme.value = _listMeme.value?.filter {
            it.title.toLowerCase(Locale.ROOT).contains(query.toLowerCase(Locale.ROOT))
        }
    }


}