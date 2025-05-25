package com.sebasdelalv.proyecto_griza.utils

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.sebasdelalv.proyecto_griza.data.mapper.capitalizeFirst
import com.sebasdelalv.proyecto_griza.domain.model.TallerResult

@Composable
fun TallerOpcionDialogAdmin(
    taller: TallerResult,
    onDismiss: () -> Unit,
    onModificar: (TallerResult) -> Unit,
    onEliminar: (TallerResult) -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("¿Qué deseas hacer?") },
        text = { Text("Selecciona una acción para el taller \"${taller.titulo.capitalizeFirst()}\".") },
        confirmButton = {
            TextButton(onClick = {
                onModificar(taller)
                onDismiss()
            }) {
                Text("Modificar")
            }
        },
        dismissButton = {
            TextButton(onClick = {
                onEliminar(taller)
                onDismiss()
            }) {
                Text("Eliminar", color = Color.Red)
            }
        }
    )
}
