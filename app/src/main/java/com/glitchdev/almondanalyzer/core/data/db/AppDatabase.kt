package com.glitchdev.almondanalyzer.core.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.glitchdev.almondanalyzer.fields.data.FieldDao
import com.glitchdev.almondanalyzer.fields.data.FieldEntity

@Database(
    entities = [FieldEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun fieldDao(): FieldDao
}
