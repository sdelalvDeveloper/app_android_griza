package com.sebasdelalv.proyecto_griza.ui.screens.cuenta

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.sebasdelalv.proyecto_griza.utils.CuentaActionRow
import com.sebasdelalv.proyecto_griza.utils.MyFooter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CuentaScreen(
    navigateToBack: () -> Unit,
    navigateToMenu: () ->Unit,
    navigateToTalleres: () -> Unit
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "navegar atras perfil",
                        modifier = Modifier
                            .size((screenWidth * 0.15f).dp)
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navigateToBack()
                        },
                        modifier = Modifier.padding(end = (screenWidth * 0.05f).dp)
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = "Cuenta",
                            modifier = Modifier.size((screenWidth * 0.08f).dp)
                        )
                    }
                }
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
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            CuentaActionRow(
                "Información personal",
                "info personal",
                screenWidth
            )

            CuentaActionRow(
                "Cambiar contraseña",
                "cambiar password",
                screenWidth
            )

            CuentaActionRow(
                "Notificaciones",
                "notificaciones",
                screenWidth
            )

            CuentaActionRow(
                "Eliminar cuenta",
                "eliminar cuenta",
                screenWidth
            )
        }
    }
}