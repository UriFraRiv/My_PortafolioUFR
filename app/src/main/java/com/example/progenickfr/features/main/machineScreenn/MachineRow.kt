package com.example.progenickfr.features.main.machineScreenn

import android.graphics.Color
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.airbnb.lottie.model.content.CircleShape


//@Composable
//fun MachineRow(
//    machine: MachineUiItem,
//    onClick: () -> Unit
//) {
//    Card(
//        modifier = Modifier.fillMaxWidth(),
//        shape = RoundedCornerShape(12.dp),
//        elevation = CardDefaults.cardElevation(4.dp)
//    ) {
//        Row(
//            modifier = Modifier
//                .padding(12.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//
//            Text(
//                text = machine.name,
//                modifier = Modifier.weight(1.2f),
//                fontWeight = FontWeight.Bold
//            )
//
//            Box(
//                modifier = Modifier
//                    .size(18.dp)
//                    .background(
//                        if (machine.isOk) Color(0xFF4CAF50) else Color(0xFFF44336),
//                        CircleShape
//                    )
//                    .clickable { onClick() }
//            )
//
//            Text(
//                text = machine.location,
//                modifier = Modifier.weight(1f),
//                textAlign = TextAlign.Center
//            )
//
//            Text(
//                text = "Eléctrico",
//                modifier = Modifier.weight(1f),
//                textAlign = TextAlign.End
//            )
//        }
//    }
//}