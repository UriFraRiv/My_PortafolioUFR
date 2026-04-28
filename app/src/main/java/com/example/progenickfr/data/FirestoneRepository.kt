package com.example.progenickfr.data


import com.example.progenickfr.domain.model.MaterialNeedStore
import com.example.progenickfr.domain.model.NewReports
import com.example.progenickfr.domain.model.Reports
import com.example.progenickfr.domain.model.Users
import com.example.progenickfr.domain.repository.UserRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FieldValue

class FirestoneRepository : UserRepository {
    private val db = FirebaseFirestore.getInstance()

    override fun guardarReporte(reporte: Reports, onResult: (Boolean) -> Unit) {
        val nuevoReporte = hashMapOf(
            "area" to reporte.area,
            "attends" to reporte.attends,
            "created_by" to reporte.created_by,
            "material" to reporte.material,
            "observations" to reporte.observations,
            "problem" to reporte.problem,
            "shift" to reporte.shift,
            "status" to reporte.status,
            "time" to FieldValue.serverTimestamp()
        )

        db.collection("Reports")
            .add(nuevoReporte)
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { onResult(false) }
    }

    override fun checkUserExists(userId: String, onResult: (Users?) -> Unit) {
        val cleanId = userId.trim()

        db.collection("User").document(cleanId).get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    // Intento de mapeo automático
                    var user = document.toObject(Users::class.java)

                    // Mapeo manual de seguridad (Si el automático falla)
                    if (user == null || user.name.isEmpty()) {
                        user = Users(
                            id = document.id,
                            name = document.getString("name") ?: "Usuario sin nombre",
                            position = document.getString("position") ?: "Puesto no asignado",
                            shift = document.getString("shift") ?: "Turno no asignado",
                            nomina = document.getString("nomina")?:"Inexistente"
                        )
                    }
                    println("DEBUG: Usuario cargado -> ${user.name}")
                    onResult(user)
                } else {
                    println("DEBUG: El documento $cleanId no existe en la colección Users")
                    onResult(null)
                }
            }
            .addOnFailureListener { e ->
                println("DEBUG: Error de Firestore: ${e.message}")
                onResult(null)
            }
    }

    override fun newReports (descriptionFail: NewReports, onResult: (Boolean) -> Unit) {
        val nuevoReporte = hashMapOf(
            "fail_machine" to descriptionFail.fail_machine, // Correcto
            "material_need" to descriptionFail.material,    // Correcto
            "observations" to descriptionFail.observations, // AQUÍ VA EL TEXTO, no el ID
            "created_by" to descriptionFail.created_by,     // AQUÍ VA EL ID
            "area" to descriptionFail.area,
            "attends" to descriptionFail.attends,
            "time" to FieldValue.serverTimestamp()          // Opcional: añade la hora del reporte
        )

        db.collection("NewReports")
            .add(nuevoReporte)
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { onResult(false) }
    }
    override fun  materialneed(takeMaterial: MaterialNeedStore, onResult: (Boolean) -> Unit){
        val materiala= hashMapOf(
            "take_material" to takeMaterial.take_material,  //
            "search_material" to takeMaterial.search_material ,
            "place_work" to takeMaterial.place_store ,
            "place_store" to takeMaterial.place_store ,
            "created_by" to FieldValue.serverTimestamp()

        )
        db.collection("MaterialA")
            .add(materiala)
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { onResult(false) }

    }
}