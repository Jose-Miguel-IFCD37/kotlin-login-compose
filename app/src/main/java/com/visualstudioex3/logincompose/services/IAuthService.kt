package com.visualstudioex3.logincompose.services

data class UserSession(
    val id: String,
    val user: String
)

data class LoginRequest(
    val user: String,
    val password: String
)

interface IAuthService {
    val currentSession: UserSession?

    suspend fun loginAsync(request: LoginRequest)
    fun logout()
}
