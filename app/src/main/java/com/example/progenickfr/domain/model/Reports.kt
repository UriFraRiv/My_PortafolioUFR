package com.example.progenickfr.domain.model

data class Reports(
    val id: String = "",
    val area: String = "",
    val attends: String = "",
    val created_by: String = "",
    val material: String = "",
    val observations: String = "",
    val problem: String = "",
    val shift: String = "",
    val status: String = "Pendiente",
)