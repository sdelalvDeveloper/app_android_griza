package com.sebasdelalv.proyecto_griza.fakes

import com.sebasdelalv.proyecto_griza.domain.model.ReservaResult
import com.sebasdelalv.proyecto_griza.domain.repository.ReservaRepository

class FakeReservaRepository: ReservaRepository {
    override suspend fun insertReserva(
        token: String,
        username: String,
        tallerId: String
    ): Result<ReservaResult> {
        TODO("Not yet implemented")
    }

    override suspend fun getReservaByUsername(
        token: String,
        username: String
    ): Result<List<ReservaResult>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteReservaById(
        token: String,
        id: String,
        tallerID: String
    ): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun getFirst(
        token: String,
        username: String
    ): Result<ReservaResult> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAll(
        token: String,
        username: String
    ): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteReservaByIdTaller(
        token: String,
        tallerID: String
    ): Result<Unit> {
        TODO("Not yet implemented")
    }
}