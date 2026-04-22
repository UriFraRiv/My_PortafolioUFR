package com.example.progenickfr.features.main.machineScreenn

import android.graphics.Color
import androidx.compose.foundation.background

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun HeaderRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Text("Área", modifier = Modifier.weight(1.2f), fontWeight = FontWeight.Bold)
        Text("Estado", modifier = Modifier.weight(0.6f))
        Text("Máquina", modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
        Text("Problema", modifier = Modifier.weight(1f), textAlign = TextAlign.End)
    }
}