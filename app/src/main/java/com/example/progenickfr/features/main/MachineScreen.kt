package com.example.progenickfr.features.main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MachineScreen(
    machineViewModel: MachineViewModel
) {
    // 1. Estados al principio
    val formFields = listOf(
        "Area" to listOf("Barra", "Mix", "Xurritos", "Procesos", "Ingeniería", "Almacén"),
        "Turno" to listOf("Turno 1", "Turno 2", "Mixto"),
        "Falla" to listOf("Eléctrico", "Mecánico", "Ingeniería")
    )

    // Usamos el estado del ViewModel si es posible, o este mapa local para las selecciones
    val selections = remember { mutableStateMapOf<String, String>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .verticalScroll(rememberScrollState())
    ) {
        // --- ENCABEZADO ---
        Column(modifier = Modifier.padding(horizontal = 10.dp, vertical = 30.dp)) {
            Text(
                text = "Reportes",
                color = Color.White,
                fontSize = 40.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }

        // --- CUERPO DEL FORMULARIO ---
        Column(modifier = Modifier.padding(18.dp)) {

            OutlinedTextField(
                value = machineViewModel.description_fault,
                onValueChange = { machineViewModel.description_fault = it },
                label = { Text("Descripción de falla", color = Color.Gray) },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.Gray
                )
            )

            Spacer(Modifier.height(15.dp))
            Text("Material", fontSize = 35.sp, color = Color.White)

            OutlinedTextField(
                value = machineViewModel.need_material,
                onValueChange = { machineViewModel.need_material = it },
                label = { Text("Ignorar si no se ocupó", color = Color.Gray) },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )

            // 1. Asegúrate de tener este estado arriba
            var expandedIndex by remember { mutableIntStateOf(-1) }

            formFields.forEachIndexed { index, (label, options) ->
                Spacer(Modifier.height(15.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(label, color = Color.White, modifier = Modifier.weight(1f))

                    // USAMOS UN BOX CON BORDE EN LUGAR DE UN TEXTFIELD
                    Box(modifier = Modifier.weight(1.5f)) {
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp) // Altura estándar de un TextField
                                .clickable { expandedIndex = if (expandedIndex == index) -1 else index },
                            color = Color.Transparent,
                            shape = RoundedCornerShape(4.dp),
                            border = BorderStroke(1.dp, Color.Gray)
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 12.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = selections[label] ?: "Seleccionar",
                                    color = if (selections[label] == null) Color.Gray else Color.White
                                )
                                Icon(Icons.Default.ArrowDropDown, null, tint = Color.White)
                            }
                        }

                        // EL MENÚ
                        DropdownMenu(
                            expanded = expandedIndex == index,
                            onDismissRequest = { expandedIndex = -1 },
                            modifier = Modifier.background(Color(0xFF2D2D2D))
                        ) {
                            options.forEach { opcion ->
                                DropdownMenuItem(
                                    text = { Text(opcion, color = Color.White) },
                                    onClick = {
                                        selections[label] = opcion
                                        expandedIndex = -1
                                    }
                                )
                            }
                        }
                    }
                }
            }

            Spacer(Modifier.height(20.dp))

            // Observaciones Finales
            OutlinedTextField(
                value = machineViewModel.observations,
                onValueChange = { machineViewModel.observations = it },
                label = { Text("Recomendaciones/Material", color = Color.Gray) },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )

            Spacer(Modifier.height(30.dp))

            // BOTÓN
            Button(
                onClick = {
                    // Opcional: pasar los datos de 'selections' al ViewModel aquí
                    machineViewModel.onSendClick()
                },
                enabled = !machineViewModel.isSyncing,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
            ) {
                Text(if (machineViewModel.isSyncing) "Enviando..." else "Enviar Reporte")
            }
        }
    }
}