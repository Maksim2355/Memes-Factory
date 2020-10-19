package com.lumi.surfeducationproject

import android.graphics.Color
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.lumi.surfeducationproject.common.StyleManager
import com.lumi.surfeducationproject.data.model.Meme
import com.lumi.surfeducationproject.navigation.NavigationAddMeme
import com.lumi.surfeducationproject.navigation.NavigationStartApp
import com.lumi.surfeducationproject.navigation.NavigationContent
import com.lumi.surfeducationproject.navigation.NavigationMemeDetails
import com.lumi.surfeducationproject.services.local.SharedPrefServiceImpl


class AppActivity : AppCompatActivity(), NavigationStartApp, NavigationContent,
                    NavigationAddMeme, NavigationMemeDetails , StyleManager {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_container)
    }

    override fun startApp() {
        if (SharedPrefServiceImpl.readUser() != null){
            navController.navigate(R.id.action_splashFragment_to_tabFragment)
        }else{
            navController.navigate(R.id.action_splashFragment_to_authFragment)
        }
    }

    override fun startContentScreen() {
        navController.navigate(R.id.action_authFragment_to_tabFragment)
    }

    override fun setColorStatusBar(color: Int) {
        val window: Window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.setStatusBarColor(resources.getColor(R.color.colorPrimaryContent))
        window.setNavigationBarColor(Color.BLUE)
    }

    override fun startMemeDetailsScreen(meme: Meme) {

    }

    override fun startAddMemeScreen() {
        navController.navigate(R.id.action_tabFragment_to_addMemeFragment)
    }
}