package com.sebasdelalv.proyecto_griza.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sebasdelalv.proyecto_griza.domain.model.ReservaResult
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import com.sebasdelalv.proyecto_griza.data.mapper.capitalizeFirst
import com.sebasdelalv.proyecto_griza.data.mapper.toFechaDesglosada


@Composable
fun ColumnReservas(
    reservas: List<ReservaResult>,
    screenWidth: Int,
    innerPadding: PaddingValues,
    onReservaClick: (ReservaResult) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(top = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(reservas) { reserva ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp)
                    .clickable { onReservaClick(reserva) },
                shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(containerColor = Color.LightGray)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val fechaDesglosada = reserva.fechaTaller.toFechaDesglosada()

                    TextStyleTaller(
                        text = " Taller: ${reserva.tituloTaller.capitalizeFirst()}",
                        screenWidth = screenWidth,
                        Color.Black
                    )

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        TextStyleTaller(text = fechaDesglosada.dia, screenWidth = screenWidth, Color.Black)
                        TextStyleTaller(text = fechaDesglosada.mes, screenWidth = screenWidth, Color.Black)
                    }

                    TextStyleTaller(text = fechaDesglosada.hora, screenWidth = screenWidth, Color.Black)
                }
            }
        }
    }
}
