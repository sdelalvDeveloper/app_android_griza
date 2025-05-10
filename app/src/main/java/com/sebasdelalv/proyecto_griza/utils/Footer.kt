package com.sebasdelalv.proyecto_griza.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.sebasdelalv.proyecto_griza.ui.theme.Principal

@Composable
fun MyFooter() {
    BottomAppBar(
        containerColor = Principal,
        contentColor = Color.Black,
        modifier = Modifier.fillMaxWidth(),
        content = {
            IconNavigation(
                onClick = { /* Navegar a Inicio */ },
                modifier = Modifier.weight(1f),
                icon = Icons.Default.Home,
                text = "Inicio"
            )

            IconNavigation(
                onClick = { /* Navegar a Buscar */ },
                modifier = Modifier.weight(1f),
                icon = Icons.AutoMirrored.Filled.List,
                text = "Talleres"
            )

            IconNavigation(
                onClick = { /* Navegar a Perfil */ },
                modifier = Modifier.weight(1f),
                icon = Icons.Default.Place,
                text = "Nosotros"
            )
        }
    )
}