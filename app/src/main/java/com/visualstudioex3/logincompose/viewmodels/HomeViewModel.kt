package com.visualstudioex3.logincompose.viewmodels

import androidx.lifecycle.ViewModel
import com.visualstudioex3.logincompose.services.FirebaseAuthService
import com.visualstudioex3.logincompose.services.IAuthService

class HomeViewModel(
    private val authService: IAuthService = FirebaseAuthService()
) : ViewModel() {
    fun isUserLogged(): Boolean = authService.currentSession != null
}
