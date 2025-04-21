package com.example.cognitiveexercisesapp.ui.navigation
import androidx.navigation.NavController

object NavigationSingleton {
    var navController: NavController? = null

    fun navigateTo(route: String) {
        navController?.navigate(route)
    }
}