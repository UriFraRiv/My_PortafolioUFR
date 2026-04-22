package com.example.progenickfr.domain.model


    data class NewReports(
        val fail_machine: String = "",
        val material: String = "",
        val observations: String = "",
        val created_by: String = "",
        val area: String = "",      // Agrégalos si los usas en el Repositorio
        val attends: String = ""    // Agrégalos si los usas en el Repositorio
    )
