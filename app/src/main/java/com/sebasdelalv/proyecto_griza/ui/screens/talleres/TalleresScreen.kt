package com.sebasdelalv.proyecto_griza.ui.screens.talleres

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
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
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.sebasdelalv.proyecto_griza.R
import com.sebasdelalv.proyecto_griza.data.local.SessionManager
import com.sebasdelalv.proyecto_griza.data.mapper.capitalizeFirst
import com.sebasdelalv.proyecto_griza.data.mapper.toFechaDesglosada
import com.sebasdelalv.proyecto_griza.data.taller.Taller
import com.sebasdelalv.proyecto_griza.ui.theme.Principal
import com.sebasdelalv.proyecto_griza.ui.theme.Quicksand
import com.sebasdelalv.proyecto_griza.utils.ColumnTalleres
import com.sebasdelalv.proyecto_griza.utils.MyFooter
import com.sebasdelalv.proyecto_griza.utils.SliceTalleres
import com.sebasdelalv.proyecto_griza.utils.TextStyleTaller
import java.time.LocalDateTime
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TalleresScreen(
    viewModel: TalleresViewModel,
    navigateToBack: () -> Unit,
    navigateToMenu: () -> Unit,
    navigateToTalleres: () -> Unit,
    navigateToInfo: () -> Unit
){
    val screenWidth = LocalConfiguration.current.screenWidthDp

    val context = LocalContext.current
    val sessionManager = remember { SessionManager(context) }
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
            MyFooter(navigateToMenu, navigateToTalleres, navigateToInfo)
        }
    ) { innerPadding ->
        HorizontalDivider(
            color = Color.Black,
            thickness = (screenWidth * 0.004f).dp,
            modifier = Modifier.padding(innerPadding)
        )

        ColumnTalleres(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(top = 8.dp),
            talleres,
            screenSizes,
            onReservar = { taller ->
                viewModel.insertReserva(
                    token = sessionManager.getToken().toString(),
                    username = sessionManager.getUsername().toString(),
                    tallerId = taller?.id ?: "No existe el taller"
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