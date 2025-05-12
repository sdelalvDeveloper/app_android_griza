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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sebasdelalv.proyecto_griza.R
import com.sebasdelalv.proyecto_griza.data.session.SessionManager
import com.sebasdelalv.proyecto_griza.ui.theme.Principal
import com.sebasdelalv.proyecto_griza.ui.theme.Quicksand
import com.sebasdelalv.proyecto_griza.utils.TextInput


@Composable
fun LoginScreen(viewModel: LoginViewModel, navigateToSignup:()-> Unit) {
    val context = LocalContext.current
    val sessionManager = remember { SessionManager(context) }
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
                    .fillMaxHeight(0.2f)
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
                Text(
                    text = "Accede a tu cuenta griza",
                    fontFamily = Quicksand,
                    fontWeight = FontWeight.Bold,
                    fontSize = (screenWidth * 0.06f).sp
                )
            }
            HorizontalDivider(
                color = Color.Black,
                thickness = (screenWidth * 0.004f).dp
            )
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
                Spacer(modifier = Modifier.fillMaxHeight(0.1f))
                Button(
                    onClick = {
                        viewModel.clearFields()
                        navigateToSignup()
                        },
                    modifier = Modifier.testTag("buttonRegister"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Principal,
                        contentColor = Color.Black
                    )
                ) {
                    Text(
                        text = "Registrarme",
                        fontFamily = Quicksand,
                        fontWeight = FontWeight.Bold,
                        fontSize = (screenWidth * 0.15f).sp
                    )
                }
            }
        }
    }
}

