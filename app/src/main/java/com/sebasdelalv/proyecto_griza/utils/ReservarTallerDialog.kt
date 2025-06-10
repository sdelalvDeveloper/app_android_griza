package com.sebasdelalv.proyecto_griza.utils

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.sebasdelalv.proyecto_griza.data.mapper.capitalizeFirst
import com.sebasdelalv.proyecto_griza.domain.model.TallerResult
import com.sebasdelalv.proyecto_griza.ui.theme.RojoAlert
import com.sebasdelalv.proyecto_griza.ui.theme.VerdeDialog
import com.sebasdelalv.proyecto_griza.ui.theme.VerdeDisponible

@Composable
fun ReservarTallerDialog(
    taller: TallerResult,
    onReservar: (TallerResult) -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("¿Reservar taller?") },
        text = { Text("¿Deseas reservar el taller \"${taller.titulo.capitalizeFirst()}\"?") },
        confirmButton = {
            TextButton(onClick = {
                onReservar(taller)
                onDismiss()
            }) {
                Text("Aceptar", color = VerdeDialog)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar", color = RojoAlert)
            }
        }
    )
}
