package com.sebasdelalv.proyecto_griza.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
fun esHoraValida(hora: String): Boolean {
    val formato = SimpleDateFormat("HH:mm")
    formato.isLenient = false
    return try {
        formato.parse(hora)
        true
    } catch (e: Exception) {
        false
    }
}
