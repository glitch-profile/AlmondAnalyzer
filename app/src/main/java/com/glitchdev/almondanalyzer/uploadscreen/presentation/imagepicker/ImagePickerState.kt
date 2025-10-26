package com.glitchdev.almondanalyzer.uploadscreen.presentation.imagepicker

import android.net.Uri

data class ImagePickerState(
    val hasPermissions: Boolean = false,
    val images: List<Uri> = listOf(),
    val selectedImages: List<Uri> = listOf()
)