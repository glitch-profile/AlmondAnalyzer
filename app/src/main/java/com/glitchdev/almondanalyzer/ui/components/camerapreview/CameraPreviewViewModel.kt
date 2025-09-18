package com.glitchdev.almondanalyzer.ui.components.camerapreview

import android.content.Context
import androidx.camera.core.CameraControl
import androidx.camera.core.CameraSelector
import androidx.camera.core.FocusMeteringAction
import androidx.camera.core.Preview
import androidx.camera.core.SurfaceOrientedMeteringPointFactory
import androidx.camera.core.SurfaceRequest
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.lifecycle.awaitInstance
import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CameraPreviewViewModel: ViewModel() {
    private val _surfaceRequests = MutableStateFlow<SurfaceRequest?>(null)
    val surfaceRequests = _surfaceRequests.asStateFlow()

    private var cameraProvider: ProcessCameraProvider? = null
    private var surfaceMeteringPointFactory: SurfaceOrientedMeteringPointFactory? = null
    private var cameraControl: CameraControl? = null

    private val cameraPreviewUseCase = Preview.Builder().build().apply {
        setSurfaceProvider { newSurfaceRequest ->
            _surfaceRequests.update { newSurfaceRequest }
            surfaceMeteringPointFactory = SurfaceOrientedMeteringPointFactory(
                newSurfaceRequest.resolution.width.toFloat(),
                newSurfaceRequest.resolution.height.toFloat()
            )
        }
    }

    suspend fun bindToCamera(
        context: Context,
        lifecycleOwner: LifecycleOwner,
        isUseBackCamera: Boolean = true,
        onBindComplete: (() -> Unit)? = null
    ) {
        val localCameraProvider = cameraProvider ?: kotlin.run {
            val camera = ProcessCameraProvider.awaitInstance(context)
            cameraProvider = camera
            cameraProvider
        }
        val camera = localCameraProvider!!.bindToLifecycle(
            lifecycleOwner = lifecycleOwner,
            cameraSelector = if (isUseBackCamera) CameraSelector.DEFAULT_BACK_CAMERA
                else CameraSelector.DEFAULT_FRONT_CAMERA,
            cameraPreviewUseCase
        )
        cameraControl = camera.cameraControl
        onBindComplete?.invoke()
        // await cancellation
        try {
            awaitCancellation()
        } finally {
            unbindCamera()
        }
    }

    fun unbindCamera() {
        cameraProvider?.unbindAll()
        cameraControl = null
    }

    fun tapToFocus(tapLocation: Offset) {
        val point = surfaceMeteringPointFactory?.createPoint(tapLocation.x, tapLocation.y)
        if (point != null) {
            val focusAction = FocusMeteringAction.Builder(point).build()
            cameraControl?.startFocusAndMetering(focusAction)
        }

    }
}