package com.lumi.surfeducationproject.vm

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.repository.UserRepository
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _isAnimationActive: MutableLiveData<Boolean> = MutableLiveData(false)
    val isAnimationActive: LiveData<Boolean>
        get() = _isAnimationActive

    init {
        startApp()
    }

    private fun startApp() {
        _isAnimationActive.value = true
        userRepository.getUser().subscribe({
            if (it != null) {
                //Переход на главный экран
            } else {
                //На экран авторизациц
            }
        }, {
            //На экран авторизациц
        })

    }
}