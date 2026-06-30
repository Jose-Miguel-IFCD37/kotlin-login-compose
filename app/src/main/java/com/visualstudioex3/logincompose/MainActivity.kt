package com.visualstudioex3.logincompose

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.visualstudioex3.logincompose.services.FirebaseAuthService
import com.visualstudioex3.logincompose.services.IAuthService
import com.visualstudioex3.logincompose.services.IToastService
import com.visualstudioex3.logincompose.services.ToastService
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreen()
        }
    }
}

@Composable
fun LoginScreen() {
    val context: Context = LocalContext.current
    val authService: IAuthService = FirebaseAuthService()
    val toastService: IToastService = ToastService(context)
    val coroutineScope = rememberCoroutineScope()

    var usuario: String by remember { mutableStateOf("") }
    var password: String by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Inicio de Sesión", style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = usuario,
                onValueChange = { usuario = it },
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
                    coroutineScope.launch {
                        var resultMessage: String

                        try {
                            authService.loginAsync(usuario, password)
                            resultMessage = "Acceso Correcto"
                        } catch (e: Exception) {
                            resultMessage = e.message!!
                        }

                        toastService.showToast(resultMessage)
                    }
                }, Modifier.defaultMinSize(minWidth = OutlinedTextFieldDefaults.MinWidth)
            ) {
                Text("Ingresar")
            }
        }
    }
}
