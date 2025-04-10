package com.sebasdelalv.proyecto_griza.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sebasdelalv.proyecto_griza.ui.screens.CoverScreen
import com.sebasdelalv.proyecto_griza.ui.screens.login.LoginScreen
import com.sebasdelalv.proyecto_griza.ui.screens.login.LoginViewModel
import com.sebasdelalv.proyecto_griza.ui.screens.signin.SignupScreen
import com.sebasdelalv.proyecto_griza.ui.screens.signin.SignupViewModel

@Composable
fun NavigationWrapper(loginViewModel: LoginViewModel, signupViewModel: SignupViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = SignUp){
        composable<Cover>{
            CoverScreen { navController.navigate(Login) }
        }

        composable<Login>{
            LoginScreen(loginViewModel)
        }

        composable<SignUp> {
            SignupScreen(signupViewModel)
        }
    }
}