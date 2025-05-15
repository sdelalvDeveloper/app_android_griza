package com.sebasdelalv.proyecto_griza.ui.screens.infoPersonal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sebasdelalv.proyecto_griza.ui.theme.Principal
import com.sebasdelalv.proyecto_griza.ui.theme.Quicksand
import com.sebasdelalv.proyecto_griza.utils.InfoItem

@Composable
fun InfoPersonalScreen(
    viewModel: InfoPersonalViewModel = viewModel(),
    navigateToBack: () -> Unit
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val userInfo by viewModel.userInfo.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if(!isLoading) {
            userInfo?.let { user ->
                InfoItem("Usuario", user.name, screenWidth)
                InfoItem("Correo electrónico", user.email, screenWidth)
                InfoItem("Teléfono", user.phone, screenWidth)

            }
        }
        Spacer(modifier = Modifier.height((screenWidth * 0.1f).dp))
        Button(
            onClick = navigateToBack,
            modifier = Modifier.testTag("buttonRegister"),
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
}