package com.sebasdelalv.proyecto_griza.ui.screens.saldo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sebasdelalv.proyecto_griza.data.saldo.Saldo
import com.sebasdelalv.proyecto_griza.ui.theme.Principal
import com.sebasdelalv.proyecto_griza.ui.theme.Quicksand
import com.sebasdelalv.proyecto_griza.utils.MyColumnSaldo
import com.sebasdelalv.proyecto_griza.utils.MyFooter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SaldoScreen(
    viewModel: SaldoViewModel,
    navigateToBack: () -> Unit,
    navigateToMenu: () -> Unit,
    navigateToTalleres: () -> Unit,
    navigateToInfo: () -> Unit
){
    val screenWidth = LocalConfiguration.current.screenWidthDp

    /*
    val saldo by viewModel.saldo.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()
    */

    val saldo = Saldo(1, 4, 5)


    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "SALDO",
                        fontFamily = Quicksand,
                        fontWeight = FontWeight.Bold,
                        fontSize = (screenWidth * 0.06f).sp
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
            MyFooter(navigateToMenu, navigateToTalleres, navigateToInfo)
        }
    ) { innerPadding ->
        HorizontalDivider(
            color = Color.Black,
            thickness = (screenWidth * 0.004f).dp,
            modifier = Modifier.padding(innerPadding)
        )
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .padding((screenWidth * 0.1f).dp)
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(vertical = (screenWidth * 0.05f).dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    MyColumnSaldo("USADOS", saldo.usados.toString(), screenWidth)
                    MyColumnSaldo("DISPONIBLE", saldo.disponible.toString(), screenWidth)
                    MyColumnSaldo("TOTAL", saldo.total.toString(), screenWidth)
                }
            }
        }
    }
}