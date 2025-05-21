package com.sebasdelalv.proyecto_griza.domain.repository

import com.sebasdelalv.proyecto_griza.domain.model.LoginResult
import com.sebasdelalv.proyecto_griza.domain.model.RegisterResult

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<LoginResult>

    suspend fun register(username: String, email: String, telefono: String, password: String, passwordRepeat: String): Result<RegisterResult>

    suspend fun get(username: String, token: String): Result<RegisterResult>

    suspend fun delete(token: String, username: String, password: String): Result<RegisterResult>

}
