package com.sebasdelalv.proyecto_griza.ui.screens.talleres

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sebasdelalv.proyecto_griza.data.local.SessionManager
import com.sebasdelalv.proyecto_griza.ui.theme.Principal
import com.sebasdelalv.proyecto_griza.ui.theme.Quicksand
import com.sebasdelalv.proyecto_griza.utils.TextInput

@Composable
fun ModificarTalleresScreen(
    id: String,
    tipoScreen: String,
    navigateToBack: () -> Unit,
    navigateToTalleres: () -> Unit
) {
    val viewModel: ModificarTalleresViewModel = hiltViewModel()
    val context = LocalContext.current
    val sessionManager = remember { SessionManager(context) }
    val screenWidth = LocalConfiguration.current.screenWidthDp

    val titulo by viewModel.titulo.collectAsState()
    val descripcion by viewModel.descripcion.collectAsState()
    val fecha by viewModel.fecha.collectAsState()
    val hora by viewModel.hora.collectAsState()

    val enabledButton by viewModel.isButtonEnabled.collectAsState()

    val dialogTitle by viewModel.dialogTitle.collectAsState()
    val dialogMessage by viewModel.dialogMessage.collectAsState()
    val isDialogOpen by viewModel.isDialogOpen.collectAsState()

    val isConfirmDialogOpen by viewModel.isConfirmDialogOpen.collectAsState()

    val toastMessage by viewModel.toastMessage.collectAsState()
    val shouldNavigate by viewModel.shouldNavigate.collectAsState()

    // Mostrar Toast
    LaunchedEffect(toastMessage) {
        toastMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            viewModel.toastShown()
        }
    }

    // Navegar después del Toast
    LaunchedEffect(shouldNavigate) {
        if (shouldNavigate) {
            navigateToTalleres()
            viewModel.onNavigated()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(1f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextInput(titulo.toString(), "Título del taller") { viewModel.onTituloChanged(it)}
        Spacer(modifier = Modifier.fillMaxHeight(0.05f))
        TextInput(descripcion.toString(), "Descripción") { viewModel.onDescripcionChanged(it) }
        Spacer(modifier = Modifier.fillMaxHeight(0.05f))
        Column {
            TextInput(fecha.toString(), "Fecha") { viewModel.onFechaChanged(it) }
            Text("Formato: dd/mm/aaaa", color = Color.Gray)
        }
        Spacer(modifier = Modifier.fillMaxHeight(0.05f))
        Column {
            TextInput(hora.toString(), "Hora") { viewModel.onHoraChanged(it) }
            Text("Formato: hh:mm", color = Color.Gray)
        }

        Spacer(modifier = Modifier.fillMaxHeight(0.1f))
        Button(
            onClick = { viewModel.openConfirmDialog() },
            enabled = enabledButton,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (enabledButton) Principal else Color.LightGray,
                contentColor = Color.Black
            )
        ) {
            Text(
                text = if (tipoScreen == "modificar") "Actualizar" else "Registrar",
                fontFamily = Quicksand,
                fontWeight = FontWeight.Bold,
                fontSize = (screenWidth * 0.04f).sp
            )
        }
        Spacer(modifier = Modifier.fillMaxHeight(0.1f))
        Button(
            onClick = {
                navigateToBack()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Principal,
                contentColor = Color.Black
            )
        ) {
            Text(
                text = "Volver",
                fontFamily = Quicksand,
                fontWeight = FontWeight.Bold,
                fontSize = (screenWidth * 0.04f).sp
            )
        }
    }

    if (isConfirmDialogOpen) {
        AlertDialog(
            onDismissRequest = { viewModel.closeConfirmDialog() },
            title = { Text(if(tipoScreen == "modificar") "Confirmar actualización" else "Confirmar registro") },
            text = { Text(if(tipoScreen == "modificar") "¿Estás seguro de que deseas modificar el taller?" else "¿Estás seguro que deseas registrar el taller?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.closeConfirmDialog()
                        if(tipoScreen == "modificar") {
                            viewModel.modificarTaller(sessionManager.getToken().toString(), id)
                        } else {
                            viewModel.insertarTaller(sessionManager.getToken().toString())
                        }
                    }
                ) {
                    Text(if(tipoScreen == "modificar") "Actualizar" else "Registrar")
                }
            },
            dismissButton = {
                TextButton(onClick = { viewModel.closeConfirmDialog() }) {
                    Text("Cancelar",
                        color = Color(0xFFF44336))
                }
            }
        )
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
