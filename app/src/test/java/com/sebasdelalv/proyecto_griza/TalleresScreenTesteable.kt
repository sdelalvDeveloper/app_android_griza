package com.sebasdelalv.proyecto_griza

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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
import com.sebasdelalv.proyecto_griza.data.local.ISessionManager
import com.sebasdelalv.proyecto_griza.data.local.SessionManager
import com.sebasdelalv.proyecto_griza.ui.screens.talleres.TalleresViewModel
import com.sebasdelalv.proyecto_griza.ui.theme.Principal
import com.sebasdelalv.proyecto_griza.ui.theme.Quicksand
import com.sebasdelalv.proyecto_griza.utils.ColumnTalleres
import com.sebasdelalv.proyecto_griza.utils.MyFooter
import com.sebasdelalv.proyecto_griza.utils.MyFooterAdmin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TalleresScreenTestable(
    viewModel: TalleresViewModel,
    sessionManager: ISessionManager = SessionManager(LocalContext.current),
    navigateToBack: () -> Unit,
    navigateToMenu: () -> Unit,
    navigateToTalleres: () -> Unit,
    navigateToInfo: () -> Unit,
    navigateToModificarTaller: (String, String) -> Unit,
    navigateToMenuAdmin: () -> Unit
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val screenSizes = LocalConfiguration.current.screenWidthDp

    val talleres by viewModel.talleres.collectAsState()
    val dialogMessage by viewModel.dialogMessage.collectAsState()
    val isDialogOpen by viewModel.isDialogOpen.collectAsState()

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                // Recarga datos cuando la pantalla vuelva a ser visible
                viewModel.getAllTalleres(sessionManager.getToken().orEmpty())
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
                        text = "TALLERES",
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
            if (sessionManager.getRole()?.lowercase() == "admin") {
                MyFooterAdmin(navigateToMenuAdmin, navigateToTalleres, navigateToInfo)
            } else {
                MyFooter(navigateToMenu, navigateToTalleres, navigateToInfo)
            }
        },
        floatingActionButton = {
            if (sessionManager.getRole()?.lowercase() == "admin") {
                FloatingActionButton(
                    onClick = { navigateToModificarTaller("id", "insertar") },
                    containerColor = Principal,
                    contentColor = Color.Black
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Agregar Taller")
                }
            }
        }
    ) { innerPadding ->
        HorizontalDivider(
            color = Color.Black,
            thickness = (screenWidth * 0.004f).dp,
            modifier = Modifier.padding(innerPadding)
        )

        ColumnTalleres(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(top = 8.dp),
            talleres,
            screenSizes,
            sessionManager.getRole().toString(),
            onReservar = { taller ->
                viewModel.insertReserva(
                    token = sessionManager.getToken().toString(),
                    username = sessionManager.getUsername().toString(),
                    tallerId = taller?.id ?: "No existe el taller"
                )
            },
            onNavigateToModificarTaller = navigateToModificarTaller,
            onEliminarTaller = { taller ->
                viewModel.eliminarTaller(
                    token = sessionManager.getToken().toString(),
                    id = taller.id
                )
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
