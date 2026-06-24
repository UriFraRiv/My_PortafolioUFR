package com.example.progenickfr.features.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.progenickfr.domain.model.NewReports
import com.example.progenickfr.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth


    class MachineViewModel(private val repository: UserRepository) : ViewModel() {
        // Estados para la UI
        var description_fault by mutableStateOf("")
        var need_material by mutableStateOf("")
        var observations by mutableStateOf("")
        var isSyncing by mutableStateOf(false)

        fun sendReport(userId: String) {
            isSyncing = true
            val newReport = NewReports(
                fail_machine = description_fault,
                material = need_material,
                observations = observations,
                created_by = userId
            )
            repository.newReports(newReport) { success ->
                isSyncing = false // Terminamos la carga
                if (success) {
                    // Limpiamos los campos después de enviar
                    description_fault = ""
                    need_material = ""
                    observations = ""
                }
            }

        }
        private val auth = FirebaseAuth.getInstance()

        fun onSendClick() {
            val uid = auth.currentUser?.uid ?: "anonimo"
            sendReport(uid)
        }
    }




