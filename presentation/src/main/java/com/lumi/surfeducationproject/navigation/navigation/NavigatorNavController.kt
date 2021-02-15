package com.lumi.surfeducationproject.navigation.navigation

import androidx.navigation.NavController


class NavigatorNavController(private val navController: NavController) {

    fun navigate(route: Route) {
        navController.navigate(route.actionRoute.action, route.bundle)
    }

    fun popBackStack(){
        navController.popBackStack()
    }

}