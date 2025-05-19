package com.sebasdelalv.proyecto_griza.data.repository

import com.google.gson.Gson
import com.sebasdelalv.proyecto_griza.data.mapper.toDomain
import com.sebasdelalv.proyecto_griza.data.network.RetrofitClient
import com.sebasdelalv.proyecto_griza.data.network.dto.ErrorResponse
import com.sebasdelalv.proyecto_griza.domain.model.ReservaResult
import com.sebasdelalv.proyecto_griza.domain.repository.ReservaRepository

class ReservaRepositoryImpl(): ReservaRepository {
    override suspend fun insertReserva(
        token: String,
        username: String,
        tallerId: String
    ): Result<ReservaResult> {
        return try {
            val response = RetrofitClient.getRetrofit().insertReserva("Bearer $token", username, tallerId)
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
}