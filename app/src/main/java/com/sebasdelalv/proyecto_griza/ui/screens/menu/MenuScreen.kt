package com.sebasdelalv.proyecto_griza.ui.screens.menu

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sebasdelalv.proyecto_griza.R
import com.sebasdelalv.proyecto_griza.data.session.SessionManager
import com.sebasdelalv.proyecto_griza.data.taller.Taller
import com.sebasdelalv.proyecto_griza.ui.theme.Principal
import com.sebasdelalv.proyecto_griza.ui.theme.Quicksand
import com.sebasdelalv.proyecto_griza.utils.MyFooter
import com.sebasdelalv.proyecto_griza.utils.SliceImages
import com.sebasdelalv.proyecto_griza.utils.SliceTalleres
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(
    viewModel: MenuViewModel,
    navigateToLogin: () -> Unit,
    navigateToPerfil: () -> Unit,
    navigateToMenu: () -> Unit,
    navigateToTalleres: () -> Unit,
    navigateToInfo: () -> Unit
) {
    val context = LocalContext.current
    val sessionManager = remember { SessionManager(context) }

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenSizes = LocalConfiguration.current.screenWidthDp
    val imagenes = listOf(
        R.drawable.image_3,
        R.drawable.image_2,
        R.drawable.image_1
    )

    val talleres = listOf(
        Taller(LocalDateTime.of(2024, 5, 10, 10, 0), "Fotografía Básica"),
        Taller(LocalDateTime.of(2024, 5, 12, 15, 0), "Diseño Gráfico"),
        Taller(LocalDateTime.of(2024, 5, 15, 9, 30), "Producción de Video"),
        Taller(LocalDateTime.of(2024, 5, 17, 14, 0), "Guion Audiovisual"),
        Taller(LocalDateTime.of(2024, 5, 20, 16, 30), "Modelado 3D"),
        Taller(LocalDateTime.of(2024, 5, 22, 11, 0), "Edición de Fotografía"),
        Taller(LocalDateTime.of(2024, 5, 25, 13, 0), "Redes Sociales"),
        Taller(LocalDateTime.of(2024, 5, 28, 18, 0), "Música Digital"),
        Taller(LocalDateTime.of(2024, 6, 2, 10, 30), "Animación 2D"),
        Taller(LocalDateTime.of(2024, 6, 5, 17, 45), "Postproducción de Audio"),
        Taller(LocalDateTime.of(2024, 6, 7, 14, 15), "Colorización de Video"),
        Taller(LocalDateTime.of(2024, 6, 10, 9, 0), "Composición Visual"),
        Taller(LocalDateTime.of(2024, 6, 13, 15, 30), "Desarrollo de Portafolio"),
        Taller(LocalDateTime.of(2024, 6, 15, 11, 45), "Dirección de Arte")
    )

    var expanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Nombre del Usuario") },
                navigationIcon = {
                    Box {
                        IconButton(
                            onClick = { expanded = true },
                            modifier = Modifier.padding(start = screenWidth * 0.05f)
                        ) {
                            Icon(
                                Icons.Default.Menu,
                                contentDescription = "Menú",
                                modifier = Modifier.size(screenWidth * 0.08f)
                            )
                        }
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("Perfil") },
                                onClick = {
                                    expanded = false
                                    navigateToPerfil()
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Notificaciones") },
                                onClick = {
                                    expanded = false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Cerrar sesión") },
                                onClick = {
                                    expanded = false
                                    sessionManager.clearSession()
                                    navigateToLogin()
                                }
                            )
                        }
                    }
                },
                actions = {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Perfil",
                        modifier = Modifier
                            .size(screenWidth * 0.15f)
                            .padding(end = screenWidth * 0.05f)
                    )
                }
            )
        },
        bottomBar = {
            MyFooter(
                navigateToMenu,
                navigateToTalleres,
                navigateToInfo
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            SliceImages(imagenes, screenWidth * 0.6f)
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height((screenWidth * 0.4f))
                    .padding((screenWidth * 0.05f)),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Principal),
                border = BorderStroke(1.dp, Color.Black),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = "Próxima cita",
                        fontFamily = Quicksand,
                        fontWeight = FontWeight.Bold,
                        fontSize = (screenSizes * 0.06f).sp
                    )
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Día",
                            fontFamily = Quicksand,
                            fontWeight = FontWeight.Bold,
                            fontSize = (screenSizes * 0.03f).sp
                        )
                        Text(
                            text = "Mes",
                            fontFamily = Quicksand,
                            fontWeight = FontWeight.Bold,
                            fontSize = (screenSizes * 0.03f).sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Hora",
                            fontFamily = Quicksand,
                            fontWeight = FontWeight.Bold,
                            fontSize = (screenSizes * 0.03f).sp
                        )
                    }
                }
            }
            SliceTalleres(talleres, screenSizes)
        }
    }
}

