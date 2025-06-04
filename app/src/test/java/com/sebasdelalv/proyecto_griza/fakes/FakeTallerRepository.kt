package com.sebasdelalv.proyecto_griza.fakes

import com.sebasdelalv.proyecto_griza.data.network.dto.TallerRequest
import com.sebasdelalv.proyecto_griza.domain.model.TallerResult
import com.sebasdelalv.proyecto_griza.domain.repository.TallerRepository

class FakeTallerRepository: TallerRepository {
    override suspend fun getAll(token: String): Result<List<TallerResult>> {
        TODO("Not yet implemented")
    }

    override suspend fun updateTaller(
        token: String,
        id: String,
        taller: TallerRequest
    ): Result<TallerResult> {
        TODO("Not yet implemented")
    }

    override suspend fun insertTaller(
        token: String,
        taller: TallerRequest
    ): Result<TallerResult> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTaller(
        token: String,
        id: String
    ): Result<Unit> {
        TODO("Not yet implemented")
    }
}