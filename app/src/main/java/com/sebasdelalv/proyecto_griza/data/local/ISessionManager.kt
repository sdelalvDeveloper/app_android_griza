package com.sebasdelalv.proyecto_griza.data.local

interface ISessionManager {
    fun getUsername(): String?
    fun getToken(): String?
    fun getRole(): String?
}
