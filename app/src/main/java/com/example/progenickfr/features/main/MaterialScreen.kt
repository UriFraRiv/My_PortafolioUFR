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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MaterialWarehouse(
    materialViewModel: materialneed
)
{
    var materiale by remember { mutableStateOf("") }

    Column (modifier=Modifier
        .fillMaxSize()
        .background(Color.Black)
        .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally){

        Text("Material/Almacen",
            fontSize = 33.sp,
            color = Color.White)
        Spacer(Modifier.padding(vertical = 10.dp))
        Column {
            Text("Busqueda de material",
                fontSize = 20.sp,
                color = Color.White)
            Spacer(Modifier.padding(vertical = 10.dp))
            OutlinedTextField(value =materialViewModel.searchMAterial, onValueChange = {materialViewModel.searchMAterial=it})
            Spacer(Modifier.padding(vertical = 30.dp))

            Row {
                Text(
                    "Existe",
                    fontSize = 20.sp,
                    color = Color.White
                )
                Spacer(modifier=Modifier.padding(horizontal = 40.dp))
                Box(
                    modifier = Modifier
                        .background(Color.Green)
                        .size(20.dp)
                )
            }
            Spacer(Modifier.padding(vertical = 30.dp))


            Text("Lugar",
                fontSize = 20.sp,
                color = Color.White)

        }

        Spacer(Modifier.padding(vertical = 20.dp))
        Text("Material tomado",fontSize = 33.sp,
            color = Color.White)

        OutlinedTextField(value = materialViewModel.materialTake, onValueChange = {materialViewModel.materialTake=it})

        Spacer(Modifier.padding(vertical = 30.dp))
         Text("Area/Proyecto/Maquina",
             fontSize = 20.sp,
             color = Color.White)

        OutlinedTextField(value = materialViewModel.placeWork, onValueChange = {materialViewModel.placeWork=it})

        Spacer(Modifier.padding(vertical = 30.dp))

        // BOTÓN
        Button(
            onClick = {
                // Opcional: pasar los datos de 'selections' al ViewModel aquí
                materialViewModel.onSendClick()
            },
            enabled = !materialViewModel.isSyncing,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
        ) {
            Text(if (materialViewModel.isSyncing) "Enviando..." else "Enviar Reporte")
        }






    }

}
