package com.sebasdelalv.proyecto_griza.fakes

import com.sebasdelalv.proyecto_griza.data.network.dto.UpdatePasswordRequest
import com.sebasdelalv.proyecto_griza.domain.model.LoginResult
import com.sebasdelalv.proyecto_griza.domain.model.RegisterResult
import com.sebasdelalv.proyecto_griza.domain.repository.AuthRepository

class FakeAuthRepository : AuthRepository {

    override suspend fun login(username: String, password: String): Result<LoginResult> {
        return if (username == "test@example.com" && password == "password") {
            // Simula un login exitoso con datos válidos
            Result.success(LoginResult(token = "fake_token", role = "user"))
        } else {
            // Simula un fallo de autenticación
            Result.failure(Exception("Credenciales inválidas"))
        }
    }

    override suspend fun register(
        username: String,
        email: String,
        telefono: String,
        password: String,
        passwordRepeat: String
    ): Result<RegisterResult> {
        TODO("Not yet implemented")
    }

    override suspend fun get(
        username: String,
        token: String
    ): Result<RegisterResult> {
        TODO("Not yet implemented")
    }

    override suspend fun delete(
        token: String,
        username: String,
        password: String
    ): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun update(
        token: String,
        usuario: UpdatePasswordRequest
    ): Result<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun getAll(token: String): Result<List<RegisterResult>> {
        TODO("Not yet implemented")
    }

    override suspend fun activarBono(
        token: String,
        username: String
    ): Result<RegisterResult> {
        TODO("Not yet implemented")
    }


}
