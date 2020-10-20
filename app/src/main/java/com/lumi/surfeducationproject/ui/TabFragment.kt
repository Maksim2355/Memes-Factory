package com.lumi.surfeducationproject.ui

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lumi.surfeducationproject.R
import com.lumi.surfeducationproject.common.StyleManager
import moxy.MvpAppCompatFragment

class TabFragment : MvpAppCompatFragment(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var styleManager: StyleManager
    private lateinit var bottomNavView: BottomNavigationView
    private lateinit var navControllerTab: NavController


    override fun onAttach(context: Context) {
        super.onAttach(context)
        styleManager = context as StyleManager
        styleManager.setColorStatusBar(R.color.colorPrimaryContent)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomNavView = view.findViewById(R.id.bottomNavigationView)
        navControllerTab = Navigation.findNavController(view.findViewById(R.id.nav_host_fragment_content))

        bottomNavView.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.memes_feed_nav_mi -> {
                styleManager.setColorStatusBar(R.color.colorPrimaryContent)
                navigateMemesFeed()
                true

            }R.id.memes_add_nav_mi -> {
                styleManager.setColorStatusBar(R.color.colorPrimaryContent)
                navigateAddMeme()
                true
            }R.id.profile_nav_mi -> {
                styleManager.setColorStatusBar(R.color.colorPrimary)
                navigateProfile()
                true
            }
            else -> false
        }
    }

    private fun navigateAddMeme() {
        navControllerTab.navigate(R.id.addMemeFragment)
    }

    private fun navigateMemesFeed() {
        navControllerTab.navigate(R.id.memeFeedFragment)
    }

    private fun navigateProfile() {
        navControllerTab.navigate(R.id.profileFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_toolbar_feed, menu)
    }


}