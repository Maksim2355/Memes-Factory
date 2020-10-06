package com.lumi.surfeducationproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.lumi.surfeducationproject.navigation.StartAppNav

class MainActivity : AppCompatActivity(),
    StartAppNav {

    //TODO Прокидывать NavController через внедрение зависимостей
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

}