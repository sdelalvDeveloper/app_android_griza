package com.sebasdelalv.proyecto_griza.data.network.dto

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("message")
    val message: String?
)


