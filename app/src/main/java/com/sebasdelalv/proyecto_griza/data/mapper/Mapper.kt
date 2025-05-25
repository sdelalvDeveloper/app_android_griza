package com.sebasdelalv.proyecto_griza.data.mapper


import android.util.Log
import com.sebasdelalv.proyecto_griza.data.network.dto.LoginResponse
import com.sebasdelalv.proyecto_griza.data.network.dto.RegisterUserResponse
import com.sebasdelalv.proyecto_griza.data.network.dto.ReservaResponse
import com.sebasdelalv.proyecto_griza.data.network.dto.TallerResponse
import com.sebasdelalv.proyecto_griza.domain.model.FechaDesglosada
import com.sebasdelalv.proyecto_griza.domain.model.LoginResult
import com.sebasdelalv.proyecto_griza.domain.model.RegisterResult
import com.sebasdelalv.proyecto_griza.domain.model.ReservaResult
import com.sebasdelalv.proyecto_griza.domain.model.TallerResult
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun LoginResponse.toDomain(): LoginResult {
    return LoginResult(
        token = token,
        role = role
    )
}

fun RegisterUserResponse.toDomain(): RegisterResult {
    return RegisterResult(
        username,
        email,
        telefono,
        bono
    )
}

fun Date.toFechaDesglosada(): FechaDesglosada {
    val diaFormat = SimpleDateFormat("dd", Locale.getDefault())
    val mesFormat = SimpleDateFormat("MMM", Locale.getDefault())
    val horaFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

    return FechaDesglosada(
        dia = diaFormat.format(this),
        mes = mesFormat.format(this),
        hora = horaFormat.format(this)
    )
}

fun List<TallerResponse>.toTallerDomain(): List<TallerResult> {
    return this.map { it.toDomain() }
}


fun TallerResponse.toDomain(): TallerResult {
    return TallerResult(
        id,
        titulo,
        descripcion,
        fecha,
        plazas,
        estado.toString()
    )
}

fun ReservaResponse.toDomain(): ReservaResult {
    return ReservaResult(
        id,
        username,
        tituloTaller,
        tallerID,
        estado,
        fechaTaller
    )
}

fun List<ReservaResponse>.toReservaDomain(): List<ReservaResult> {
    return this.map { it.toDomain() }
}

fun String.capitalizeFirst(): String {
    return this.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase() else it.toString()
    }
}

