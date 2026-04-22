package com.example.progenickfr.domain.repository


import com.example.progenickfr.domain.model.MaterialNeedStore
import com.example.progenickfr.domain.model.NewReports
import com.example.progenickfr.domain.model.Reports
import com.example.progenickfr.domain.model.Users

interface UserRepository {
    // Definimos QUÉ queremos hacer, pero no CÓMO se hace
    fun checkUserExists(userId: String, onResult: (Users?) -> Unit)
    fun guardarReporte(reporte: Reports, onResult: (Boolean) -> Unit)

    fun newReports(descriptionFail: NewReports, onResult: (Boolean) -> Unit)

    fun materialneed(materialstore: MaterialNeedStore, onResult: (Boolean) -> Unit)
}