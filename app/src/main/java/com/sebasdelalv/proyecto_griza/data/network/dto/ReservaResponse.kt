package com.sebasdelalv.proyecto_griza.data.network.dto

import java.util.Date

enum class EstadoReserva {
    ACTIVA, CANCELADA
}

data class ReservaResponse(
    val username: String,
    val estado: EstadoReserva,
    val fecha: Date
)
