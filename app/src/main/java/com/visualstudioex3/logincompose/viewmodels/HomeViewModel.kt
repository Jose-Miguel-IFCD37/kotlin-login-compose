package com.visualstudioex3.logincompose.viewmodels

import androidx.lifecycle.ViewModel
import com.visualstudioex3.logincompose.services.FirebaseAuthService
import com.visualstudioex3.logincompose.services.IAuthService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class HomeUiState(
    val message: String = ""
)

class HomeViewModel(
    private val authService: IAuthService = FirebaseAuthService()
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun isUserLogged(): Boolean = authService.currentSession != null

    fun logout(): Boolean {
        var success = false
        var errorMessage = ""

        try {
            authService.logout()
            success = true
        } catch (e: Exception) {
            errorMessage = e.message!!
        } finally {
            _uiState.update { currentState ->
                currentState.copy(
                    message = errorMessage
                )
            }
        }

        return success
    }
}
