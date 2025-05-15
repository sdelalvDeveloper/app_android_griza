package com.sebasdelalv.proyecto_griza.core.navigation



import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sebasdelalv.proyecto_griza.ui.screens.cover.CoverScreen
import com.sebasdelalv.proyecto_griza.ui.screens.info.InfoScreen
import com.sebasdelalv.proyecto_griza.ui.screens.infoPersonal.InfoPersonalScreen
import com.sebasdelalv.proyecto_griza.ui.screens.login.LoginScreen
import com.sebasdelalv.proyecto_griza.ui.screens.login.LoginViewModel
import com.sebasdelalv.proyecto_griza.ui.screens.menu.MenuScreen
import com.sebasdelalv.proyecto_griza.ui.screens.menu.MenuViewModel
import com.sebasdelalv.proyecto_griza.ui.screens.perfil.PerfilScreen
import com.sebasdelalv.proyecto_griza.ui.screens.reservas.ReservasScreen
import com.sebasdelalv.proyecto_griza.ui.screens.reservas.ReservasViewModel
import com.sebasdelalv.proyecto_griza.ui.screens.saldo.SaldoScreen
import com.sebasdelalv.proyecto_griza.ui.screens.saldo.SaldoViewModel
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
    saldoViewModel: SaldoViewModel
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Menu){
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
                navigateToBack = { navController.popBackStack() },
                navigateToMenu = { navController.navigate(Menu) },
                navigateToTalleres = { navController.navigate(Talleres) },
                navigateToInfo = { navController.navigate(Info) },
                navigateToReservas = { navController.navigate(Reservas) },
                navigateToSaldo = { navController.navigate(Saldo) },
                navigateToInfoPersonal = { navController.navigate(InfoPersonal)}
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

        composable<Saldo> {
            SaldoScreen(
                viewModel = saldoViewModel,
                navigateToBack = { navController.popBackStack() },
                navigateToMenu = { navController.navigate(Menu) },
                navigateToTalleres = { navController.navigate(Talleres) },
                navigateToInfo = { navController.navigate(Info) }
            )
        }

        composable<InfoPersonal> {
            InfoPersonalScreen { navController.popBackStack() }
        }
    }
}