package com.glitchdev.almondanalyzer.ui.components.camerapreview

import androidx.camera.compose.CameraXViewfinder
import androidx.camera.core.SurfaceRequest
import androidx.camera.viewfinder.core.ImplementationMode
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun CameraPreview(
    modifier: Modifier = Modifier,
    surfaceRequest: SurfaceRequest?
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        if (surfaceRequest != null) {
            CameraXViewfinder(
                modifier = Modifier
                    .fillMaxSize(),
                surfaceRequest = surfaceRequest,
                implementationMode = ImplementationMode.EMBEDDED
            )
        }
    }
}