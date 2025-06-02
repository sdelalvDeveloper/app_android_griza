package com.sebasdelalv.proyecto_griza.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
fun esFechaValida(fecha: String): Boolean {
    val formato = SimpleDateFormat("dd/MM/yyyy")
    formato.isLenient = false // No permite fechas inválidas como 32/01/2024
    return try {
        formato.parse(fecha)
        true
    } catch (e: Exception) {
        false
    }
}
