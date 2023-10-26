package com.example.usersappcompose.core

import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import javax.inject.Inject

class RouterImpl @Inject constructor() : Router {

    private var navController: NavHostController? = null
    override fun attach(navHostController: NavHostController) {
        navController = navHostController
    }

    override fun navigate(route: String, navOptions: NavOptions?) {
        navController?.navigate(route, navOptions)
    }

    override fun pop() {
        navController?.popBackStack()
    }

    override fun detach() {
        navController = null
    }
}