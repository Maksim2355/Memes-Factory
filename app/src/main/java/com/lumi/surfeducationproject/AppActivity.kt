package com.lumi.surfeducationproject

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.lumi.surfeducationproject.common.Key_Details_Meme
import com.lumi.surfeducationproject.common.StyleManager
import com.lumi.surfeducationproject.data.dto.network.NetworkMeme
import com.lumi.surfeducationproject.data.repository.UserRepositoryImpl
import com.lumi.surfeducationproject.navigation.*
import com.lumi.surfeducationproject.data.services.local.SharedPreferenceServiceImpl
import com.lumi.surfeducationproject.domain.model.Meme


class AppActivity : AppCompatActivity(), NavigationStartApp, NavigationContent, NavigationMemeDetails , StyleManager,
    NavigationBackPressed, NavigationAuth {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_container)
    }

    override fun startApp(isAuthUser: Boolean) {
        if (isAuthUser){
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
        window.statusBarColor = resources.getColor(color)
    }

    override fun startMemeDetailsScreen(meme: Meme) {
        val bundle = Bundle()
        bundle.putSerializable(Key_Details_Meme, meme)
        navController.navigate(R.id.action_tabFragment_to_memeDetailsFragment, bundle)
    }

    override fun back() {
        navController.popBackStack()
    }

    override fun startAuthScreen() {
        navController.navigate(R.id.action_tabFragment_to_authFragment)
    }

}