package com.glitchdev.almondanalyzer.uploadscreen.data.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.glitchdev.almondanalyzer.uploadscreen.domain.repository.UploadScreenSettingsRepository

private const val PREFERENCE_NAME = "UploadScreenSettings"
private const val CAMERA_SETUP_KEY = "IsCameraSetupComplete"
private const val FILES_SETUP_KEY = "IsFilesSetupComplete"
private const val FILES_FULL_ACCESS_KEY = "IsFullAccessToFiles"

class UploadScreenSettingsRepositoryImpl(
    private val context: Context
): UploadScreenSettingsRepository {

    override val preferences: SharedPreferences
        get() = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    private var isCameraSetup: Boolean? = null
    override fun getIsCameraSetupComplete(): Boolean {
        return isCameraSetup ?: kotlin.run {
            isCameraSetup = preferences.getBoolean(CAMERA_SETUP_KEY, false)
            isCameraSetup!!
        }
    }
    override fun setIsCameraSetupComplete(value: Boolean) {
        isCameraSetup = value
        preferences.edit {
            putBoolean(CAMERA_SETUP_KEY, value)
        }
    }

    private var isFilesSetup: Boolean? = null
    override fun getIsFilesSetupComplete(): Boolean {
        return isFilesSetup ?: kotlin.run {
            isFilesSetup = preferences.getBoolean(FILES_SETUP_KEY, false)
            isFilesSetup!!
        }
    }
    override fun setIsFilesSetupComplete(value: Boolean) {
        isFilesSetup = value
        preferences.edit {
            putBoolean(FILES_SETUP_KEY, value)
        }
    }

    private var hasFullAccessToFiles: Boolean? = null
    override fun getIsFullAccessToFiles(): Boolean {
        return hasFullAccessToFiles ?: kotlin.run {
            hasFullAccessToFiles = preferences.getBoolean(FILES_FULL_ACCESS_KEY, false)
            hasFullAccessToFiles!!
        }
    }
    override fun setIsFullAccessToFiles(value: Boolean) {
        hasFullAccessToFiles = value
        preferences.edit {
            putBoolean(FILES_FULL_ACCESS_KEY, value)
        }
    }
}