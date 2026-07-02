package com.visualstudioex3.logincompose.viewmodels

import androidx.lifecycle.ViewModel
import com.visualstudioex3.logincompose.services.FirebaseAuthService
import com.visualstudioex3.logincompose.services.IAuthService
import com.visualstudioex3.logincompose.services.SigninRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class SigninUiState(
    val awaiting: Boolean = false,
    val message: String = ""
)

class SigninViewModel(
    private val authService: IAuthService = FirebaseAuthService()
) : ViewModel() {
    private val _uiState = MutableStateFlow(SigninUiState())
    val uiState: StateFlow<SigninUiState> = _uiState.asStateFlow()

    suspend fun signinAsync(request: SigninRequest): Boolean {
        var success = false
        var errorMessage = ""

        try {
            _uiState.update { currentState ->
                currentState.copy(
                    awaiting = true,
                    message = "Creando usuario..."
                )
            }

            authService.signinAsync(request)
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
