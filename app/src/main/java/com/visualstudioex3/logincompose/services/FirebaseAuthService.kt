package com.visualstudioex3.logincompose.services

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

class FirebaseAuthService: IAuthService {
    companion object {
        private const val LOG_TAG: String = "AUTH_SERVICE"
    }

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    constructor() {
        if (currentSession != null)
            Log.i(LOG_TAG, "Sesión iniciada: $currentSession")
    }

    override val currentSession: UserSession?
        get() {
            val firebaseUser: FirebaseUser? = auth.currentUser

            return if (firebaseUser != null)
                UserSession(firebaseUser.uid, firebaseUser.email!!)
            else
                null
        }

    override suspend fun loginAsync(request: LoginRequest) {
        try {
            check(currentSession == null) {
                "Ya existe una sesión activa de usuario ($currentSession)"
            }
            require(request.user.isNotEmpty()) {
                "Debe introducir un valor para el usuario."
            }
            require(request.password.isNotEmpty()) {
                "Debe introducir un valor para la contraseña."
            }

            Log.i(LOG_TAG, "Iniciando sesión para el usuario '${request.user}'...")

            auth.signInWithEmailAndPassword(request.user, request.password)
                .await()

            Log.i(
                LOG_TAG,
                "Inicio de sesión correcto para el usuario '${request.user}' " +
                        "($currentSession)"
            )
        } catch (_: FirebaseAuthInvalidCredentialsException) {
            reportError("El usuario o la contraseña son incorrectos.")
        } catch (e: Exception) {
            reportError("Error al iniciar sesión del usuario: ${e.message}")
        }
    }

    override fun logout() {
        try {
            check(currentSession != null) { "No existe sesión activa de usuario." }

            Log.i(LOG_TAG, "Cerrando sesión activa... ($currentSession)")

            auth.signOut()

            Log.i(LOG_TAG, "Sesión activa cerrada correctamente.")
        } catch (e: Exception) {
            reportError("Error al cerrar sesión del usuario: $e")
        }
    }

    private fun reportError(message: String) {
        Log.e(LOG_TAG, message)
        error(message)
    }
}
