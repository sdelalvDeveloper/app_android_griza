package com.sebasdelalv.proyecto_griza.core.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.sebasdelalv.proyecto_griza.ui.screens.cambiarPassword.CambiarPasswordScreen
import com.sebasdelalv.proyecto_griza.ui.screens.cover.CoverScreen
import com.sebasdelalv.proyecto_griza.ui.screens.eliminarCuenta.EliminarCuentaScreen
import com.sebasdelalv.proyecto_griza.ui.screens.info.InfoScreen
import com.sebasdelalv.proyecto_griza.ui.screens.infoPersonal.InfoPersonalScreen
import com.sebasdelalv.proyecto_griza.ui.screens.login.LoginScreen
import com.sebasdelalv.proyecto_griza.ui.screens.menu.MenuScreen
import com.sebasdelalv.proyecto_griza.ui.screens.menuAdmin.MenuAdminScreen
import com.sebasdelalv.proyecto_griza.ui.screens.perfil.PerfilScreen
import com.sebasdelalv.proyecto_griza.ui.screens.reservas.ReservasScreen
import com.sebasdelalv.proyecto_griza.ui.screens.signin.SignupScreen
import com.sebasdelalv.proyecto_griza.ui.screens.talleres.ModificarTalleresScreen
import com.sebasdelalv.proyecto_griza.ui.screens.talleres.TalleresScreen
import com.sebasdelalv.proyecto_griza.ui.screens.usuarios.UsuariosScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Login){
        composable<Cover>{
            CoverScreen { navController.navigate(Login) }
        }

        composable<Login>{
            LoginScreen(
                navigateToSignup = { navController.navigate(SignUp) },
                navigateToMenu = { navController.navigate(Menu) },
                navigateToMenuAdmin = { navController.navigate(MenuAdmin)}
            )
        }

        composable<SignUp> {
            SignupScreen { navController.navigate(Login) }
        }

        composable<Menu> {
            MenuScreen(
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
                navigateToBack = { navController.popBackStack() },
                navigateToMenu = { navController.navigate(Menu) },
                navigateToTalleres = { navController.navigate(Talleres) },
                navigateToInfo = { navController.navigate(Info) },
                navigateToReservas = { navController.navigate(Reservas) },
                navigateToInfoPersonal = { navController.navigate(InfoPersonal) },
                navigateToEliminarCuenta = { navController.navigate(EliminarCuenta) },
                navigateToCambiarPassword = { navController.navigate(CambiarPassword)}
            )
        }

        composable<Talleres> {
            TalleresScreen(
                navigateToBack = { navController.popBackStack() },
                navigateToMenu = { navController.navigate(Menu) },
                navigateToTalleres = { navController.navigate(Talleres) },
                navigateToInfo = { navController.navigate(Info) },
                navigateToModificarTaller = { id, tipoScreen -> navController.navigate(ModificarTaller(id = id, tipoScreen = tipoScreen)) },
                navigateToMenuAdmin = { navController.navigate(MenuAdmin) }
            )
        }

        composable<Info> {
            InfoScreen(
                navigateToBack = { navController.popBackStack() },
                navigateToMenu = { navController.navigate(Menu) },
                navigateToTalleres = { navController.navigate(Talleres) },
                navigateToInfo = { navController.navigate(Info) },
                navigateToMenuAdmin = { navController.navigate(MenuAdmin) }
            )
        }

        composable<Reservas> {
            ReservasScreen(
                navigateToBack = { navController.popBackStack() },
                navigateToMenu = { navController.navigate(Menu) },
                navigateToTalleres = { navController.navigate(Talleres) },
                navigateToInfo = { navController.navigate(Info) },
                navigateToMenuAdmin = { navController.navigate(MenuAdmin) }
            )
        }

        composable<InfoPersonal> {
            InfoPersonalScreen { navController.popBackStack() }
        }

        composable<EliminarCuenta> {
            EliminarCuentaScreen(
                navigateToBack = { navController.popBackStack() },
                navigateToLogin = {
                    navController.navigate(Login) {
                        popUpTo(0) { inclusive = true } // Borra todo el backstack
                        launchSingleTop = true
                    }
                }
            )
        }

        composable<CambiarPassword> {
            CambiarPasswordScreen(
                navigateToBack = { navController.popBackStack() },
                navigateToPerfil = { navController.navigate(Perfil) }
            )
        }

        composable<MenuAdmin> {
            MenuAdminScreen(
                navigateToLogin = {
                    navController.navigate(Login) {
                        popUpTo(0) { inclusive = true } // Borra todo el backstack
                        launchSingleTop = true
                    }
                },
                navigateToMenuAdmin = { navController.navigate(MenuAdmin) },
                navigateToTalleres = { navController.navigate(Talleres) },
                navigateToReservas = { navController.navigate(Reservas)},
                navigateToInfo = { navController.navigate(Info) },
                navigateToUsuarios = { navController.navigate(Usuarios) }
            )
        }

        composable<Usuarios> {
            UsuariosScreen(
                navigateToBack = { navController.popBackStack() },
                navigateToMenu = { navController.navigate(Menu) },
                navigateToTalleres = { navController.navigate(Talleres) },
                navigateToInfo = { navController.navigate(Info) }
            )
        }

        composable<ModificarTaller> { backStackEntry ->
            val taller: ModificarTaller = backStackEntry.toRoute()
            ModificarTalleresScreen(
                id = taller.id,
                tipoScreen = taller.tipoScreen,
                navigateToBack = { navController.popBackStack() },
                navigateToTalleres = { navController.navigate(Talleres) },
            )
        }
    }
}