package com.sebasdelalv.proyecto_griza.data.repository

import com.google.gson.Gson
import com.sebasdelalv.proyecto_griza.data.mapper.toDomain
import com.sebasdelalv.proyecto_griza.data.network.RetrofitClient
import com.sebasdelalv.proyecto_griza.data.network.dto.ErrorResponse
import com.sebasdelalv.proyecto_griza.data.network.dto.LoginRequest
import com.sebasdelalv.proyecto_griza.data.network.dto.RegisterUserRequest
import com.sebasdelalv.proyecto_griza.data.network.dto.UpdatePasswordRequest
import com.sebasdelalv.proyecto_griza.domain.model.LoginResult
import com.sebasdelalv.proyecto_griza.domain.model.RegisterResult
import com.sebasdelalv.proyecto_griza.domain.repository.AuthRepository

class AuthRepositoryImpl() : AuthRepository {

    override suspend fun login(username: String, password: String): Result<LoginResult> {
        return try {
            val response = RetrofitClient.getRetrofit().login(LoginRequest(username, password))
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it.toDomain())
                } ?: Result.failure(Exception("Respuesta vacía"))
            } else {
                val errorBodyString = response.errorBody()?.string()
                val errorMessage = try {
                    val gson = Gson()
                    val errorResponse = gson.fromJson(errorBodyString, ErrorResponse::class.java)
                    errorResponse?.message ?: "Error desconocido"
                } catch (e: Exception) {
                    "Error desconocido"
                }
                Result.failure(Exception(errorMessage))
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun register(
        username: String,
        email: String,
        telefono: String,
        password: String,
        passwordRepeat: String
    ): Result<RegisterResult> {
        return try {
            val response = RetrofitClient.getRetrofit().register(RegisterUserRequest(username, email, telefono, password, passwordRepeat))
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it.toDomain())
                } ?: Result.failure(Exception("Respuesta vacía"))
            } else {
                val errorBodyString = response.errorBody()?.string()
                val errorMessage = try {
                    val gson = Gson()
                    val errorResponse = gson.fromJson(errorBodyString, ErrorResponse::class.java)
                    errorResponse?.message ?: "Error desconocido"
                } catch (e: Exception) {
                    "Error desconocido"
                }
                Result.failure(Exception(errorMessage))
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun get(
        username: String,
        token: String
    ): Result<RegisterResult> {
        return try {
            val response = RetrofitClient.getRetrofit().getUser("Bearer $token", username)
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it.toDomain())
                } ?: Result.failure(Exception("Respuesta vacía"))
            } else {
                val errorBodyString = response.errorBody()?.string()
                val errorMessage = try {
                    val gson = Gson()
                    val errorResponse = gson.fromJson(errorBodyString, ErrorResponse::class.java)
                    errorResponse?.message ?: "Error desconocido"
                } catch (e: Exception) {
                    "Error desconocido"
                }
                Result.failure(Exception(errorMessage))
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun delete(
        token: String,
        username: String,
        password: String
    ): Result<RegisterResult> {
        return try {
            val response = RetrofitClient.getRetrofit().deleteUser("Bearer $token", username, password)
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it.toDomain())
                } ?: Result.failure(Exception("Respuesta vacía"))
            } else {
                val errorBodyString = response.errorBody()?.string()
                val errorMessage = try {
                    val gson = Gson()
                    val errorResponse = gson.fromJson(errorBodyString, ErrorResponse::class.java)
                    errorResponse?.message ?: "Error desconocido"
                } catch (e: Exception) {
                    "Error desconocido"
                }
                Result.failure(Exception(errorMessage))
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun update(token: String, usuario: UpdatePasswordRequest): Result<Boolean> {
        return try {
            val response = RetrofitClient.getRetrofit().updatePassword("Bearer $token",usuario)
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(Exception("Respuesta vacía"))
            } else {
                val errorBodyString = response.errorBody()?.string()
                val errorMessage = try {
                    val gson = Gson()
                    val errorResponse = gson.fromJson(errorBodyString, ErrorResponse::class.java)
                    errorResponse?.message ?: "Error desconocido"
                } catch (e: Exception) {
                    "Error desconocido"
                }
                Result.failure(Exception(errorMessage))
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }


}
