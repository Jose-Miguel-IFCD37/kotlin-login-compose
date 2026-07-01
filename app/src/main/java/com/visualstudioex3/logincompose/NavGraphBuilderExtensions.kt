package com.visualstudioex3.logincompose

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.visualstudioex3.logincompose.views.HomeView
import com.visualstudioex3.logincompose.views.LoginView
import com.visualstudioex3.logincompose.views.LogoutView
import com.visualstudioex3.logincompose.views.ProfileView
import com.visualstudioex3.logincompose.views.SigninView

fun NavGraphBuilder.addLoginTopLevel(navController: NavController) {
    val route = Screen.Login.route

    navigation(
        route = route,
        startDestination = route
    ) {
        composable(route) { LoginView(navController = navController) }
    }
}

fun NavGraphBuilder.addLogoutTopLevel(navController: NavController) {
    val route = Screen.Logout.route

    navigation(
        route = route,
        startDestination = route
    ) {
        composable(route) { LogoutView(navController = navController) }
    }
}

fun NavGraphBuilder.addSigninTopLevel(navController: NavController) {
    val route = Screen.Signin.route

    navigation(
        route = route,
        startDestination = route
    ) {
        composable(route) { SigninView(navController = navController) }
    }
}

fun NavGraphBuilder.addHomeTopLevel(navController: NavController) {
    val route = Screen.Home.route

    navigation(
        route = route,
        startDestination = route
    ) {
        composable(route) { HomeView(navController = navController) }
    }
}

fun NavGraphBuilder.addProfileTopLevel(navController: NavController) {
    val route = Screen.Profile.route

    navigation(
        route = route,
        startDestination = route
    ) {
        composable(route) { ProfileView(navController = navController) }
    }
}
