package com.lumi.surfeducationproject.vm

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.Meme
import com.example.domain.model.User
import com.example.domain.repository.MemeRepository
import com.example.domain.repository.UserRepository
import com.lumi.surfeducationproject.common.Event
import com.lumi.surfeducationproject.common.params.EXTRA_MEME_ID
import com.lumi.surfeducationproject.navigation.navigation.ActionRoute
import com.lumi.surfeducationproject.navigation.navigation.Route
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val memeRepository: MemeRepository
) : ViewModel() {

    private val _listUserMeme: MutableLiveData<List<Meme>> = MutableLiveData(emptyList())
    val listUserMeme: LiveData<List<Meme>>
        get() = _listUserMeme

    private val _user: MutableLiveData<User> = MutableLiveData()
    val user: LiveData<User>
        get() = _user

    private val _isShowLogoutDialog: MutableLiveData<Boolean> = MutableLiveData(false)
    val isShowLogoutDialog: LiveData<Boolean>
        get() = _isShowLogoutDialog

    private val _messageSnackBar: MutableLiveData<Event<String>> = MutableLiveData()
    val messageSnackBar: LiveData<Event<String>>
        get() = _messageSnackBar

    private val _navigateTo: MutableLiveData<Event<Route>> = MutableLiveData()
    val navigateTo: LiveData<Event<Route>>
        get() = _navigateTo

    init {
        memeRepository.getUserMemes().observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _listUserMeme.value = it
            }, {
                //TODO хз че тут делать (на самом деле знаю, но мне лень)
            })
        userRepository.getUser().observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it != null) {
                    _user.value = it
                } else processingErrorDownloadProfile()
            }, {
                processingErrorDownloadProfile()
            })
    }

    fun navigateLogoutProfile() {
        _navigateTo.value =
            Event(
                Route(ActionRoute.TAB_FRAGMENT_TO_AUTH_FRAGMENT)
            )
    }

    fun navigateMemeDetails(memeId: Int) {
        val bundle = Bundle().apply { putInt(EXTRA_MEME_ID, memeId) }
        _navigateTo.value =
            Event(
                Route(ActionRoute.PROFILE_FRAGMENT_TO_MEME_DETAILS, bundle)
            )
    }

    fun showLogoutDialog() {
        _isShowLogoutDialog.value = true
    }

    fun hideLogoutDialog() {
        _isShowLogoutDialog.value = false
    }

    fun logout() {
        userRepository
            .deleteUser()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                //Тут я должен что-то с навигацией придумать. Надо создавать Route и передавать
            }, {
                _messageSnackBar.value = Event("Ошибка выхода из аккаунта. Попробуйте еще раз")
            })
    }

    private fun processingErrorDownloadProfile() {
        val mockUser = User(
            -1, "no data", "no data",
            "no data", "no data"
        )
        _user.value = mockUser
    }

}