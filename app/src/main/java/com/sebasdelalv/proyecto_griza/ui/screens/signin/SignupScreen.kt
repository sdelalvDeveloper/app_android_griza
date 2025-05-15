package com.sebasdelalv.proyecto_griza.ui.screens.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sebasdelalv.proyecto_griza.R
import com.sebasdelalv.proyecto_griza.ui.theme.Principal
import com.sebasdelalv.proyecto_griza.ui.theme.Quicksand
import com.sebasdelalv.proyecto_griza.utils.PassswordInput
import com.sebasdelalv.proyecto_griza.utils.TextInput

@Composable
fun SignupScreen(viewModel: SignupViewModel, navigateToLogin: () -> Unit) {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val username by viewModel.username.collectAsState()
    val email by viewModel.email.collectAsState()
    val phone by viewModel.phone.collectAsState()
    val password by viewModel.password.collectAsState()
    val passwordRepeat by viewModel.passwordRepeat.collectAsState()
    val enabledButton by viewModel.isButtonEnabled.collectAsState()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.2f)
                    .background(Principal)
            ) {
                // Flecha de retroceso
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Volver atrás",
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 16.dp)
                        .size(30.dp)
                        .clickable {
                            viewModel.clearFields()
                            navigateToLogin()
                        }
                )

                // Contenido principal (logo + texto)
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
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
                    Text(
                        text = "Regístrate en Griza",
                        fontFamily = Quicksand,
                        fontWeight = FontWeight.Bold,
                        fontSize = (screenWidth * 0.06f).sp
                    )
                }
            }
            HorizontalDivider(
                color = Color.Black,
                thickness = (screenWidth * 0.004f).dp
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(1f),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextInput(username, "Nombre de usuario") { viewModel.onUsernameChanged(it)}
                TextInput(email, "Correo electrónico") { viewModel.onEmailChanged(it) }
                TextInput(phone, "Teléfono") { viewModel.onPhoneChanged(it) }
                PassswordInput(
                    value = password,
                    label = "Contraseña",
                    onValueChange = { viewModel.onPasswordChanged(it) },
                    isPassword = true
                )
                PassswordInput(
                    value = passwordRepeat,
                    label = "Repita contraseña",
                    onValueChange = { viewModel.onPasswordRepeatChanged(it) },
                    isPassword = true
                )

                Button(
                    onClick = {  },
                    modifier = Modifier.testTag("buttonRegister"),
                    enabled = enabledButton,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (enabledButton) Principal else Color.LightGray,
                        contentColor = Color.Black,

                    )
                ) {
                    Text(
                        text = "Registrarme",
                        fontFamily = Quicksand,
                        fontWeight = FontWeight.Bold,
                        fontSize = (screenWidth * 0.04f).sp
                    )
                }
            }
        }
    }
}

