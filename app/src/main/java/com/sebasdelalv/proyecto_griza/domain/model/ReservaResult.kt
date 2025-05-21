package com.sebasdelalv.proyecto_griza.domain.model

import com.sebasdelalv.proyecto_griza.data.network.dto.EstadoReserva
import java.util.Date

data class ReservaResult(
    val id: String,
    val tituloTaller: String,
    val tallerID: String,
    val estado: EstadoReserva,
    val fechaTaller: Date
)
