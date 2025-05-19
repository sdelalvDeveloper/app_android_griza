package com.sebasdelalv.proyecto_griza.data.repository

import com.google.gson.Gson
import com.sebasdelalv.proyecto_griza.data.mapper.toDomain
import com.sebasdelalv.proyecto_griza.data.network.RetrofitClient
import com.sebasdelalv.proyecto_griza.data.network.dto.ErrorResponse
import com.sebasdelalv.proyecto_griza.domain.model.TallerResult
import com.sebasdelalv.proyecto_griza.domain.repository.TallerRespository

class TallerRepositoryImpl(): TallerRespository {
    override suspend fun getAll(token: String): Result<List<TallerResult>> {
        return try {
            val response = RetrofitClient.getRetrofit().getAllTalleres("Bearer $token")
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