package com.sebasdelalv.proyecto_griza.domain.model

import com.sebasdelalv.proyecto_griza.data.network.dto.EstadoReserva
import java.util.Date

data class ReservaResult(
    val username: String,
    val estado: EstadoReserva,
    val fecha: Date
)
