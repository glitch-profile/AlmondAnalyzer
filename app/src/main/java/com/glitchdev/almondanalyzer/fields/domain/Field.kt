package com.glitchdev.almondanalyzer.fields.domain

data class Field(
    val id: Long = 0,
    val name: String,
    val variety: String,
    val cadastralNumber: String,
    val plantingScheme: String,
    val plantingYear: Int,
    val seedlingsCount: Int
)