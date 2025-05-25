package com.sebasdelalv.proyecto_griza.ui.screens.reservas

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.sebasdelalv.proyecto_griza.data.mapper.capitalizeFirst
import com.sebasdelalv.proyecto_griza.domain.model.ReservaResult
import com.sebasdelalv.proyecto_griza.ui.theme.Principal
import com.sebasdelalv.proyecto_griza.ui.theme.Quicksand
import com.sebasdelalv.proyecto_griza.utils.ColumnReservas
import com.sebasdelalv.proyecto_griza.utils.MyFooter

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ReservasScreen(
    viewModel: ReservasViewModel,
    navigateToBack: () -> Unit,
    navigateToMenu: () -> Unit,
    navigateToTalleres: () -> Unit,
    navigateToInfo: () -> Unit
){
    val context = LocalContext.current
    val sessionManager = remember { SessionManager(context) }
    val screenWidth = LocalConfiguration.current.screenWidthDp

    val reservas by viewModel.reservas.collectAsState()
    val dialogMessage by viewModel.dialogMessage.collectAsState()
    val isDialogOpen by viewModel.isDialogOpen.collectAsState()

    var reservaSeleccionada by remember { mutableStateOf<ReservaResult?>(null) }

    // Observa cambios de ciclo de vida (como volver a esta pantalla)
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                // Recargar reservas al volver a esta pantalla
                viewModel.getReservasUsuario(
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
                    Text(
                        text = "RESERVAS",
                        fontFamily = Quicksand,
                        fontWeight = FontWeight.Bold,
                        fontSize = (screenWidth * 0.06f).sp
                    )
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
                            contentDescription = "volver atras",
                            modifier = Modifier.size((screenWidth * 0.08f).dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Principal
                )
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
        ColumnReservas(
            reservas = reservas,
            screenWidth = screenWidth,
            innerPadding = innerPadding,
            onReservaClick = { reservaSeleccionada = it }
        )
    }
    reservaSeleccionada?.let { reserva ->
        AlertDialog(
            onDismissRequest = { reservaSeleccionada = null },
            title = { Text("Eliminar reserva") },
            text = { Text("¿Deseas eliminar la reserva del taller \"${reserva.tituloTaller.capitalizeFirst()}\"?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.eliminarReserva(
                            sessionManager.getToken().toString(),
                            reserva
                        )
                        reservaSeleccionada = null
                    }
                ) {
                    Text("Eliminar", color = Color.Red)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { reservaSeleccionada = null }
                ) {
                    Text("Salir")
                }
            }
        )
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