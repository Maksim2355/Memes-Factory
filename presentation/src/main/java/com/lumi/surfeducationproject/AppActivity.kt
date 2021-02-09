package com.lumi.surfeducationproject


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.lumi.surfeducationproject.navigation.NavigationAuth
import com.lumi.surfeducationproject.navigation.NavigationContent
import com.lumi.surfeducationproject.navigation.NavigationStartApp


class AppActivity : AppCompatActivity(), NavigationStartApp, NavigationContent,
    NavigationAuth {

    private val navController: NavController by lazy {
        findNavController(R.id.nav_host_fragment_container)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.instance.startActivityComponent(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onDestroy() {
        super.onDestroy()
        App.instance.clearActivityComponent()
    }

    override fun startApp(isAuthUser: Boolean) {
        if (isAuthUser) {
            navController.navigate(R.id.action_splashFragment_to_tabFragment)
        } else {
            navController.navigate(R.id.action_splashFragment_to_authFragment)
        }
    }

    override fun startContentScreen() {
        navController.navigate(R.id.action_authFragment_to_tabFragment)
    }

    override fun startAuthScreen() {
        navController.navigate(R.id.action_tabFragment_to_authFragment)
    }


}