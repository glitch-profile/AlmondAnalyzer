package com.glitchdev.almondanalyzer.uploadscreen.domain.repository

import android.content.SharedPreferences

interface UploadScreenSettingsRepository {

    val preferences: SharedPreferences

    fun getIsCameraSetupComplete(): Boolean
    fun setIsCameraSetupComplete(value: Boolean)

    fun getIsFilesSetupComplete(): Boolean
    fun setIsFilesSetupComplete(value: Boolean)

    fun getIsFullAccessToFiles(): Boolean
    fun setIsFullAccessToFiles(value: Boolean)

}