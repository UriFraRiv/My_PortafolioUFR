package com.example.progenickfr.features.main

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.SnapPosition
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.format.TextStyle

@Composable
fun MaterialWarehouse(
    materialViewModel: materialneed
) {
    // Usamos un fondo gris muy oscuro en lugar de negro puro para que se vea más moderno
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "FRPROGENICK",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4CAF50) // Un verde industrial
        )

        Text(
            text = "Control de Almacén",
            fontSize = 16.sp,
            color = Color.Gray
        )

        Spacer(Modifier.height(24.dp))

        // TARJETA DE BÚSQUEDA
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E)),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Búsqueda de Material", color = Color.White, fontWeight = FontWeight.Medium)
                Spacer(Modifier.height(8.dp))

                OutlinedTextField(
                    value = materialViewModel.searchMAterial,
                    onValueChange = { materialViewModel.searchMAterial = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Nombre o ID") },
                    textStyle = androidx.compose.ui.text.TextStyle(color = Color.White),
                    singleLine = true
                )

                Spacer(Modifier.height(16.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Estado en inventario:", color = Color.LightGray, fontSize = 14.sp)
                    Spacer(Modifier.width(12.dp))
                    // Un indicador circular más elegante
                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .background(Color.Green, shape = CircleShape)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text("Disponible", color = Color.Green, fontSize = 14.sp)
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        // TARJETA DE REPORTE
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E)),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Detalles del Movimiento", color = Color.White, fontWeight = FontWeight.Medium)
                Spacer(Modifier.height(12.dp))

                OutlinedTextField(
                    value = materialViewModel.materialTake,
                    onValueChange = { materialViewModel.materialTake = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Cantidad a tomar") },
                    textStyle = androidx.compose.ui.text.TextStyle(color = Color.White)
                )

                Spacer(Modifier.height(12.dp))

                OutlinedTextField(
                    value = materialViewModel.placeWork,
                    onValueChange = { materialViewModel.placeWork = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Área / Proyecto / Máquina") },
                    textStyle = androidx.compose.ui.text.TextStyle(color = Color.White)
                )
            }
        }

        Spacer(Modifier.weight(1f)) // Empuja el botón hacia abajo

        // BOTÓN MODERNO
        Button(
            onClick = { materialViewModel.onSendClick() },
            enabled = !materialViewModel.isSyncing,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2196F3),
                disabledContainerColor = Color.Gray
            )
        ) {
            if (materialViewModel.isSyncing) {
                CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
            } else {
                Text("ENVIAR REPORTE", fontWeight = FontWeight.Bold, letterSpacing = 2.sp)
            }
        }
    }
}