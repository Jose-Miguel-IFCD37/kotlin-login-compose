package com.visualstudioex3.logincompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.visualstudioex3.logincompose.ui.theme.LoginComposeTheme
import com.visualstudioex3.logincompose.views.Home
import com.visualstudioex3.logincompose.views.HomeView
import com.visualstudioex3.logincompose.views.Login
import com.visualstudioex3.logincompose.views.LoginView
import com.visualstudioex3.logincompose.views.Profile
import com.visualstudioex3.logincompose.views.ProfileView
import com.visualstudioex3.logincompose.views.Signin
import com.visualstudioex3.logincompose.views.SigninView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController: NavHostController = rememberNavController()

            LoginComposeTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Home,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable<Home> { HomeView(navController = navController) }
                        composable<Login> { LoginView(navController = navController) }
                        composable<Signin> { SigninView(navController = navController) }
                        composable<Profile> { ProfileView(navController = navController) }
                    }
                }
            }
        }
    }
}
