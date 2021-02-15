package com.lumi.surfeducationproject.navigation

import com.lumi.surfeducationproject.navigation.navigation.Route

interface NavigationDestination {
    fun navigateTo(route: Route)
}