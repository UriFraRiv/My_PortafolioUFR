package com.example.progenickfr.features.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.progenickfr.domain.model.Users
import com.example.progenickfr.domain.repository.UserRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: UserRepository) : ViewModel() {

    // Estado reactivo para el perfil
    var userProfile by mutableStateOf<Users?>(null)
        private set

    // Estado para el menú lateral
    var selectedDrawerIndex = mutableIntStateOf(0)

    fun cargarDatos(userId: String) {
        // Si ya hay datos, no pedimos de nuevo para ahorrar recursos
        if (userProfile != null) return

        viewModelScope.launch {
            repository.checkUserExists(userId) { user ->
                if (user != null) {
                    userProfile = user
                    println("DEBUG VM: Perfil actualizado en el ViewModel")
                } else {
                    println("DEBUG VM: El repositorio devolvió un usuario nulo")
                }
            }
        }
    }
}