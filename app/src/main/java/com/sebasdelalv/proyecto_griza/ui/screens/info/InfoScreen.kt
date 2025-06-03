package com.sebasdelalv.proyecto_griza.ui.screens.info

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sebasdelalv.proyecto_griza.R
import com.sebasdelalv.proyecto_griza.data.local.SessionManager
import com.sebasdelalv.proyecto_griza.ui.theme.Principal
import com.sebasdelalv.proyecto_griza.utils.MyFooter
import com.sebasdelalv.proyecto_griza.utils.MyFooterAdmin
import com.sebasdelalv.proyecto_griza.utils.MyGoogleMaps

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoScreen(
    navigateToBack: () -> Unit,
    navigateToMenu: () -> Unit,
    navigateToTalleres: () -> Unit,
    navigateToInfo: () -> Unit,
    navigateToMenuAdmin: () -> Unit
){
    val context = LocalContext.current
    val sessionManager = remember { SessionManager(context) }
    val screenWidth = LocalConfiguration.current.screenWidthDp

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Image(
                        painter = painterResource(id = R.drawable.griza_negro_sin_fondo),
                        contentDescription = "Logo griza estudio",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size((screenWidth * 0.4f).dp)
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navigateToBack()
                        },
                        modifier = Modifier.padding(start = (screenWidth * 0.05f).dp)
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = "volver atras",
                            modifier = Modifier.size((screenWidth * 0.08f).dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Principal
                )
            )
        },
        bottomBar = {
            if (sessionManager.getRole()?.lowercase() == "admin") {
                MyFooterAdmin(navigateToMenuAdmin, navigateToTalleres, navigateToInfo)
            } else {
                MyFooter(navigateToMenu, navigateToTalleres, navigateToInfo)
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ){
            HorizontalDivider(
                color = Color.Black,
                thickness = (screenWidth * 0.004f).dp
            )

            MyGoogleMaps(modifier = Modifier
                .fillMaxWidth()
                .height((screenWidth * 0.6f).dp)
            )

            Column(
                modifier = Modifier.fillMaxWidth().weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "C/ Fernández Fermina, 16, Oficina 9"
                )
                Text(
                    text = "(Promálaga - Cruz de Humilladero)"
                )
                Text(
                    text = "29006. Málaga"
                )
            }

            HorizontalDivider(
                color = Color.LightGray,
                thickness = (screenWidth * 0.004f).dp
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = (screenWidth * 0.12f).dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Sobre Griza",
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height((screenWidth * 0.02f).dp))
                Text(
                    text = "Hola, soy Ani Delgado, diseñadora e ilustradora creativa.",
                    fontWeight = FontWeight.Bold,
                    fontSize = (screenWidth * 0.032f).sp,
                    lineHeight = (screenWidth * 0.035f).sp
                )
                Text(
                    text = "Desde que tengo memoria, la creatividad ha sido mi fiel compañera. De pequeña, mi madre me llamaba...",
                    fontWeight = FontWeight.Normal,
                    fontSize = (screenWidth * 0.032f).sp,
                    lineHeight = (screenWidth * 0.035f).sp
                )
            }

            HorizontalDivider(
                color = Color.LightGray,
                thickness = (screenWidth * 0.004f).dp
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = (screenWidth * 0.12f).dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Redes y contactos",
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height((screenWidth * 0.02f).dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically // Centra el texto con el icono
                ) {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "email",
                        tint = Color.Black,
                        modifier = Modifier.size((screenWidth * 0.05f).dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "info@grizaestudio.es",
                        fontSize = 16.sp
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Call,
                        contentDescription = "telefono",
                        tint = Color.Black,
                        modifier = Modifier.size((screenWidth * 0.05f).dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "614357580",
                        fontSize = 16.sp
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Language,
                        contentDescription = "redes",
                        tint = Color.Black,
                        modifier = Modifier.size((screenWidth * 0.05f).dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "https://grizaestudio.es/",
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}