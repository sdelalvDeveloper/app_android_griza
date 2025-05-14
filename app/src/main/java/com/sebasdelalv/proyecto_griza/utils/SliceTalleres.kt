package com.sebasdelalv.proyecto_griza.utils

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sebasdelalv.proyecto_griza.data.taller.Taller
import com.sebasdelalv.proyecto_griza.ui.theme.Quicksand
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SliceTalleres(talleres: List<Taller>, screenSizes: Int) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy((screenSizes * 0.04f).dp),
        contentPadding = PaddingValues((screenSizes * 0.02f).dp)
    ) {
        items(talleres) { taller ->

            val dia = taller.fecha.format(DateTimeFormatter.ofPattern("dd"))
            val mes = taller.fecha.format(DateTimeFormatter.ofPattern("MMM"))
            val hora = taller.fecha.format(DateTimeFormatter.ofPattern("HH:mm"))

            Card(
                modifier = Modifier
                    .width((screenSizes * 0.4f).dp)
                    .height((screenSizes * 0.3f).dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = taller.nombre,
                        fontFamily = Quicksand,
                        fontWeight = FontWeight.Bold,
                        fontSize = (screenSizes * 0.04f).sp,
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
                            TextStyleTaller(dia, screenSizes)
                            TextStyleTaller(mes, screenSizes)

                        }
                        TextStyleTaller(hora, screenSizes)
                    }
                }
            }
        }
    }
}
