package com.sebasdelalv.proyecto_griza

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.sebasdelalv.proyecto_griza.core.navigation.NavigationWrapper
import com.sebasdelalv.proyecto_griza.ui.theme.Proyecto_grizaTheme

class MainActivity : ComponentActivity() {
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
