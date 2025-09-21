package com.glitchdev.almondanalyzer.fields.data

import com.glitchdev.almondanalyzer.fields.domain.Field
import com.glitchdev.almondanalyzer.fields.domain.FieldRepository

class FieldRepositoryImpl(
    private val dao: FieldDao
) : FieldRepository {

    override suspend fun getAllFields(): List<Field> {
        return dao.getAllFields().map { it.toDomain() }
    }

    override suspend fun getFieldById(id: Long): Field? =
        dao.getFieldById(id)?.toDomain()

    override suspend fun addField(field: Field): Long {
        return dao.insertField(field.toEntity())
    }

    override suspend fun updateField(field: Field) {
        dao.updateField(field.toEntity())
    }

    override suspend fun deleteFieldById(id: Long) =
        dao.deleteFieldById(id)

    fun FieldEntity.toDomain(): Field {
        return Field(
            id = id,
            name = name,
            variety = variety,
            cadastralNumber = cadastralNumber,
            plantingScheme = plantingScheme,
            plantingYear = plantingYear,
            seedlingsCount = seedlingsCount
        )
    }

    fun Field.toEntity(): FieldEntity {
        return FieldEntity(
            id = id,
            name = name,
            variety = variety,
            cadastralNumber = cadastralNumber,
            plantingScheme = plantingScheme,
            plantingYear = plantingYear,
            seedlingsCount = seedlingsCount
        )
    }
}
