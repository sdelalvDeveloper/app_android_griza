package com.sebasdelalv.proyecto_griza.data.network.dto

data class UpdatePasswordRequest(
    val username: String,
    val password: String,
    val newPassword: String
)
