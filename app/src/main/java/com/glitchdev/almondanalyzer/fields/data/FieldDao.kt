package com.glitchdev.almondanalyzer.fields.data

import androidx.room.*

@Dao
interface FieldDao {

    @Query("SELECT * FROM fields")
    suspend fun getAllFields(): List<FieldEntity>

    @Query("SELECT * FROM fields WHERE id = :id")
    suspend fun getFieldById(id: Long): FieldEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertField(field: FieldEntity): Long

    @Update
    suspend fun updateField(field: FieldEntity)

    @Query("DELETE FROM fields WHERE id = :id")
    suspend fun deleteFieldById(id: Long)
}