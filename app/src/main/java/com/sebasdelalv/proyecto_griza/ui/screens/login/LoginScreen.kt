package com.sebasdelalv.proyecto_griza.ui.screens.login


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.sebasdelalv.proyecto_griza.R
import com.sebasdelalv.proyecto_griza.ui.theme.Principal
import com.sebasdelalv.proyecto_griza.ui.theme.Quicksand
import com.sebasdelalv.proyecto_griza.utils.TextInput


@Composable
fun LoginScreen(viewModel: LoginViewModel) {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val username by viewModel.username.collectAsState()
    val password by viewModel.password.collectAsState()
    val enabledButton by viewModel.isButtonEnabled.collectAsState()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.15f)
                    .background(Principal),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.griza_negro_sin_fondo),
                    contentDescription = "Logo griza estudio",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxWidth(0.45f)
                        .fillMaxHeight(0.15f)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {

                    Text(
                        text = "Login",
                        fontFamily = Quicksand,
                        fontWeight = FontWeight.Bold,
                        fontSize = (screenWidth * 0.05f).sp
                    )
                    Text(
                        text = "Sign in",
                        fontFamily = Quicksand,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = (screenWidth * 0.05f).sp,
                        color = Color.Gray
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextInput(username, "Nombre de usuario") { viewModel.onUsernameChanged(it)}
                Spacer(modifier = Modifier.fillMaxHeight(0.1f))
                TextInput(password, "Contraseña") { viewModel.onPasswordChanged(it) }
                Spacer(modifier = Modifier.fillMaxHeight(0.1f))
                Button(
                    onClick = {  },
                    modifier = Modifier.testTag("buttonSession"),
                    enabled = enabledButton,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (enabledButton) Principal else Color.LightGray,
                        contentColor = Color.Black
                    )
                ) {
                    Text(
                        text = "Iniciar sesión",
                        fontFamily = Quicksand,
                        fontWeight = FontWeight.Bold,
                        fontSize = (screenWidth * 0.04f).sp
                    )
                }
            }
        }
    }
}

