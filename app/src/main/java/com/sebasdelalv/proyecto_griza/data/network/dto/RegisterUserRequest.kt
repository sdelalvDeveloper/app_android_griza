package com.sebasdelalv.proyecto_griza.data.network.dto

data class RegisterUserRequest(
    val username: String,
    val email: String,
    val telefono: String,
    val password: String,
    val passwordRepeat: String
)
