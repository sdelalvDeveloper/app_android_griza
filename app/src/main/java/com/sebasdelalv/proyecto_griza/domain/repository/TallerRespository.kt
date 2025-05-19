package com.sebasdelalv.proyecto_griza.domain.repository

import com.sebasdelalv.proyecto_griza.domain.model.TallerResult

interface TallerRespository {
    suspend fun getAll(token: String): Result<List<TallerResult>>
}