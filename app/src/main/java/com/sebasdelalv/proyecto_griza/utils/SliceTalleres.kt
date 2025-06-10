package com.sebasdelalv.proyecto_griza.utils

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sebasdelalv.proyecto_griza.data.mapper.capitalizeFirst
import com.sebasdelalv.proyecto_griza.data.mapper.toFechaDesglosada
import com.sebasdelalv.proyecto_griza.domain.model.TallerResult
import com.sebasdelalv.proyecto_griza.ui.theme.Quicksand
import com.sebasdelalv.proyecto_griza.ui.theme.RojoAlert
import com.sebasdelalv.proyecto_griza.ui.theme.VerdeDialog
import com.sebasdelalv.proyecto_griza.ui.theme.VerdeDisponible

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SliceTalleres(
    talleres: List<TallerResult>,
    screenSizes: Int,
    onReservar: (TallerResult?) -> Unit
    ) {

    var tallerSeleccionado by remember { mutableStateOf<TallerResult?>(null) }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy((screenSizes * 0.04f).dp),
        contentPadding = PaddingValues((screenSizes * 0.02f).dp)
    ) {
        items(talleres) { taller ->
            val fechaDesglosada = taller.fecha.toFechaDesglosada()

            Card(
                modifier = Modifier
                    .width((screenSizes * 0.4f).dp)
                    .height((screenSizes * 0.3f).dp)
                    .clickable { tallerSeleccionado = taller },
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Black.copy(alpha = 0.7f)
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = taller.titulo.capitalizeFirst(),
                        fontFamily = Quicksand,
                        fontWeight = FontWeight.Bold,
                        fontSize = (screenSizes * 0.04f).sp,
                        color = Color.White,
                        maxLines = 2
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            TextStyleTaller(fechaDesglosada.dia, screenSizes, Color.White)
                            TextStyleTaller(fechaDesglosada.mes, screenSizes, Color.White)

                        }
                        TextStyleTaller(fechaDesglosada.hora, screenSizes, Color.White)
                    }

                    Text(
                        text = taller.estado,
                        color = if (taller.estado == "DISPONIBLE") VerdeDisponible else RojoAlert,
                        fontSize = (screenSizes * 0.03f).sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = Quicksand
                    )

                }
            }
        }
    }

    // AlertDialog para confirmar la reserva
    tallerSeleccionado?.let { taller ->
        AlertDialog(
            onDismissRequest = { tallerSeleccionado = null },
            title = { Text("¿Reservar taller?") },
            text = { Text("¿Deseas reservar el taller \"${taller.titulo.capitalizeFirst()}\"?") },
            confirmButton = {
                TextButton(onClick = {
                    onReservar(taller)
                    tallerSeleccionado = null
                }) {
                    Text("Aceptar", color = VerdeDialog)
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    tallerSeleccionado = null
                }) {
                    Text("Cancelar",
                        color = Color(0xFFF44336)
                    )
                }
            }
        )
    }
}
