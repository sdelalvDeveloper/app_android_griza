package com.sebasdelalv.proyecto_griza.ui.screens.perfil

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.sebasdelalv.proyecto_griza.utils.MyFooter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilScreen() {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Column(verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text("Nombre del Usuario")
                        Text("email del usuario ")
                    }
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Perfil",
                        modifier = Modifier
                            .size(screenWidth * 0.15f)
                            .padding(start = screenWidth * 0.05f)
                    )
                },
                actions= {
                    IconButton(
                        onClick = {
                            // Navegar a la cuenta
                        },
                        modifier = Modifier.padding(end = screenWidth * 0.05f)
                    ) {
                        Icon(
                            Icons.Default.KeyboardArrowRight,
                            contentDescription = "Cuenta",
                            modifier = Modifier.size(screenWidth * 0.08f)
                        )
                    }
                }
            )

        },
        bottomBar = {
            MyFooter()
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ){
            HorizontalDivider(
                color = Color.Black,
                thickness = 1.dp,
                modifier = Modifier.padding(top = screenWidth * 0.04f)
            )
        }
    }
}