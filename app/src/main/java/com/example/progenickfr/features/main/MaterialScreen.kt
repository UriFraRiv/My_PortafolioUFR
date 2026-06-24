package com.example.progenickfr.features.main


import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import coil.compose.rememberAsyncImagePainter
import materialneed
import java.io.File


@Composable
fun MaterialWarehouse(
    homeViewModel: HomeViewModel,
    materialViewModel: materialneed
) {
    var photoUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) { materialViewModel.selectedImageUri = photoUri }
    }

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        materialViewModel.selectedImageUri = uri
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "FRPROGENICK", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color(0xFF4CAF50))
        Text(text = "Control de Almacén", fontSize = 16.sp, color = Color.Gray)

        Spacer(Modifier.height(24.dp))

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
                    textStyle = TextStyle(color = Color.White),
                    singleLine = true
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E)),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Evidencia Visual", color = Color.White, fontWeight = FontWeight.Medium, modifier = Modifier.fillMaxWidth())
                Spacer(Modifier.height(16.dp))

                if (materialViewModel.selectedImageUri != null) {
                    Box(contentAlignment = Alignment.TopEnd) {
                        Image(
                            painter = rememberAsyncImagePainter(materialViewModel.selectedImageUri),
                            contentDescription = null,
                            modifier = Modifier.size(150.dp).clip(RoundedCornerShape(12.dp)),
                            contentScale = ContentScale.Crop
                        )
                        IconButton(
                            onClick = { materialViewModel.selectedImageUri = null },
                            modifier = Modifier.background(Color.Black.copy(alpha = 0.5f), CircleShape)
                        ) {
                            Icon(Icons.Default.Close, contentDescription = null, tint = Color.White)
                        }
                    }
                } else {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                        // Botón Cámara
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            IconButton(
                                onClick = {
                                    val file = File(context.cacheDir, "temp_${System.currentTimeMillis()}.jpg")
                                    val uri = FileProvider.getUriForFile(context, "${context.packageName}.provider", file)
                                    photoUri = uri
                                    cameraLauncher.launch(uri)
                                },
                                modifier = Modifier.size(60.dp).background(Color(0xFF333333), CircleShape)
                            ) {
                                Icon(Icons.Default.AddCircle, contentDescription = null, tint = Color.White, modifier = Modifier.size(30.dp))
                            }
                            Text("Cámara", color = Color.Gray, fontSize = 12.sp)
                        }

                        // Botón Galería
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            IconButton(
                                onClick = { galleryLauncher.launch("image/*") },
                                modifier = Modifier.size(60.dp).background(Color(0xFF333333), CircleShape)
                            ) {
                                Icon(Icons.Default.List, contentDescription = null, tint = Color.White, modifier = Modifier.size(30.dp))
                            }
                            Text("Galería", color = Color.Gray, fontSize = 12.sp)
                        }
                    }
                }
            }
        }

        Spacer(Modifier.height(16.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E)),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                OutlinedTextField(
                    value = materialViewModel.materialTake,
                    onValueChange = { materialViewModel.materialTake = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Cantidad a tomar") },
                    textStyle = TextStyle(color = Color.White)
                )
                Spacer(Modifier.height(12.dp))
                OutlinedTextField(
                    value = materialViewModel.placeWork,
                    onValueChange = { materialViewModel.placeWork = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Área / Proyecto / Máquina") },
                    textStyle = TextStyle(color = Color.White)
                )
            }
        }

        Spacer(Modifier.weight(1f))

        Button(
            onClick = {
                materialViewModel.onSendClick()
            },
            enabled = !materialViewModel.isSyncing && materialViewModel.selectedImageUri != null,
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4CAF50),
                disabledContainerColor = Color.Gray
            )
        ) {
            if (materialViewModel.isSyncing) {
                CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
            } else {
                Text("ENVIAR REPORTE", fontWeight = FontWeight.Bold)
            }
        }
    }
}