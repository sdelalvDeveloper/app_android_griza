package com.sebasdelalv.proyecto_griza.ui.screens.menuAdmin

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sebasdelalv.proyecto_griza.R
import com.sebasdelalv.proyecto_griza.data.local.SessionManager
import com.sebasdelalv.proyecto_griza.ui.theme.Principal
import com.sebasdelalv.proyecto_griza.ui.theme.Quicksand
import com.sebasdelalv.proyecto_griza.utils.MyFooterAdmin

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuAdminScreen(
    viewModel: MenuAdminViewModel,
    navigateToLogin: () -> Unit,
    navigateToMenuAdmin: () -> Unit,
    navigateToTalleres: () -> Unit,
    navigateToReservas: () -> Unit,
    navigateToInfo: () -> Unit
) {
    val context = LocalContext.current
    val sessionManager = remember { SessionManager(context) }

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenSizes = LocalConfiguration.current.screenWidthDp

    val dialogMessage by viewModel.dialogMessage.collectAsState()
    val isDialogOpen by viewModel.isDialogOpen.collectAsState()

    var expanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Image(
                        painter = painterResource(id = R.drawable.griza_negro_sin_fondo),
                        contentDescription = "Logo griza estudio",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size((screenSizes * 0.5f).dp)
                    )
                },
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
                    Text(
                        text = "Admin",
                        fontSize = (screenSizes * 0.04f).sp,
                        modifier = Modifier
                            .padding(end = screenWidth * 0.05f)
                    )
                }
            )
        },
        bottomBar = {
            MyFooterAdmin(
                navigateToMenuAdmin,
                navigateToTalleres,
                navigateToInfo
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height((screenWidth * 0.3f))
                    .padding((screenWidth * 0.05f))
                    .clickable {

                    },
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Principal),
                border = BorderStroke(1.dp, Color.Black),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Usuarios",
                        fontFamily = Quicksand,
                        fontWeight = FontWeight.Bold,
                        fontSize = (screenSizes * 0.06f).sp
                    )
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height((screenWidth * 0.3f))
                    .padding((screenWidth * 0.05f))
                    .clickable {
                        navigateToTalleres()
                    },
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Principal),
                border = BorderStroke(1.dp, Color.Black),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Talleres",
                        fontFamily = Quicksand,
                        fontWeight = FontWeight.Bold,
                        fontSize = (screenSizes * 0.06f).sp
                    )
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height((screenWidth * 0.3f))
                    .padding((screenWidth * 0.05f))
                    .clickable {
                        navigateToReservas()
                    },
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Principal),
                border = BorderStroke(1.dp, Color.Black),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Reservas",
                        fontFamily = Quicksand,
                        fontWeight = FontWeight.Bold,
                        fontSize = (screenSizes * 0.06f).sp
                    )
                }
            }
        }
    }

    if (isDialogOpen) {
        AlertDialog(
            onDismissRequest = { viewModel.closeErrorDialog() },
            title = { Text("Error") },
            text = { Text(dialogMessage ?: "") },
            confirmButton = {
                Button(onClick = { viewModel.closeErrorDialog() }) {
                    Text("Aceptar")
                }
            }
        )
    }
}