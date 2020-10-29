package com.lumi.surfeducationproject.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lumi.surfeducationproject.R
import javax.inject.Inject

class TabFragment : Fragment(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var navControllerTab: NavController

    private lateinit var bottomNavView: BottomNavigationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomNavView = view.findViewById(R.id.bottomNavigationView)
        bottomNavView.setOnNavigationItemSelectedListener(this)

        navControllerTab = Navigation.findNavController(view.findViewById(R.id.nav_host_fragment_content))
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.memes_feed_nav_mi -> {
                navigateMemesFeed()
                true
            }R.id.memes_add_nav_mi -> {
                navigateAddMeme()
                true
            }R.id.profile_nav_mi -> {
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