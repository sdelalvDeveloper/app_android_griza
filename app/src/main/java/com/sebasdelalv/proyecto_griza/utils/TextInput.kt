package com.sebasdelalv.proyecto_griza.utils

import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.sebasdelalv.proyecto_griza.ui.theme.Principal

@Composable
fun TextInput(value: String, label: String, onValueChange:(String)-> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(
            text= label,
            color = Color.Black
        ) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color.Gray,
            cursorColor = Color.Gray,
            focusedLabelColor = Color.Black, // <- Cambia el color de la etiqueta enfocada
            selectionColors = TextSelectionColors( // <- Cambia color de selección
                handleColor = Principal,     // la "gota"
                backgroundColor = Color.LightGray // fondo del texto seleccionado
            )
        ),
        singleLine = true
    )
}