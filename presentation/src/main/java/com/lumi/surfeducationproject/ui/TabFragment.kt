package com.lumi.surfeducationproject.ui

import android.os.Bundle
import android.view.*
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.domain.model.Meme
import com.lumi.surfeducationproject.R
import com.lumi.surfeducationproject.common.managers.BottomBarVisible
import com.lumi.surfeducationproject.common.params.EXTRA_MEME_ID
import com.lumi.surfeducationproject.databinding.FragmentTabBinding
import com.lumi.surfeducationproject.navigation.NavigationBackPressed
import com.lumi.surfeducationproject.navigation.NavigationDestination
import com.lumi.surfeducationproject.navigation.navigation.NavigatorNavController
import com.lumi.surfeducationproject.navigation.navigation.Route
import dagger.android.support.DaggerFragment

class TabFragment : DaggerFragment(), NavigationBackPressed,
    BottomBarVisible, NavigationDestination {

    private lateinit var navigator: NavigatorNavController

    private lateinit var binding: FragmentTabBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTabBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navControllerTab =
            Navigation.findNavController(view.findViewById(R.id.nav_host_fragment_content))
        navigator = NavigatorNavController(navControllerTab)
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navControllerTab)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_toolbar_feed, menu)
    }

    override fun showBottomNavigationBar() {
        binding.bottomNavigationView.visibility = View.VISIBLE
    }

    override fun hideBottomNavigationBar() {
        binding.bottomNavigationView.visibility = View.GONE
    }

    override fun back() {
        navigator.popBackStack()
    }

    override fun navigateTo(route: Route) {
        navigator.navigate(route)
    }

}