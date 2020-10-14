package com.lumi.surfeducationproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.lumi.surfeducationproject.common.StyleManager
import com.lumi.surfeducationproject.navigation.StartAppNav
import com.lumi.surfeducationproject.navigation.StartContentScreenNav
import javax.inject.Inject

class AppActivity : AppCompatActivity(), StartAppNav, StartContentScreenNav, StyleManager {

    private lateinit var mNavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mNavController = Navigation.findNavController(this, R.id.nav_host_fragment_container)
    }

    override fun startApp() {
        //TODO() Добавить логику перехода к нужному фрагменту в случае, если пользователь авторизированн
        mNavController.navigate(R.id.action_splashFragment_to_authFragment)
    }

    override fun startContentScreen() {
        mNavController.navigate(R.id.action_authFragment_to_tabFragment)
    }

    override fun setStyleTheme(themeId: Int) {
        setTheme(themeId)
    }

}