package com.glitchdev.almondanalyzer.fields.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fields")
data class FieldEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val variety: String,
    val cadastralNumber: String,
    val plantingScheme: String,
    val plantingYear: Int,
    val seedlingsCount: Int
)
