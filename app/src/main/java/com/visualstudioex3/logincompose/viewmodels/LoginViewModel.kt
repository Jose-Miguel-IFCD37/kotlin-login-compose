package com.visualstudioex3.logincompose.viewmodels

import androidx.lifecycle.ViewModel
import com.visualstudioex3.logincompose.services.FirebaseAuthService
import com.visualstudioex3.logincompose.services.IAuthService
import com.visualstudioex3.logincompose.services.LoginRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class LoginUiState(
    val awaiting: Boolean = false,
    val message: String = ""
)

class LoginViewModel(
    private val authService: IAuthService = FirebaseAuthService()
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    suspend fun loginAsync(request: LoginRequest): Boolean {
        var success = false
        var errorMessage = ""

        try {
            _uiState.update { currentState ->
                currentState.copy(
                    awaiting = true,
                    message = "Iniciando sesión..."
                )
            }

            authService.loginAsync(request)
            success = true
        } catch (e: Exception) {
            errorMessage = e.message!!
        } finally {
            _uiState.update { currentState ->
                currentState.copy(
                    awaiting = false,
                    message = errorMessage
                )
            }
        }

        return success
    }
}
