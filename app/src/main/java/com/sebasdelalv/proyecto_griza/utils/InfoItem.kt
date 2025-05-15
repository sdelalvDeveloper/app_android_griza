package com.sebasdelalv.proyecto_griza.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sebasdelalv.proyecto_griza.ui.theme.Quicksand

@Composable
fun InfoItem(title: String, value: String, screenWidth: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = (screenWidth * 0.08f).dp, horizontal = (screenWidth * 0.2f).dp),
        verticalArrangement = Arrangement.spacedBy((screenWidth * 0.03f).dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = title,
            fontFamily = Quicksand,
            fontWeight = FontWeight.Bold,
            fontSize = (screenWidth * 0.05f).sp
        )
        Text(
            text = value,
            fontFamily = Quicksand,
            fontWeight = FontWeight.SemiBold,
            fontSize = (screenWidth * 0.05f).sp
        )
    }
}
