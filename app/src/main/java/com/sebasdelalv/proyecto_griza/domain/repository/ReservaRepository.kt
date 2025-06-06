package com.sebasdelalv.proyecto_griza.domain.repository

import com.sebasdelalv.proyecto_griza.domain.model.ReservaResult

interface ReservaRepository {
    suspend fun insertReserva(token: String, username: String, tallerId: String): Result<ReservaResult>

    suspend fun getReservaByUsername(token: String, username: String): Result<List<ReservaResult>>

    suspend fun deleteReservaById(token: String, id: String, tallerID: String): Result<Unit>

    suspend fun getFirst(token: String, username: String): Result<ReservaResult>

    suspend fun deleteAll(token: String, username: String) : Result<Unit>

    suspend fun deleteReservaByIdTaller(token: String, tallerID: String): Result<Unit>
}