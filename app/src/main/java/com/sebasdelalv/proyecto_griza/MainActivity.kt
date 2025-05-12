package com.sebasdelalv.proyecto_griza

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sebasdelalv.proyecto_griza.core.navigation.NavigationWrapper
import com.sebasdelalv.proyecto_griza.ui.screens.login.LoginViewModel
import com.sebasdelalv.proyecto_griza.ui.screens.menu.MenuViewModel
import com.sebasdelalv.proyecto_griza.ui.screens.signin.SignupViewModel
import com.sebasdelalv.proyecto_griza.ui.screens.talleres.TalleresViewModel
import com.sebasdelalv.proyecto_griza.ui.theme.Proyecto_grizaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Proyecto_grizaTheme {
                val loginViewModel: LoginViewModel = viewModel()
                val signupViewModel: SignupViewModel = viewModel()
                val menuViewModel: MenuViewModel = viewModel()
                val talleresViewModel: TalleresViewModel = viewModel()

                NavigationWrapper(
                    loginViewModel,
                    signupViewModel,
                    menuViewModel,
                    talleresViewModel
                )
            }
        }
    }
}
