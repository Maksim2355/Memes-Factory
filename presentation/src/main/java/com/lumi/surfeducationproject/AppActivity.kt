package com.lumi.surfeducationproject


import android.os.Bundle
import androidx.navigation.findNavController
import com.lumi.surfeducationproject.databinding.ActivityAppBinding
import com.lumi.surfeducationproject.navigation.NavigationDestination
import com.lumi.surfeducationproject.navigation.navigation.NavigatorNavController
import com.lumi.surfeducationproject.navigation.navigation.Route
import dagger.android.DaggerActivity
import dagger.android.support.DaggerAppCompatActivity


class AppActivity : DaggerAppCompatActivity(), NavigationDestination {

    private lateinit var navigator: NavigatorNavController
    private lateinit var binding: ActivityAppBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navigator = NavigatorNavController(findNavController(R.id.nav_host_fragment_container))
    }

    override fun navigateTo(route: Route) {
        navigator.navigate(route)
    }


}