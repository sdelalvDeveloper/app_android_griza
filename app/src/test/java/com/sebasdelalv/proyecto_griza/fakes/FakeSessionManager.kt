package com.sebasdelalv.proyecto_griza.fakes

import com.sebasdelalv.proyecto_griza.data.local.ISessionManager

// Fake SessionManager para pruebas
class FakeSessionManager(
    private val role: String = "user",
    private val token: String = "token123",
    private val username: String = "user1"
) : ISessionManager {
    override fun getRole(): String? = role
    override fun getToken(): String? = token
    override fun getUsername(): String? = username
}