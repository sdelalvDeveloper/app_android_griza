package com.sebasdelalv.proyecto_griza.ui.screens.menu

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.sebasdelalv.proyecto_griza.R
import com.sebasdelalv.proyecto_griza.data.local.SessionManager
import com.sebasdelalv.proyecto_griza.data.mapper.toFechaDesglosada
import com.sebasdelalv.proyecto_griza.ui.theme.Principal
import com.sebasdelalv.proyecto_griza.ui.theme.Quicksand
import com.sebasdelalv.proyecto_griza.utils.MyFooter
import com.sebasdelalv.proyecto_griza.utils.SliceImages
import com.sebasdelalv.proyecto_griza.utils.SliceTalleres
import com.sebasdelalv.proyecto_griza.utils.TextStyleTaller

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(
    navigateToLogin: () -> Unit,
    navigateToPerfil: () -> Unit,
    navigateToMenu: () -> Unit,
    navigateToTalleres: () -> Unit,
    navigateToInfo: () -> Unit,
    navigateToReservas: () -> Unit
) {
    val viewModel: MenuViewModel = hiltViewModel()
    val context = LocalContext.current
    val sessionManager = remember { SessionManager(context) }

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenSizes = LocalConfiguration.current.screenWidthDp
    val imagenes = listOf(
        R.drawable.image_3,
        R.drawable.image_2,
        R.drawable.image_1
    )

    val talleres by viewModel.talleres.collectAsState()
    val reserva by viewModel.reserva.collectAsState()
    val dialogMessage by viewModel.dialogMessage.collectAsState()
    val isDialogOpen by viewModel.isDialogOpen.collectAsState()

    var expanded by remember { mutableStateOf(false) }

    val toastMessage by viewModel.toastMessage.collectAsState()

    // Mostrar Toast
    LaunchedEffect(toastMessage) {
        toastMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            viewModel.toastShown()
        }
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                // Recarga datos cuando la pantalla vuelva a ser visible
                viewModel.getAllTalleres(sessionManager.getToken().orEmpty())
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
                title = { Text(sessionManager.getUsername().toString()) },
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
                                text = { Text("Reservas") },
                                onClick = {
                                    expanded = false
                                    navigateToReservas()
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
                .padding(innerPadding),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            SliceImages(imagenes, screenWidth * 0.6f)
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height((screenWidth * 0.4f))
                    .padding((screenWidth * 0.05f)),
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
                        fontSize = (screenSizes * 0.06f).sp
                    )
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (reserva == null) {
                            TextStyleTaller("$dialogMessage", screenSizes, Color.Black)
                        } else {
                            val fechaDesglosada = reserva!!.fechaTaller.toFechaDesglosada()
                            Text(
                                text = fechaDesglosada.dia,
                                fontFamily = Quicksand,
                                fontWeight = FontWeight.Bold,
                                fontSize = (screenSizes * 0.03f).sp
                            )
                            Text(
                                text = fechaDesglosada.mes,
                                fontFamily = Quicksand,
                                fontWeight = FontWeight.Bold,
                                fontSize = (screenSizes * 0.03f).sp
                            )
                            Text(
                                text = fechaDesglosada.hora,
                                fontFamily = Quicksand,
                                fontWeight = FontWeight.Bold,
                                fontSize = (screenSizes * 0.03f).sp
                            )
                        }
                    }
                }
            }
            Text(
                text = "Próximos talleres",
                fontFamily = Quicksand,
                fontWeight = FontWeight.Bold,
                fontSize = (screenSizes * 0.05f).sp,
                modifier = Modifier.padding(horizontal = (screenSizes * 0.05f).dp)
            )

            SliceTalleres(
                talleres,
                screenSizes,
                onReservar = { taller ->
                    viewModel.insertReserva(
                        token = sessionManager.getToken().toString(),
                        username = sessionManager.getUsername().toString(),
                        tallerId = taller?.id ?: "No existe el taller"
                    )
                    viewModel.getFirstReserva(sessionManager.getToken().toString(), sessionManager.getUsername().toString())
                }
            )
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

