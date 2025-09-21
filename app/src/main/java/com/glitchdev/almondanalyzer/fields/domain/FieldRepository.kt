package com.glitchdev.almondanalyzer.fields.domain

interface FieldRepository {
    suspend fun getAllFields(): List<Field>
    suspend fun getFieldById(id: Long): Field?
    suspend fun addField(field: Field): Long
    suspend fun updateField(field: Field)
    suspend fun deleteFieldById(id: Long)
}
