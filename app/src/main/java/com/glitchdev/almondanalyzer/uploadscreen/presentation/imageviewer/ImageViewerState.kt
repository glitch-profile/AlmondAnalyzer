package com.glitchdev.almondanalyzer.uploadscreen.presentation.imageviewer

import android.net.Uri

data class ImageViewerState(
    val isImageOpen: Boolean = false,
    val imageUri: Uri = Uri.EMPTY
)