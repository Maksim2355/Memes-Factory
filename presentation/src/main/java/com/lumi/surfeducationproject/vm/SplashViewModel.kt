package com.lumi.surfeducationproject.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.repository.UserRepository
import com.lumi.surfeducationproject.common.Event
import com.lumi.surfeducationproject.navigation.navigation.ActionRoute
import com.lumi.surfeducationproject.navigation.navigation.Route
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _navigateTo: MutableLiveData<Event<Route>> = MutableLiveData()
    val navigateTo: LiveData<Event<Route>>
        get() = _navigateTo

    init {
        startApp()
    }

    private fun startApp() {
        userRepository.getUser().subscribe({
            if (it != null) {
                //Переход на главный экран
                _navigateTo.value = Event(Route(ActionRoute.SPLASH_FRAGMENT_TO_TAB_FRAGMENT))
            } else {
                //На экран авторизациц
                _navigateTo.value = Event(Route(ActionRoute.SPLASH_FRAGMENT_TO_AUTH_FRAGMENT))
            }
        }, {
            //На экран авторизациц. Обработать ощибку
            _navigateTo.value = Event(Route(ActionRoute.SPLASH_FRAGMENT_TO_AUTH_FRAGMENT))
        })

    }
}