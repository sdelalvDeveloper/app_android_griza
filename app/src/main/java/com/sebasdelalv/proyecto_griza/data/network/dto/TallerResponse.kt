package com.sebasdelalv.proyecto_griza.data.network.dto

import java.util.Date

enum class EstadoTaller() {
    DISPONIBLE, COMPLETO
}

data class TallerResponse(
    val id: String,
    val titulo: String,
    val descripcion: String,
    val fecha: Date,
    val plazas: Int,
    val estado: EstadoTaller
)
