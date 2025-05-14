package com.sebasdelalv.proyecto_griza

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sebasdelalv.proyecto_griza.core.navigation.NavigationWrapper
import com.sebasdelalv.proyecto_griza.ui.screens.login.LoginViewModel
import com.sebasdelalv.proyecto_griza.ui.screens.menu.MenuViewModel
import com.sebasdelalv.proyecto_griza.ui.screens.reservas.ReservasViewModel
import com.sebasdelalv.proyecto_griza.ui.screens.saldo.SaldoViewModel
import com.sebasdelalv.proyecto_griza.ui.screens.signin.SignupViewModel
import com.sebasdelalv.proyecto_griza.ui.screens.talleres.TalleresViewModel
import com.sebasdelalv.proyecto_griza.ui.theme.Proyecto_grizaTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Proyecto_grizaTheme {
                val loginViewModel: LoginViewModel = viewModel()
                val signupViewModel: SignupViewModel = viewModel()
                val menuViewModel: MenuViewModel = viewModel()
                val talleresViewModel: TalleresViewModel = viewModel()
                val reservasViewModel: ReservasViewModel = viewModel()
                val saldoViewModel: SaldoViewModel = viewModel()

                NavigationWrapper(
                    loginViewModel,
                    signupViewModel,
                    menuViewModel,
                    talleresViewModel,
                    reservasViewModel,
                    saldoViewModel
                )
            }
        }
    }
}
