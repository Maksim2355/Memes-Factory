package com.lumi.surfeducationproject.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
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
        navControllerTab = view.findNavController()

        bottomNavView.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.memes_add_nav_mi -> {
                navigateAddMeme()
                true
            }R.id.memes_feed_nav_mi -> {
                navigateMemesFeed()
                true
            }R.id.profile_nav_mi -> {
                navigateProfile()
                true
            }
            else -> false
        }
    }

    private fun navigateAddMeme() {
        TODO("Not yet implemented")
    }

    private fun navigateMemesFeed() {
        navControllerTab.navigate(R.id.memeFeedFragment)
    }

    private fun navigateProfile() {
        navControllerTab.navigate(R.id.profileFragment)
    }


}