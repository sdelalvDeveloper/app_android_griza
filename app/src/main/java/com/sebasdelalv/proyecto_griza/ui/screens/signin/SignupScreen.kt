package com.sebasdelalv.proyecto_griza.ui.screens.signin

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
fun SignupScreen(viewModel: SignupViewModel) {
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
                        fontWeight = FontWeight.SemiBold,
                        fontSize = (screenWidth * 0.05f).sp,
                        color = Color.Gray
                    )
                    Text(
                        text = "Sign in",
                        fontFamily = Quicksand,
                        fontWeight = FontWeight.Bold,
                        fontSize = (screenWidth * 0.05f).sp
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(1f),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextInput(username, "Username") { viewModel.onUsernameChanged(it)}
                TextInput(email, "Email") { viewModel.onEmailChanged(it) }
                TextInput(phone, "Phone") { viewModel.onPhoneChanged(it) }
                TextInput(password, "Password") { viewModel.onPasswordChanged(it) }
                TextInput(passwordRepeat, "Password repeat") { viewModel.onPasswordRepeatChanged(it) }

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

