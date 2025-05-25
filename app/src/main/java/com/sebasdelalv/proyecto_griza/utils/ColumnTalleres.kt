package com.sebasdelalv.proyecto_griza.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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

@Composable
fun ColumnTalleres(
    modifier: Modifier,
    talleres: List<TallerResult>,
    screenSizes: Int,
    role: String,
    onReservar: (TallerResult?) -> Unit,
    onNavigateToModificarTaller: () -> Unit
) {

    var tallerSeleccionado by remember { mutableStateOf<TallerResult?>(null) }

    LazyColumn(
        modifier = modifier
    ) {
        items(talleres) { taller ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp) // Margen horizontal alrededor del card
                    .padding(vertical = 6.dp)
                    .clickable { tallerSeleccionado = taller },
                shape = RoundedCornerShape(8.dp), // Bordes redondeados
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp), // Sombra del card
                colors = CardDefaults.cardColors(containerColor = Color.LightGray)

            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding((screenSizes * 0.05f).dp),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = taller.titulo.capitalizeFirst(),
                        fontFamily = Quicksand,
                        fontWeight = FontWeight.Bold,
                        fontSize = (screenSizes * 0.05f).sp,
                        color = Color.Black,
                        maxLines = 2
                    )
                    Text(
                        text = taller.descripcion.capitalizeFirst(),
                        fontFamily = Quicksand,
                        fontWeight = FontWeight.Bold,
                        fontSize = (screenSizes * 0.04f).sp,
                        color = Color.Black,
                        maxLines = 2
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = (screenSizes * 0.03).dp, vertical = (screenSizes * 0.02).dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val fechaDesglosada = taller.fecha.toFechaDesglosada()

                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            TextStyleTaller(text = fechaDesglosada.dia, screenWidth = screenSizes, Color.Black)
                            TextStyleTaller(text = fechaDesglosada.mes, screenWidth = screenSizes, Color.Black)
                        }
                        TextStyleTaller(text = fechaDesglosada.hora, screenWidth = screenSizes, Color.Black)
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = taller.estado,
                            color = if (taller.estado == "DISPONIBLE") Color(0xFF4CAF50) else Color(0xFFF44336),
                            fontSize = (screenSizes * 0.03f).sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = Quicksand
                        )

                        Text(
                            text = "Plazas: ${taller.plazas}",
                            fontSize = (screenSizes * 0.03f).sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black,
                            fontFamily = Quicksand
                        )
                    }
                }
            }
        }
    }
    // AlertDialog para confirmar la reserva
    tallerSeleccionado?.let { taller ->
        if (role.lowercase() == "admin") {
            TallerOpcionDialogAdmin(
                taller = taller,
                onDismiss = { tallerSeleccionado = null },
                onModificar = { onNavigateToModificarTaller() },
                onEliminar = {  }
            )
        } else {
            ReservarTallerDialog(
                taller = taller,
                onReservar = onReservar,
                onDismiss = { tallerSeleccionado = null }
            )
        }
    }

}