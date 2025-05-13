package com.sebasdelalv.proyecto_griza.core.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sebasdelalv.proyecto_griza.ui.screens.cover.CoverScreen
import com.sebasdelalv.proyecto_griza.ui.screens.cuenta.CuentaScreen
import com.sebasdelalv.proyecto_griza.ui.screens.info.InfoScreen
import com.sebasdelalv.proyecto_griza.ui.screens.login.LoginScreen
import com.sebasdelalv.proyecto_griza.ui.screens.login.LoginViewModel
import com.sebasdelalv.proyecto_griza.ui.screens.menu.MenuScreen
import com.sebasdelalv.proyecto_griza.ui.screens.menu.MenuViewModel
import com.sebasdelalv.proyecto_griza.ui.screens.perfil.PerfilScreen
import com.sebasdelalv.proyecto_griza.ui.screens.signin.SignupScreen
import com.sebasdelalv.proyecto_griza.ui.screens.signin.SignupViewModel
import com.sebasdelalv.proyecto_griza.ui.screens.talleres.TalleresScreen
import com.sebasdelalv.proyecto_griza.ui.screens.talleres.TalleresViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationWrapper(
    loginViewModel: LoginViewModel,
    signupViewModel: SignupViewModel,
    menuViewModel: MenuViewModel,
    talleresViewModel: TalleresViewModel
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Cover){
        composable<Cover>{
            CoverScreen { navController.navigate(Login) }
        }

        composable<Login>{
            LoginScreen(
                viewModel = loginViewModel,
                navigateToSignup = { navController.navigate(SignUp) },
                navigateToMenu = { navController.navigate(Menu) }
            )
        }

        composable<SignUp> {
            SignupScreen(signupViewModel) { navController.navigate(Login) }
        }

        composable<Menu> {
            MenuScreen(
                viewModel = menuViewModel,
                navigateToLogin = { navController.navigate(Login) },
                navigateToPerfil = { navController.navigate(Perfil) },
                navigateToMenu = { navController.navigate(Menu) },
                navigateToTalleres = { navController.navigate(Talleres) },
                navigateToInfo = { navController.navigate(Info) }
            )
        }

        composable<Perfil> {
            PerfilScreen(
                navigateToCuenta = { navController.navigate(Cuenta) },
                navigateToMenu = { navController.navigate(Menu) },
                navigateToTalleres = { navController.navigate(Talleres) },
                navigateToInfo = { navController.navigate(Info) }
            )
        }

        composable<Cuenta> {
            CuentaScreen(
                navigateToBack = { navController.popBackStack() },
                navigateToMenu = { navController.navigate(Menu) },
                navigateToTalleres = { navController.navigate(Talleres)},
                navigateToInfo = { navController.navigate(Info) }
            )
        }

        composable<Talleres> {
            TalleresScreen(
                viewModel = talleresViewModel,
                navigateToBack = { navController.popBackStack() },
                navigateToMenu = { navController.navigate(Menu) },
                navigateToTalleres = { navController.navigate(Talleres) },
                navigateToInfo = { navController.navigate(Info) }
            )
        }

        composable<Info> {
            InfoScreen(
                viewModel = talleresViewModel,
                navigateToBack = { navController.popBackStack() },
                navigateToMenu = { navController.navigate(Menu) },
                navigateToTalleres = { navController.navigate(Talleres) },
                navigateToInfo = { navController.navigate(Info) }
            )
        }
    }
}