package com.sebasdelalv.proyecto_griza.core.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sebasdelalv.proyecto_griza.ui.screens.cover.CoverScreen
import com.sebasdelalv.proyecto_griza.ui.screens.eliminarCuenta.EliminarCuentaScreen
import com.sebasdelalv.proyecto_griza.ui.screens.eliminarCuenta.EliminarCuentaViewModel
import com.sebasdelalv.proyecto_griza.ui.screens.info.InfoScreen
import com.sebasdelalv.proyecto_griza.ui.screens.infoPersonal.InfoPersonalScreen
import com.sebasdelalv.proyecto_griza.ui.screens.login.LoginScreen
import com.sebasdelalv.proyecto_griza.ui.screens.login.LoginViewModel
import com.sebasdelalv.proyecto_griza.ui.screens.menu.MenuScreen
import com.sebasdelalv.proyecto_griza.ui.screens.menu.MenuViewModel
import com.sebasdelalv.proyecto_griza.ui.screens.perfil.PerfilScreen
import com.sebasdelalv.proyecto_griza.ui.screens.perfil.PerfilViewModel
import com.sebasdelalv.proyecto_griza.ui.screens.reservas.ReservasScreen
import com.sebasdelalv.proyecto_griza.ui.screens.reservas.ReservasViewModel
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
    talleresViewModel: TalleresViewModel,
    reservasViewModel: ReservasViewModel,
    perfilViewModel: PerfilViewModel,
    eliminarCuentaViewModel: EliminarCuentaViewModel
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Login){
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
                navigateToLogin = {
                    navController.navigate(Login) {
                        popUpTo(0) { inclusive = true } // Borra todo el backstack
                        launchSingleTop = true
                    }
                },
                navigateToPerfil = { navController.navigate(Perfil) },
                navigateToMenu = { navController.navigate(Menu) },
                navigateToTalleres = { navController.navigate(Talleres) },
                navigateToInfo = { navController.navigate(Info) },
                navigateToReservas = { navController.navigate(Reservas)}
            )
        }

        composable<Perfil> {
            PerfilScreen(
                viewModel = perfilViewModel,
                navigateToBack = { navController.popBackStack() },
                navigateToMenu = { navController.navigate(Menu) },
                navigateToTalleres = { navController.navigate(Talleres) },
                navigateToInfo = { navController.navigate(Info) },
                navigateToReservas = { navController.navigate(Reservas) },
                navigateToInfoPersonal = { navController.navigate(InfoPersonal) },
                navigateToEliminarCuenta = { navController.navigate(EliminarCuenta)}
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
                navigateToBack = { navController.popBackStack() },
                navigateToMenu = { navController.navigate(Menu) },
                navigateToTalleres = { navController.navigate(Talleres) },
                navigateToInfo = { navController.navigate(Info) }
            )
        }

        composable<Reservas> {
            ReservasScreen(
                viewModel = reservasViewModel,
                navigateToBack = { navController.popBackStack() },
                navigateToMenu = { navController.navigate(Menu) },
                navigateToTalleres = { navController.navigate(Talleres) },
                navigateToInfo = { navController.navigate(Info) }
            )
        }

        composable<InfoPersonal> {
            InfoPersonalScreen { navController.popBackStack() }
        }

        composable<EliminarCuenta> {
            EliminarCuentaScreen(
                viewModel = eliminarCuentaViewModel,
                navigateToBack = { navController.popBackStack() },
                navigateToLogin = {
                    navController.navigate(Login) {
                        popUpTo(0) { inclusive = true } // Borra todo el backstack
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}