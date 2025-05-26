package com.sebasdelalv.proyecto_griza.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sebasdelalv.proyecto_griza.domain.model.RegisterResult

@Composable
fun ColumnUsuarios(
    usuarios: List<RegisterResult>,
    screenWidth: Int,
    innerPadding: PaddingValues,
    onUsuarioClick: (RegisterResult) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(top = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(usuarios) { usuario ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp)
                    .clickable { onUsuarioClick(usuario) },
                shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(containerColor = Color.LightGray)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 4.dp, vertical = 16.dp),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        TextStyleTaller(
                            text = " Usuario: ${usuario.username}",
                            screenWidth = screenWidth,
                            Color.Black
                        )

                        TextStyleTaller(
                            text = "Teléfono: ${usuario.telefono}",
                            screenWidth = screenWidth,
                            Color.Black
                        )

                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        TextStyleTaller(
                            text = " Email: ${usuario.email}",
                            screenWidth = screenWidth,
                            Color.Black
                        )

                        TextStyleTaller(
                            text = "Bono: ${usuario.bono}",
                            screenWidth = screenWidth,
                            Color.Black
                        )
                    }
                }
            }
        }
    }
}