package com.sebasdelalv.proyecto_griza.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sebasdelalv.proyecto_griza.ui.screens.cover.CoverScreen
import com.sebasdelalv.proyecto_griza.ui.screens.login.LoginScreen
import com.sebasdelalv.proyecto_griza.ui.screens.login.LoginViewModel
import com.sebasdelalv.proyecto_griza.ui.screens.menu.MenuScreen
import com.sebasdelalv.proyecto_griza.ui.screens.menu.MenuViewModel
import com.sebasdelalv.proyecto_griza.ui.screens.perfil.PerfilScreen
import com.sebasdelalv.proyecto_griza.ui.screens.signin.SignupScreen
import com.sebasdelalv.proyecto_griza.ui.screens.signin.SignupViewModel

@Composable
fun NavigationWrapper(loginViewModel: LoginViewModel, signupViewModel: SignupViewModel, menuViewModel: MenuViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Perfil){
        composable<Cover>{
            CoverScreen { navController.navigate(Login) }
        }

        composable<Login>{
            LoginScreen(loginViewModel) { navController.navigate(SignUp) }
        }

        composable<SignUp> {
            SignupScreen(signupViewModel) { navController.navigate(Login) }
        }

        composable<Menu> {
            MenuScreen(menuViewModel) { navController.navigate(Login) }
        }

        composable<Perfil> {
            PerfilScreen()
        }
    }
}