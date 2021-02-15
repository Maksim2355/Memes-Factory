package com.lumi.surfeducationproject


import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.lumi.surfeducationproject.navigation.NavigationDestination
import com.lumi.surfeducationproject.navigation.navigation.NavigatorNavController
import com.lumi.surfeducationproject.navigation.navigation.Route
import dagger.android.DaggerActivity


class AppActivity : DaggerActivity(), NavigationDestination {

    private lateinit var navigator: NavigatorNavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigator = NavigatorNavController(findNavController(R.id.nav_host_fragment_container))
    }

    override fun navigateTo(route: Route) {
        navigator.navigate(route)
    }


}