package com.sebasdelalv.proyecto_griza.domain.repository

import com.sebasdelalv.proyecto_griza.domain.model.ReservaResult

interface ReservaRepository {
    suspend fun insertReserva(token: String, username: String, tallerId: String): Result<ReservaResult>
}