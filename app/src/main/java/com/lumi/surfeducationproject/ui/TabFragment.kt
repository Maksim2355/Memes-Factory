package com.lumi.surfeducationproject.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lumi.surfeducationproject.App
import com.lumi.surfeducationproject.R
import com.lumi.surfeducationproject.common.managers.BottomBarVisible
import com.lumi.surfeducationproject.common.params.EXTRA_DETAILS_MEME
import com.lumi.surfeducationproject.domain.model.Meme
import com.lumi.surfeducationproject.navigation.NavigationBackPressed
import com.lumi.surfeducationproject.navigation.NavigationMemeDetails

class TabFragment : Fragment(), NavigationBackPressed, NavigationMemeDetails, BottomBarVisible {

    companion object {
        private val LABEL_MEME_FEED = "fragment_meme_feed"
        private val LABEL_PROFILE = "fragment_profile"
    }

    private lateinit var navControllerTab: NavController
    private lateinit var bottomNavView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.instance.getFragmentContentComponentOrCreateIfNull(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomNavView = view.findViewById(R.id.bottom_navigation_view)
        navControllerTab =
            Navigation.findNavController(view.findViewById(R.id.nav_host_fragment_content))
        NavigationUI.setupWithNavController(bottomNavView, navControllerTab);
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_toolbar_feed, menu)
    }

    override fun showBottomNavigationBar() {
        bottomNavView.visibility = View.VISIBLE
    }

    override fun hideBottomNavigationBar() {
        bottomNavView.visibility = View.GONE
    }

    override fun back() {
        navControllerTab.popBackStack()
    }

    override fun startMemeDetailsScreen(meme: Meme) {
        when (navControllerTab.currentDestination?.label) {
            LABEL_MEME_FEED -> {
                val action =
                    MemeFeedFragmentDirections.actionMemeFeedFragmentToMemeDetailsFragment(meme)
                navControllerTab.navigate(action)
            }
            LABEL_PROFILE -> {
                val action =
                    ProfileFragmentDirections.actionProfileFragmentToMemeDetailsFragment(meme)
                navControllerTab.navigate(action)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        App.instance.clearFragmentContentComponent()
    }


}