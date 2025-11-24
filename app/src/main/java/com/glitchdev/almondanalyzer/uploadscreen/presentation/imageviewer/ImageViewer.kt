package com.glitchdev.almondanalyzer.uploadscreen.presentation.imageviewer

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import com.glitchdev.almondanalyzer.ui.components.AppPopupDialog
import com.glitchdev.almondanalyzer.ui.components.LoadingComponent

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun ImageViewerScreen(
    imageUri: Uri? = null,
    onDismiss: () -> Unit
) {
    var lastUsedImageUri by remember { mutableStateOf(Uri.EMPTY) }
    LaunchedEffect(imageUri) {
        if (imageUri != null) lastUsedImageUri = imageUri
    }
    AppPopupDialog(
        visible = imageUri != null,
        onDismiss = onDismiss
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            var isImageLoading by remember {
                mutableStateOf(false)
            }

            var scale by remember {
                mutableFloatStateOf(1f)
            }
            var offset by remember {
                mutableStateOf(Offset.Zero)
            }

            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth(),
                content = {
                    val state = rememberTransformableState { zoomChange, panChange, _ ->
                        scale = (scale * zoomChange).coerceIn(1f, 5f)

                        val extraWidth = (scale - 1) * constraints.maxWidth
                        val extraHeight = (scale - 1) * constraints.maxHeight

                        val maxX = extraWidth / 2
                        val maxY = extraHeight / 2

                        offset = Offset(
                            x = (offset.x + scale * panChange.x).coerceIn(-maxX, maxX),
                            y = (offset.y + scale * panChange.y).coerceIn(-maxY, maxY)
                        )

                    }
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .graphicsLayer {
                                scaleX = scale
                                scaleY = scale
                                translationX = offset.x
                                translationY = offset.y
                            }
                            .transformable(state),
                        model = lastUsedImageUri,
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        onLoading = { isImageLoading = true },
                        onSuccess = { isImageLoading = false }
                    )
                }
            )

            if (isImageLoading) {
                LoadingComponent()
            }

        }
    }
}