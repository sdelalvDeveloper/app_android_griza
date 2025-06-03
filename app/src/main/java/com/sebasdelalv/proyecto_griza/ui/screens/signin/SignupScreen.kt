package com.sebasdelalv.proyecto_griza.ui.screens.signin

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.sebasdelalv.proyecto_griza.R
import com.sebasdelalv.proyecto_griza.ui.theme.Principal
import com.sebasdelalv.proyecto_griza.ui.theme.Quicksand
import com.sebasdelalv.proyecto_griza.utils.PassswordInput
import com.sebasdelalv.proyecto_griza.utils.TextInput

@Composable
fun SignupScreen(navigateToLogin: () -> Unit) {
    val viewModel: SignupViewModel = hiltViewModel()
    val context = LocalContext.current
    val screenWidth = LocalConfiguration.current.screenWidthDp

    val username by viewModel.username.collectAsState()
    val email by viewModel.email.collectAsState()
    val phone by viewModel.phone.collectAsState()
    val password by viewModel.password.collectAsState()
    val passwordRepeat by viewModel.passwordRepeat.collectAsState()

    val dialogTitle by viewModel.dialogTitle.collectAsState()
    val dialogMessage by viewModel.dialogMessage.collectAsState()
    val isDialogOpen by viewModel.isDialogOpen.collectAsState()
    val enabledButton by viewModel.isButtonEnabled.collectAsState()

    val toastMessage by viewModel.toastMessage.collectAsState()
    val shouldNavigate by viewModel.shouldNavigate.collectAsState()

    // Mostrar Toast
    LaunchedEffect(toastMessage) {
        toastMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            viewModel.toastShown()
        }
    }

    // Navegar después del Toast
    LaunchedEffect(shouldNavigate) {
        if (shouldNavigate) {
            navigateToLogin()
            viewModel.onNavigated()
        }
    }

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
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Volver atrás",
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 16.dp)
                        .size(30.dp)
                        .clickable {
                            navigateToLogin()
                            viewModel.clearFields()
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
                Column(
                ) {
                    PassswordInput(
                        value = password,
                        label = "Contraseña",
                        onValueChange = { viewModel.onPasswordChanged(it) },
                        isPassword = true
                    )
                    Text(
                        text = "6 caracteres",
                        fontSize = 12.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }

                PassswordInput(
                    value = passwordRepeat,
                    label = "Repita contraseña",
                    onValueChange = { viewModel.onPasswordRepeatChanged(it) },
                    isPassword = true
                )

                Button(
                    onClick = { viewModel.register() },
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
    if (isDialogOpen) {
        AlertDialog(
            onDismissRequest = { viewModel.closeDialog() },
            title = { Text(dialogTitle.toString()) },
            text = { Text(dialogMessage ?: "") },
            confirmButton = {
                Button(onClick = { viewModel.closeDialog() }) {
                    Text("Aceptar")
                }
            }
        )
    }
}

