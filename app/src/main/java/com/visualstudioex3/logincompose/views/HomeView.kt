package com.visualstudioex3.logincompose.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.visualstudioex3.logincompose.Screen
import com.visualstudioex3.logincompose.viewmodels.HomeViewModel

@Composable
fun HomeView(
    viewModel: HomeViewModel = viewModel(),
    navController: NavController
) {
    if (!viewModel.isUserLogged()) {
        navController.navigate(Screen.Login.route)
    } else {
        Column(Modifier.fillMaxWidth()) {
            Text(
                text = "Pagina de inicio",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { navController.navigate(Screen.Profile.route) },
                Modifier.defaultMinSize(minWidth = OutlinedTextFieldDefaults.MinWidth),
            ) {
                Text("Perfil de usuario")
            }

            Button(
                onClick = { navController.navigate(Screen.Logout.route) },
                Modifier.defaultMinSize(minWidth = OutlinedTextFieldDefaults.MinWidth),
            ) {
                Text("Cerrar sesión")
            }
        }
    }
}
