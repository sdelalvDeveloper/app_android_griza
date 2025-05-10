package com.sebasdelalv.proyecto_griza.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sebasdelalv.proyecto_griza.ui.theme.Quicksand

@Composable
fun CuentaActionRow(
    label: String,
    description: String,
    screenWidth: Int,
    /*onClick: () -> Unit*/
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = (screenWidth * 0.04f).dp,
                horizontal = (screenWidth * 0.1f).dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontFamily = Quicksand,
            fontWeight = FontWeight.Bold,
            fontSize = (screenWidth * 0.05f).sp
        )
        IconButton(
            onClick = {/*navegacion*/},
            modifier = Modifier.padding(start = (screenWidth * 0.05f).dp)
        ) {
            Icon(
                Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = description,
                modifier = Modifier.size((screenWidth * 0.08f).dp)
            )
        }
    }
}