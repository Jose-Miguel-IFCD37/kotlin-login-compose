package com.visualstudioex3.logincompose.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.visualstudioex3.logincompose.viewmodels.HomeUiState
import com.visualstudioex3.logincompose.viewmodels.HomeViewModel
import kotlinx.serialization.Serializable

@Serializable
object Home

@Composable
fun HomeView(
    navController: NavController,
    viewModel: HomeViewModel = viewModel()
) {
    if (!viewModel.isUserLogged()) {
        navController.navigate(Login)
    } else {
        val uiState: HomeUiState by viewModel.uiState.collectAsStateWithLifecycle()

        Column(Modifier.fillMaxWidth()) {
            Text(
                text = "Pagina de inicio",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { navController.navigate(Profile) },
                Modifier.fillMaxWidth()
                    .defaultMinSize(minWidth = OutlinedTextFieldDefaults.MinWidth),
            ) {
                Text("Perfil de usuario")
            }

            Button(
                onClick = {
                        navController.navigate(Login)
                },
                Modifier.fillMaxWidth()
                    .defaultMinSize(minWidth = OutlinedTextFieldDefaults.MinWidth),
            ) {
                Text("Cerrar sesión")
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(uiState.message)
            }
        }
    }
}
