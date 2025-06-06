package com.sebasdelalv.proyecto_griza.utils


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.sebasdelalv.proyecto_griza.ui.theme.Quicksand

@Composable
fun TextStyleTaller(
    text: String,
    screenWidth: Int,
    color: Color
) {
    Text(
        text = text,
        fontFamily = Quicksand,
        fontWeight = FontWeight.Bold,
        fontSize = (screenWidth * 0.04f).sp,
        color = color
    )
}
