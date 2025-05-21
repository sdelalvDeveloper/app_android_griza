package com.sebasdelalv.proyecto_griza.domain.model

import java.util.Date

data class TallerResult(
    val id: String,
    val titulo: String,
    val descripcion: String,
    val fecha: Date,
    val plazas: Int,
    val estado: String
)
