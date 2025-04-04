package com.sebasdelalv.proyecto_griza.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sebasdelalv.proyecto_griza.ui.screens.CoverScreen
import com.sebasdelalv.proyecto_griza.ui.screens.LoginScreen

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Login){
        composable<Cover>{
            CoverScreen { navController.navigate(Login) }
        }

        composable<Login>{
            LoginScreen()
        }
    }
}