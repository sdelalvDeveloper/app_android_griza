package com.sebasdelalv.proyecto_griza.ui.screens.menu

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.sebasdelalv.proyecto_griza.R
import com.sebasdelalv.proyecto_griza.data.session.SessionManager
import com.sebasdelalv.proyecto_griza.utils.CarruselDeImagenes
import com.sebasdelalv.proyecto_griza.utils.MyFooter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(
    viewModel: MenuViewModel,
    navigateToLogin: () -> Unit,
    navigateToPerfil: () -> Unit,
    navigateToMenu: () -> Unit,
    navigateToTalleres: () -> Unit,
    navigateToInfo: () -> Unit
) {
    val context = LocalContext.current
    val sessionManager = remember { SessionManager(context) }

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val imagenes = listOf(
        R.drawable.image_3,
        R.drawable.image_2,
        R.drawable.image_1
    )

    var expanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Nombre del Usuario") },
                navigationIcon = {
                    Box {
                        IconButton(
                            onClick = { expanded = true },
                            modifier = Modifier.padding(start = screenWidth * 0.05f)
                        ) {
                            Icon(
                                Icons.Default.Menu,
                                contentDescription = "Menú",
                                modifier = Modifier.size(screenWidth * 0.08f)
                            )
                        }
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("Perfil") },
                                onClick = {
                                    expanded = false
                                    navigateToPerfil()
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Cerrar sesión") },
                                onClick = {
                                    expanded = false
                                    sessionManager.clearSession()
                                    navigateToLogin()
                                }
                            )
                        }
                    }
                },
                actions = {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Perfil",
                        modifier = Modifier
                            .size(screenWidth * 0.15f)
                            .padding(end = screenWidth * 0.05f)
                    )
                }
            )
        },
        bottomBar = {
            MyFooter(
                navigateToMenu,
                navigateToTalleres,
                navigateToInfo
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            CarruselDeImagenes(imagenes, screenWidth * 0.6f)
        }
    }
}

