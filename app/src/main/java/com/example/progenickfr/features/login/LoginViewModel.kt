package com.example.progenickfr.features.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class PerfilViewModel : ViewModel() {
    // Datos del usuario
    val userName = mutableStateOf("User")
    val attendance = mutableStateOf(5)
    val absences = mutableStateOf(2)
    val position = mutableStateOf("Desarrollador")

    // Código del usuario
    val code = mutableStateOf("") // <- AQUÍ estaba el problema

    // Función para actualizar posición según código
    fun updatePositionFromCode() {
        position.value = when (code.value) {
            "1111" -> "Desarrollador"
            "2222" -> "Administrador"
            "3333" -> "Ingeniero"
            "4444" -> "Técnico"
            else -> "Invitado"
        }
    }

    // Drawer seleccionado
    val selectedDrawerIndex = mutableStateOf(0)
}