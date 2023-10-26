package com.example.usersappcompose.core

import androidx.navigation.NavHostController
import androidx.navigation.NavOptions

interface Router {
    fun attach(navHostController: NavHostController)
    fun navigate(route: String, navOptions: NavOptions? = null)
    fun pop()
    fun detach()
}