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
fun CarruselDeImagenes(imagenes: List<Int>, height: Dp) {
    // Añadir una imagen duplicada al final para bucle visual
    val loopedImages = remember { imagenes + imagenes.first() }

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { loopedImages.size }
    )

    LaunchedEffect(pagerState) {
        while (true) {
            delay(4000)
            val nextPage = pagerState.currentPage + 1

            if (nextPage < loopedImages.size) {
                pagerState.animateScrollToPage(nextPage)
            }

            // Si llega al final, salta sin animación a la primera
            if (nextPage == loopedImages.lastIndex) {
                pagerState.scrollToPage(0)
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
                painter = painterResource(id = loopedImages[page]),
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

