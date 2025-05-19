package com.sebasdelalv.proyecto_griza.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sebasdelalv.proyecto_griza.ui.theme.Quicksand

@Composable
fun MyColumnSaldo(
    titulo: String,
    cantidad: String,
    screenWidth: Int,
    color: Color
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = titulo,
            fontFamily = Quicksand,
            fontWeight = FontWeight.Bold,
            fontSize = (screenWidth * 0.04f).sp
        )
        Spacer(modifier = Modifier.height((screenWidth * 0.05f).dp))
        Text(
            text = cantidad,
            fontFamily = Quicksand,
            fontWeight = FontWeight.Bold,
            fontSize = (screenWidth * 0.05f).sp,
            color = color
        )
    }
}