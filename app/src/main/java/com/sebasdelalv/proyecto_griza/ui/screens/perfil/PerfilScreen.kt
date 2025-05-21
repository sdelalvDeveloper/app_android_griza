package com.sebasdelalv.proyecto_griza.ui.screens.perfil

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.sebasdelalv.proyecto_griza.data.local.SessionManager
import com.sebasdelalv.proyecto_griza.data.mapper.toFechaDesglosada
import com.sebasdelalv.proyecto_griza.ui.theme.Principal
import com.sebasdelalv.proyecto_griza.ui.theme.Quicksand
import com.sebasdelalv.proyecto_griza.utils.MyFooter
import com.sebasdelalv.proyecto_griza.utils.PerfilActionRow
import com.sebasdelalv.proyecto_griza.utils.TextStyleTaller

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilScreen(
    viewModel: PerfilViewModel,
    navigateToBack: () -> Unit,
    navigateToMenu: () ->Unit,
    navigateToTalleres: () -> Unit,
    navigateToInfo: () -> Unit,
    navigateToReservas: () -> Unit,
    navigateToInfoPersonal: () -> Unit,
    navigateToEliminarCuenta: () -> Unit
) {
    val context = LocalContext.current
    val sessionManager = remember { SessionManager(context) }
    val screenWidth = LocalConfiguration.current.screenWidthDp

    val saldo by viewModel.saldo.collectAsState()
    val reserva by viewModel.reserva.collectAsState()
    val dialogTitle by viewModel.dialogTitle.collectAsState()
    val dialogMessage by viewModel.dialogMessage.collectAsState()
    val isDialogOpen by viewModel.isDialogOpen.collectAsState()

    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                // Cada vez que volvamos a esta pantalla:
                val token = sessionManager.getToken() ?: ""
                val username = sessionManager.getUsername() ?: ""
                viewModel.loadSaldo(username, token)
                viewModel.getFirstReserva(
                    sessionManager.getToken().orEmpty(),
                    sessionManager.getUsername().orEmpty()
                )
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text("${sessionManager.getUsername()}")
                    }
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
                            contentDescription = "Cuenta",
                            modifier = Modifier.size((screenWidth * 0.08f).dp)
                        )
                    }
                },
                actions= {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Perfil",
                        modifier = Modifier
                            .size((screenWidth * 0.15f).dp)
                            .padding(end = (screenWidth * 0.05f).dp)
                    )
                }
            )
        },
        bottomBar = {
            MyFooter(navigateToMenu, navigateToTalleres, navigateToInfo)
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
        ){
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height((screenWidth * 0.4f).dp)
                    .padding((screenWidth * 0.05f).dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Principal),
                border = BorderStroke(1.dp, Color.Black),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = "Próxima cita",
                        fontFamily = Quicksand,
                        fontWeight = FontWeight.Bold,
                        fontSize = (screenWidth * 0.06f).sp
                    )
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (reserva == null) {
                            TextStyleTaller("$dialogMessage", screenWidth, Color.Black)
                        } else {
                            val fechaDesglosada = reserva!!.fechaTaller.toFechaDesglosada()
                            Text(
                                text = fechaDesglosada.dia,
                                fontFamily = Quicksand,
                                fontWeight = FontWeight.Bold,
                                fontSize = (screenWidth * 0.03f).sp
                            )
                            Text(
                                text = fechaDesglosada.mes,
                                fontFamily = Quicksand,
                                fontWeight = FontWeight.Bold,
                                fontSize = (screenWidth * 0.03f).sp
                            )
                            Text(
                                text = fechaDesglosada.hora,
                                fontFamily = Quicksand,
                                fontWeight = FontWeight.Bold,
                                fontSize = (screenWidth * 0.03f).sp
                            )
                        }
                    }
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height((screenWidth * 0.3f).dp)
                    .padding((screenWidth * 0.04f).dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Principal),
                border = BorderStroke(1.dp, Color.Black),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = "Saldo disponible",
                        fontFamily = Quicksand,
                        fontWeight = FontWeight.Bold,
                        fontSize = (screenWidth * 0.04f).sp
                    )

                    Text(
                        text = "$saldo",
                        fontFamily = Quicksand,
                        fontWeight = FontWeight.Bold,
                        fontSize = (screenWidth * 0.04f).sp
                    )
                }
            }

            PerfilActionRow(
                "Reservas",
                "reservas",
                screenWidth,
                navigateToReservas
            )

            PerfilActionRow(
                "Información personal",
                "info personal",
                screenWidth,
                navigateToInfoPersonal
            )

            PerfilActionRow(
                "Cambiar contraseña",
                "cambiar password",
                screenWidth,
                navigateToInfoPersonal
            )

            PerfilActionRow(
                "Eliminar cuenta",
                "eliminar cuenta",
                screenWidth,
                navigateToEliminarCuenta
            )
        }
    }
    if (isDialogOpen) {
        AlertDialog(
            onDismissRequest = { viewModel.closeDialog() },
            title = { Text(dialogTitle.toString()) },
            text = { Text(dialogMessage ?: "") },
            confirmButton = {
                Button(onClick = { viewModel.closeDialog() }) {
                    Text("Aceptar")
                }
            }
        )
    }
}