package com.sebasdelalv.proyecto_griza.data.network.dto

import java.util.Date

enum class EstadoReserva {
    ACTIVA, CANCELADA
}

data class ReservaResponse(
    val id: String,
    val username: String,
    val tituloTaller: String,
    val tallerID: String,
    val estado: EstadoReserva,
    val fechaTaller: Date
)
