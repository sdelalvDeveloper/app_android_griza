package com.sebasdelalv.proyecto_griza.utils

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.sebasdelalv.proyecto_griza.ui.theme.Quicksand

@Composable
fun StyledText(
    text: String,
    screenWidth: Int
) {
    Text(
        text = text,
        fontFamily = Quicksand,
        fontWeight = FontWeight.Bold,
        fontSize = (screenWidth * 0.02f).sp
    )
}
