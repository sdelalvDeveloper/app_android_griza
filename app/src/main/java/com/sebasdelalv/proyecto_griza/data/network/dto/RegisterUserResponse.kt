package com.sebasdelalv.proyecto_griza.data.network.dto

data class RegisterUserResponse(
    val username: String,
    val email: String,
    val telefono: String,
    val bono: Int
)
