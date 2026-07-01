package com.visualstudioex3.logincompose.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.visualstudioex3.logincompose.Screen
import com.visualstudioex3.logincompose.services.LoginRequest
import com.visualstudioex3.logincompose.viewmodels.LoginViewModel
import kotlinx.coroutines.launch

@Composable
fun LoginView(
    viewModel: LoginViewModel = viewModel(),
    navController: NavController
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val coroutineScope = rememberCoroutineScope()

    var user: String by remember { mutableStateOf("") }
    var password: String by remember { mutableStateOf("") }

    Column(Modifier.fillMaxWidth()) {
        Text(
            text = "Inicio de Sesión",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = user,
            onValueChange = { user = it },
            label = { Text("Usuario") })

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                // TODO: Test synchronous method here:
                coroutineScope.launch {
                    val request = LoginRequest(user, password)

                    if (viewModel.loginAsync(request))
                        navController.navigate(Screen.Home.route)
                }
            },
            Modifier.defaultMinSize(minWidth = OutlinedTextFieldDefaults.MinWidth)
        ) {
            Text("Iniciar sesión")
        }

        Button(
            onClick = {
                navController.navigate(Screen.Signin.route)
            },
            Modifier.defaultMinSize(minWidth = OutlinedTextFieldDefaults.MinWidth)
        ) {
            Text("Registrarse")
        }

        Spacer(modifier = Modifier.height(20.dp))

        if (uiState.isLoging) {
            // TODO: Implement CircularProgressIndicator
        }

        if (uiState.showError) {
            Text(uiState.errorMessage) // TODO: Improve text style.
        }
    }
}
