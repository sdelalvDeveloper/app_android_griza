package com.sebasdelalv.proyecto_griza.utils

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun SliceImages(imagenes: List<Int>, height: Dp) {
    val pagerState = rememberPagerState(
        initialPage = 0,  // Empezamos en la primera imagen
        pageCount = {imagenes.size}  // Número total de imágenes
    )

    // Deslizar automáticamente cada 4 segundos
    LaunchedEffect(pagerState) {
        while (true) {
            delay(4000)  // Espera 4 segundos antes de avanzar
            val nextPage = pagerState.currentPage + 1  // Avanza a la siguiente página

            if (nextPage < imagenes.size) {
                pagerState.animateScrollToPage(nextPage)  // Avanza con animación
            } else {
                // Cuando lleguemos al final, ir a la primera imagen sin animación
                pagerState.scrollToPage(0)  // Cambia sin animación
            }
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
        ) { page ->
            Image(
                painter = painterResource(id = imagenes[page]),
                contentDescription = "Imagen $page",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height)
            )
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            repeat(imagenes.size) { index ->
                val isSelected = pagerState.currentPage % imagenes.size == index
                Canvas(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(if (isSelected) 10.dp else 8.dp)
                ) {
                    drawCircle(color = if (isSelected) Color.Black else Color.LightGray)
                }
            }
        }
    }
}

