package com.sebasdelalv.proyecto_griza.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class SessionManager(context: Context): ISessionManager {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("UserSession", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_USERNAME = "username"
        private const val KEY_TOKEN = "token"
        private const val KEY_ROLE = "role"
    }

    // Guardar el username y el token
    fun saveUserSession(username: String, token: String, role: String) {
        sharedPreferences.edit() {
            putString(KEY_USERNAME, username)
            putString(KEY_TOKEN, token)
            putString(KEY_ROLE, role)
        }
    }

    // Obtener el username
    override fun getUsername(): String? {
        return sharedPreferences.getString(KEY_USERNAME, null)
    }

    // Obtener el token
    override fun getToken(): String? {
        return sharedPreferences.getString(KEY_TOKEN, null)
    }

    // Obtener el rol
    override fun getRole(): String? {
        return sharedPreferences.getString(KEY_ROLE, null)
    }

    // Limpiar la sesión (para logout)
    fun clearSession() {
        sharedPreferences.edit() {
            clear()
        }
    }
}