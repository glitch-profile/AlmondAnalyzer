package com.glitchdev.almondanalyzer.ui.components

import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LocalLifecycleOwner

@Composable
fun CameraView(
    cameraController: LifecycleCameraController,
    modifier: Modifier = Modifier,
    onUpdateStreamState: ((isStreamAvailable: Boolean) -> Unit)? = null
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    Box(
        modifier = modifier
            .clipToBounds()
    ) {
        AndroidView(
            factory = { context ->
                PreviewView(context).apply {
                    this.previewStreamState.observe(lifecycleOwner) { streamState ->
                        onUpdateStreamState?.invoke(streamState == PreviewView.StreamState.STREAMING)
                    }
                    this.controller = cameraController
                    this.scaleType = PreviewView.ScaleType.FILL_CENTER
                    this.implementationMode = PreviewView.ImplementationMode.COMPATIBLE
                    cameraController.bindToLifecycle(lifecycleOwner)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(900.dp)
        )
    }
}