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
    val isLoging: Boolean = false,
    val showError: Boolean = false,
    val errorMessage: String = ""
)

class LoginViewModel(
    private val authService: IAuthService = FirebaseAuthService()
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    suspend fun loginAsync(request: LoginRequest): Boolean {
        var success: Boolean = false
        var errorMessage: String = ""

        try {
            _uiState.update { currentState ->
                currentState.copy(
                    isLoging = true
                )
            }

            authService.loginAsync(request)
            success = true
        } catch (e: Exception) {
            errorMessage = e.message!!
        }

        _uiState.update { currentState ->
            currentState.copy(
                isLoging = false,
                showError = !success,
                errorMessage = errorMessage
            )
        }

        return success
    }

    fun logout(): Boolean {
        var success: Boolean = false
        var errorMessage: String = ""

        try {
            authService.logout()
            success = true
        } catch (e: Exception) {
            errorMessage = e.message!!
        }

        _uiState.update { currentState ->
            currentState.copy(
                showError = !success,
                errorMessage = errorMessage
            )
        }

        return success
    }
}
