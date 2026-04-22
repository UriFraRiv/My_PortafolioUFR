package com.example.progenickfr.features.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.progenickfr.domain.repository.UserRepository // Importamos la Interfaz
import com.google.firebase.auth.FirebaseAuth

// Ahora depende de la Interfaz (UserRepository), no de la clase directa
class LoginViewModel(private val repository: UserRepository) : ViewModel() {

    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var isLoading by mutableStateOf(false)
    var errorMsg by mutableStateOf<String?>(null)

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun iniciarSesion(onSuccess: (String) -> Unit) {
        if (email.isEmpty() || password.isEmpty()) {
            errorMsg = "Completa todos los campos"
            return
        }

        isLoading = true
        errorMsg = null

        // PASO 1: Autenticar con Firebase Auth
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { result ->
                val uid = result.user?.uid ?: ""

                // PASO 2: Validar en Firestore usando nuestro Repositorio
                repository.checkUserExists(uid) { user ->
                    isLoading = false
                    if (user != null) {
                        // PASO 3: Éxito total
                        onSuccess(uid)
                    } else {
                        errorMsg = "Usuario autenticado, pero perfil no encontrado en base de datos."
                    }
                }
            }
            .addOnFailureListener { exception ->
                isLoading = false
                // Aquí podrías ser más específico con el error si quisieras
                errorMsg = "Credenciales incorrectas o error de red"
            }
    }
}