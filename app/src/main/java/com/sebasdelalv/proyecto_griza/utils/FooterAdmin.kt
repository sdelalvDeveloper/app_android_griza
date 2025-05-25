package com.sebasdelalv.proyecto_griza.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.sebasdelalv.proyecto_griza.ui.theme.Principal

@Composable
fun MyFooterAdmin(
    navigateToMenu: () -> Unit,
    navigateToTalleres: () -> Unit,
    navigateToInfo: () -> Unit
) {
    BottomAppBar(
        containerColor = Principal,
        contentColor = Color.Black,
        modifier = Modifier.fillMaxWidth(),
        content = {
            IconNavigation(
                onClick = { navigateToMenu() },
                modifier = Modifier.weight(1f),
                icon = Icons.Default.Home,
                text = "Inicio"
            )

            IconNavigation(
                onClick = { navigateToTalleres() },
                modifier = Modifier.weight(1f),
                icon = Icons.Default.CalendarMonth,
                text = "Talleres"
            )

            IconNavigation(
                onClick = { navigateToInfo() },
                modifier = Modifier.weight(1f),
                icon = Icons.Default.Place,
                text = "Nosotros"
            )
        }
    )
}