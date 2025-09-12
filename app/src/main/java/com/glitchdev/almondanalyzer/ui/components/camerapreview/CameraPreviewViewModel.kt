package com.glitchdev.almondanalyzer.ui.components.camerapreview

import android.content.Context
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.core.SurfaceRequest
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.lifecycle.awaitInstance
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CameraPreviewViewModel: ViewModel() {
    private val _cameraState = MutableStateFlow(CameraPreviewState())
    val cameraState = _cameraState.asStateFlow()

    private val _surfaceRequests = MutableStateFlow<SurfaceRequest?>(null)
    val surfaceRequests = _surfaceRequests.asStateFlow()

    private val cameraPreviewUseCase = Preview.Builder().build().apply {
        setSurfaceProvider { newSurfaceRequest ->
            _surfaceRequests.update { newSurfaceRequest }
        }
    }

    suspend fun bindToCamera(
        context: Context,
        lifecycleOwner: LifecycleOwner
    ) {
        val processCameraProvider = ProcessCameraProvider.awaitInstance(context)
        processCameraProvider.bindToLifecycle(
            lifecycleOwner = lifecycleOwner,
            cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA,
            cameraPreviewUseCase
        )

        // await cancellation
        try {
            awaitCancellation()
        } finally {
            processCameraProvider.unbindAll()
        }
    }
}