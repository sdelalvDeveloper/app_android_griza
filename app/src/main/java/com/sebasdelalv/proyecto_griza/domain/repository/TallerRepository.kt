package com.sebasdelalv.proyecto_griza.domain.repository

import com.sebasdelalv.proyecto_griza.data.network.dto.TallerRequest
import com.sebasdelalv.proyecto_griza.domain.model.TallerResult

interface TallerRepository {
    suspend fun getAll(token: String): Result<List<TallerResult>>
    suspend fun updateTaller(token: String, id: String, taller: TallerRequest): Result<TallerResult>
    suspend fun insertTaller(token: String, taller: TallerRequest): Result<TallerResult>
    suspend fun deleteTaller(token: String, id: String): Result<Unit>
}