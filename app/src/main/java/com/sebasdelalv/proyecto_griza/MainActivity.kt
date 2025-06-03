package com.sebasdelalv.proyecto_griza

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import com.sebasdelalv.proyecto_griza.core.navigation.NavigationWrapper
import com.sebasdelalv.proyecto_griza.ui.theme.Proyecto_grizaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Proyecto_grizaTheme {
                NavigationWrapper()
            }
        }
    }
}
