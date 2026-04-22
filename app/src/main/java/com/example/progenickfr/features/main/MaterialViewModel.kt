package com.example.progenickfr.features.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.progenickfr.domain.model.MaterialNeedStore
import com.example.progenickfr.domain.model.NewReports
import com.example.progenickfr.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth


   class materialneed (private val repository: UserRepository) : ViewModel() {
        var searchMAterial by mutableStateOf("")
    var materialTake by mutableStateOf("")
    var placeWork by mutableStateOf("")
    var placeStore by mutableStateOf("")

       var isSyncing by mutableStateOf(false)

    fun sendReport(userId: String) {
        isSyncing = true // Iniciamos la carga

        // Creamos el objeto con los datos actuales
        val need_material = MaterialNeedStore(
            search_material = searchMAterial,
            place_work =placeWork ,
            place_store = placeStore,
            take_material =materialTake ,
                    created_by = userId  // El UID que ya tienes
        )

        // Llamamos al repositorio que creaste
        repository.materialneed(need_material) { success ->
            isSyncing = false // Terminamos la carga
            if (success) {
                // Limpiamos los campos después de enviar
                searchMAterial = ""
                placeWork = ""
                placeStore = ""
                materialTake = ""
            }
        }

    }
    private val auth = FirebaseAuth.getInstance()

    fun onSendClick() {
        val uid = auth.currentUser?.uid ?: "anonimo"
        sendReport(uid)
    }
}