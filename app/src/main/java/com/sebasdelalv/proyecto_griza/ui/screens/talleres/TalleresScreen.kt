package com.sebasdelalv.proyecto_griza.ui.screens.talleres

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sebasdelalv.proyecto_griza.data.taller.Taller
import com.sebasdelalv.proyecto_griza.ui.theme.Principal
import com.sebasdelalv.proyecto_griza.ui.theme.Quicksand
import com.sebasdelalv.proyecto_griza.utils.MyFooter
import com.sebasdelalv.proyecto_griza.utils.TextStyleTaller
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TalleresScreen(
    viewModel: TalleresViewModel,
    navigateToBack: () -> Unit,
    navigateToMenu: () -> Unit,
    navigateToTalleres: () -> Unit
){
    val screenWidth = LocalConfiguration.current.screenWidthDp

    //val talleres by viewModel.talleres.collectAsState()

    val talleres = listOf(
        Taller(LocalDateTime.of(2024, 5, 10, 10, 0), "Fotografía Básica"),
        Taller(LocalDateTime.of(2024, 5, 12, 15, 0), "Diseño Gráfico"),
        Taller(LocalDateTime.of(2024, 5, 15, 9, 30), "Producción de Video"),
        Taller(LocalDateTime.of(2024, 5, 17, 14, 0), "Guion Audiovisual"),
        Taller(LocalDateTime.of(2024, 5, 20, 16, 30), "Modelado 3D"),
        Taller(LocalDateTime.of(2024, 5, 22, 11, 0), "Edición de Fotografía"),
        Taller(LocalDateTime.of(2024, 5, 25, 13, 0), "Redes Sociales"),
        Taller(LocalDateTime.of(2024, 5, 28, 18, 0), "Música Digital"),
        Taller(LocalDateTime.of(2024, 6, 2, 10, 30), "Animación 2D"),
        Taller(LocalDateTime.of(2024, 6, 5, 17, 45), "Postproducción de Audio"),
        Taller(LocalDateTime.of(2024, 6, 7, 14, 15), "Colorización de Video"),
        Taller(LocalDateTime.of(2024, 6, 10, 9, 0), "Composición Visual"),
        Taller(LocalDateTime.of(2024, 6, 13, 15, 30), "Desarrollo de Portafolio"),
        Taller(LocalDateTime.of(2024, 6, 15, 11, 45), "Dirección de Arte")
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "TALLERES",
                        fontFamily = Quicksand,
                        fontWeight = FontWeight.Bold,
                        fontSize = (screenWidth * 0.06f).sp
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navigateToBack()
                        },
                        modifier = Modifier.padding(start = (screenWidth * 0.05f).dp)
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = "volver atras",
                            modifier = Modifier.size((screenWidth * 0.08f).dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Principal
                )
            )
        },
        bottomBar = {
            MyFooter(navigateToMenu, navigateToTalleres)
        }
    ) { innerPadding ->
        HorizontalDivider(
            color = Color.Black,
            thickness = (screenWidth * 0.004f).dp,
            modifier = Modifier.padding(innerPadding)
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(top = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(talleres) { taller ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp) // Margen horizontal alrededor del card
                        .padding(vertical = 6.dp), // Margen vertical
                    shape = RoundedCornerShape(8.dp), // Bordes redondeados
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp), // Sombra del card
                    colors = CardDefaults.cardColors(containerColor = Color.LightGray)

                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp), // Padding interno del card
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val dia = taller.fecha.format(DateTimeFormatter.ofPattern("dd"))
                        val mes = taller.fecha.format(DateTimeFormatter.ofPattern("MMMM"))
                        val hora = taller.fecha.format(DateTimeFormatter.ofPattern("HH:mm"))

                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            TextStyleTaller(text = dia, screenWidth = screenWidth)
                            TextStyleTaller(text = mes, screenWidth = screenWidth)
                        }
                        TextStyleTaller(text = hora, screenWidth = screenWidth)
                        TextStyleTaller(text = taller.nombre, screenWidth = screenWidth)
                    }
                }
            }
        }
    }
}