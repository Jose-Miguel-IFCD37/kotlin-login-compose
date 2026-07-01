package com.visualstudioex3.logincompose

sealed class Screen(val route: String) {
    object Login: Screen(route = "login")
    object Logout: Screen(route = "logout")
    object Signin: Screen(route = "signin")
    object Home: Screen(route = "home")
    object Profile: Screen(route = "profile")
}
