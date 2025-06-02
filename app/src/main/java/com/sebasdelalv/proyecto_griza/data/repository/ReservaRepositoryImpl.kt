package com.sebasdelalv.proyecto_griza.data.repository

import com.google.gson.Gson
import com.sebasdelalv.proyecto_griza.data.mapper.toDomain
import com.sebasdelalv.proyecto_griza.data.mapper.toReservaDomain
import com.sebasdelalv.proyecto_griza.data.network.RetrofitClient
import com.sebasdelalv.proyecto_griza.data.network.dto.ErrorResponse
import com.sebasdelalv.proyecto_griza.data.network.dto.RegisterReservaRequest
import com.sebasdelalv.proyecto_griza.domain.model.ReservaResult
import com.sebasdelalv.proyecto_griza.domain.repository.ReservaRepository

class ReservaRepositoryImpl(): ReservaRepository {
    override suspend fun insertReserva(
        token: String,
        username: String,
        tallerId: String
    ): Result<ReservaResult> {
        return try {
            val response = RetrofitClient.getRetrofit().insertReserva("Bearer $token", RegisterReservaRequest(username, tallerId))
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

    override suspend fun getReservaByUsername(
        token: String,
        username: String
    ): Result<List<ReservaResult>> {
        return try {
            val response = RetrofitClient.getRetrofit().getReservasByUsername("Bearer $token", username)
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it.toReservaDomain())
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

    override suspend fun deleteReservaById(
        token: String,
        id: String,
        tallerID: String
    ): Result<Unit> {
        return try {
            val response = RetrofitClient.getRetrofit()
                .deleteReserva("Bearer $token", id, tallerID)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                val errorBody = response.errorBody()?.string()
                val errorMsg = try {
                    Gson().fromJson(errorBody, ErrorResponse::class.java)?.message
                        ?: "Error desconocido"
                } catch (e: Exception) {
                    "Error desconocido"
                }
                Result.failure(Exception(errorMsg))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getFirst(
        token: String,
        username: String
    ): Result<ReservaResult> {
        return try {
            val response = RetrofitClient.getRetrofit().getFirstReservaByUsername("Bearer $token", username)
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

    override suspend fun deleteAll(
        token: String,
        username: String
    ): Result<Unit> {
        return try {
            val response = RetrofitClient.getRetrofit().deleteReservaAllByUsername("Bearer $token", username)
            if (response.isSuccessful) {
                Result.success(Unit)
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

    override suspend fun deleteReservaByIdTaller(
        token: String,
        tallerID: String
    ): Result<Unit> {
        return try {
            val response = RetrofitClient.getRetrofit()
                .deleteReservasByIdTaller("Bearer $token", tallerID)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                val errorBody = response.errorBody()?.string()
                val errorMsg = try {
                    Gson().fromJson(errorBody, ErrorResponse::class.java)?.message
                        ?: "Error desconocido"
                } catch (e: Exception) {
                    "Error desconocido"
                }
                Result.failure(Exception(errorMsg))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}