package com.visualstudioex3.logincompose.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.visualstudioex3.logincompose.services.SigninRequest
import com.visualstudioex3.logincompose.viewmodels.SigninUiState
import com.visualstudioex3.logincompose.viewmodels.SigninViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

@Serializable
object Signin

@Composable
fun SigninView(
    viewModel: SigninViewModel = viewModel(),
    navController: NavController
) {
    val uiState: SigninUiState by viewModel.uiState.collectAsStateWithLifecycle()
    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    var email: String by remember { mutableStateOf("") }
    var password: String by remember { mutableStateOf("") }
    var repeatPassword: String by remember { mutableStateOf("") }

    Column(Modifier.fillMaxWidth()) {
        Text(
            text = "Registrar nuevo usuario",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            Modifier.fillMaxWidth(),
            label = { Text("Correo electrónico") })

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            Modifier.fillMaxWidth(),
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = repeatPassword,
            onValueChange = { repeatPassword = it },
            Modifier.fillMaxWidth(),
            label = { Text("Repetir contraseña") },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                // TODO: Test synchronous method here:
                coroutineScope.launch {
                    val request = SigninRequest(email, password)

                    if (viewModel.signinAsync(request))
                        navController.navigate(Home)
                }
            },
            Modifier.fillMaxWidth()
                .defaultMinSize(minWidth = OutlinedTextFieldDefaults.MinWidth),
            !uiState.awaiting
        ) {
            Text("Registrarse")
        }

        Button(
            onClick = {
                navController.navigate(Home)
            },
            Modifier.fillMaxWidth()
                .defaultMinSize(minWidth = OutlinedTextFieldDefaults.MinWidth),
            !uiState.awaiting
        ) {
            Text("Volver")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (uiState.awaiting) {
                CircularProgressIndicator(Modifier.size(14.dp))
                Spacer(Modifier.width(4.dp))
            }

            Text(uiState.message)
        }
    }
}
