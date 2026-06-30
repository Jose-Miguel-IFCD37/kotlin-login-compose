package com.visualstudioex3.logincompose.services

interface IAuthService {
    val currentSession: UserSession?

    suspend fun loginAsync(user: String, password: String)
    fun logout()
}
