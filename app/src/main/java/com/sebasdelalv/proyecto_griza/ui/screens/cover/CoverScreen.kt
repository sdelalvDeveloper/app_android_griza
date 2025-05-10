package com.sebasdelalv.proyecto_griza.ui.screens.cover

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import com.sebasdelalv.proyecto_griza.R
import com.sebasdelalv.proyecto_griza.ui.theme.Principal
import kotlinx.coroutines.delay

@Composable
fun CoverScreen(navigateToHome:()-> Unit){
    // Navegación después de 1 segundo
    LaunchedEffect(Unit) {
        delay(1000)
        navigateToHome()
    }

    Box(modifier = Modifier.fillMaxSize(1f).testTag("portada")
        .background(color = Principal),
        contentAlignment = Alignment.Center
    ){
        Image(
            painter = painterResource(id = R.drawable.griza_negro_sin_fondo),
            contentDescription = "Logo griza estudio",
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxWidth(0.8f).fillMaxHeight(0.4f)
        )
    }
}
