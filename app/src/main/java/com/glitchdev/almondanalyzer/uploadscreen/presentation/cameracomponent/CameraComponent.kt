package com.glitchdev.almondanalyzer.uploadscreen.presentation.cameracomponent

import android.graphics.Bitmap
import android.graphics.Matrix
import android.net.Uri
import android.util.Size
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageProxy
import androidx.camera.core.resolutionselector.ResolutionSelector
import androidx.camera.core.resolutionselector.ResolutionStrategy
import androidx.camera.core.resolutionselector.ResolutionStrategy.FALLBACK_RULE_CLOSEST_HIGHER_THEN_LOWER
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.glitchdev.almondanalyzer.R
import com.glitchdev.almondanalyzer.ui.components.AppButtonDefaults
import com.glitchdev.almondanalyzer.ui.components.AppIconButton
import com.glitchdev.almondanalyzer.ui.components.CameraView
import com.glitchdev.almondanalyzer.ui.icons.AppIcons
import com.glitchdev.almondanalyzer.ui.icons.svgs.Camera
import com.glitchdev.almondanalyzer.ui.icons.svgs.CameraUnavailable
import com.glitchdev.almondanalyzer.ui.icons.svgs.Camerafilled
import com.glitchdev.almondanalyzer.ui.icons.svgs.Clear
import com.glitchdev.almondanalyzer.ui.icons.svgs.Upload
import com.glitchdev.almondanalyzer.ui.theme.AppTheme
import com.glitchdev.almondanalyzer.ui.theme.appSpringDefault
import java.io.File
import java.time.OffsetDateTime
import java.time.ZoneId

@Composable
fun CameraComponent(
    modifier: Modifier = Modifier,
    state: CameraComponentState,
    onNoPermissionClicked: () -> Unit,
    onExpandButtonClicked: () -> Unit,
    onCollapseButtonClicked: () -> Unit,
    onSwitchCameraButtonClicked: () -> Unit,
    onUpdateCameraStreamStatus: (isAvailable: Boolean) -> Unit,
    onPhotoTaken: (photoUri: Uri) -> Unit
) {
    val context = LocalContext.current

    val cameraController = remember {
        val resolutionSelector = ResolutionSelector.Builder()
            .setResolutionStrategy(ResolutionStrategy(Size(960, 1280), FALLBACK_RULE_CLOSEST_HIGHER_THEN_LOWER))
            .build()
        LifecycleCameraController(context.applicationContext).apply {
            setEnabledUseCases(
                CameraController.IMAGE_CAPTURE
            )
            imageCaptureMode = ImageCapture.CAPTURE_MODE_MAXIMIZE_QUALITY
            imageCaptureResolutionSelector = resolutionSelector
        }
    }

    val cameraTintAlpha by animateFloatAsState(
        targetValue = if (state.isExpanded)
            0f
        else {
            when (state.cameraStatus) {
                CameraStatusState.READY -> 0.8f
                else -> 1f
            }
        },
        animationSpec = appSpringDefault()
    )

    LaunchedEffect(state.isBackCamera) {
        if (state.cameraStatus == CameraStatusState.READY) {
            cameraController.cameraSelector = if (state.isBackCamera) CameraSelector.DEFAULT_BACK_CAMERA
                else CameraSelector.DEFAULT_FRONT_CAMERA
        }
    }

    Box(
        modifier = modifier
            .clipToBounds()
    ) {
        if (state.cameraStatus == CameraStatusState.READY) {
            CameraView(
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer(alpha = if (state.cameraStreamStatus == CameraStreamStatus.OK) 1f else 0f),
                cameraController = cameraController,
                onUpdateStreamState = onUpdateCameraStreamStatus
            )
        }

        AnimatedVisibility(
            modifier = Modifier.fillMaxSize(),
            visible = state.isExpanded,
            enter = fadeIn(appSpringDefault()),
            exit = fadeOut(appSpringDefault())
        ) {
            AnimatedContent(
                modifier = Modifier.align(Alignment.Center),
                targetState = state.cameraStreamStatus,
                transitionSpec = { fadeIn(appSpringDefault()) togetherWith fadeOut(appSpringDefault()) },
                contentAlignment = Alignment.Center
            ) { streamStatus ->
                when (streamStatus) {
                    CameraStreamStatus.LOADING -> CameraStreamLoadingMessage()
                    CameraStreamStatus.ERROR -> CameraStreamErrorMessage()
                    else -> {}
                }
            }
        }

        AnimatedVisibility(
            visible = state.isExpanded,
            enter = expandVertically(appSpringDefault()) + fadeIn(appSpringDefault()),
            exit = shrinkVertically(appSpringDefault()) + fadeOut(appSpringDefault()),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(bottom = AppTheme.size.large)
        ) {
            CameraControlsComponent(
                onCloseCameraButtonClicked = onCollapseButtonClicked,
                onTakePhotoButtonClicked = {
                    if ((state.isExpanded)
                        && (state.cameraStatus == CameraStatusState.READY)
                        && (state.cameraStreamStatus == CameraStreamStatus.OK)
                    ) {
                        cameraController.takePicture(
                            ContextCompat.getMainExecutor(context),
                            object: ImageCapture.OnImageCapturedCallback() {
                                override fun onCaptureSuccess(image: ImageProxy) {
                                    super.onCaptureSuccess(image)
                                    val fileName =
                                        "${OffsetDateTime.now(ZoneId.systemDefault()).toEpochSecond()}.jpg"
                                    val fileCacheDir = context.cacheDir.resolve("Camera").apply { mkdirs() }
                                    val imageFile = File(fileCacheDir, fileName)
                                    val matrix = Matrix().apply {
                                        postRotate(image.imageInfo.rotationDegrees.toFloat())
                                    }
                                    val rotatedBitmap = Bitmap.createBitmap(
                                        image.toBitmap(),
                                        0,
                                        0,
                                        image.width,
                                        image.height,
                                        matrix,
                                        true
                                    )
                                    imageFile.outputStream().use { stream ->
                                        rotatedBitmap.compress(Bitmap.CompressFormat.JPEG, 75, stream)
                                    }
                                    rotatedBitmap.recycle()
                                    onPhotoTaken.invoke(imageFile.toUri())
                                }
                            }
                        )
                    }
                },
                onSwitchCameraButtonClicked = onSwitchCameraButtonClicked
            )
        }

        if (cameraTintAlpha != 0f) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(cameraTintAlpha)
                    .background(AppTheme.colorScheme.surface)
                    .clickable(
                        enabled = state.cameraStatus != CameraStatusState.INITIALIZING && !state.isExpanded,
                        interactionSource = null,
                        onClick = {
                            when (state.cameraStatus) {
                                CameraStatusState.NO_PERMISSIONS -> onNoPermissionClicked.invoke()
                                CameraStatusState.READY -> {
                                    if (!state.isExpanded) onExpandButtonClicked.invoke()
                                }

                                else -> Unit
                            }
                        }
                    )
            ) {
                Crossfade(
                    targetState = state.cameraStatus != CameraStatusState.INITIALIZING,
                    animationSpec = spring(stiffness = Spring.StiffnessMedium)
                ) { isCameraReady ->
                    if (isCameraReady) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(AppTheme.size.medium),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Crossfade(
                                targetState = state.cameraStatus == CameraStatusState.NO_PERMISSIONS,
                                animationSpec = appSpringDefault()
                            ) { isCameraUnavailable ->
                                if (isCameraUnavailable) {
                                    Icon(
                                        imageVector = AppIcons.CameraUnavailable,
                                        contentDescription = null,
                                        tint = AppTheme.colorScheme.onSurface
                                    )
                                } else {
                                    Icon(
                                        imageVector = AppIcons.Camera,
                                        contentDescription = null,
                                        tint = AppTheme.colorScheme.onSurface
                                    )
                                }
                            }
                            Spacer(Modifier.width(AppTheme.size.small))
                            Column(
                                modifier = Modifier
                                    .animateContentSize(appSpringDefault())
                            ) {
                                AnimatedContent(
                                    targetState = state.cameraStatus == CameraStatusState.NO_PERMISSIONS,
                                    transitionSpec = {
                                        fadeIn(appSpringDefault()) togetherWith fadeOut(appSpringDefault())
                                    }
                                ) { isCameraUnavailable ->
                                    if (isCameraUnavailable) {
                                        Text(
                                            text = stringResource(R.string.upload_image_camera_component_camera_not_ready_title),
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis,
                                            style = AppTheme.typography.titleMedium,
                                            color = AppTheme.colorScheme.onSurface
                                        )
                                    } else {
                                        Text(
                                            text = stringResource(R.string.upload_image_camera_component_camera_ready_title),
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis,
                                            style = AppTheme.typography.titleMedium,
                                            color = AppTheme.colorScheme.onSurface
                                        )
                                    }
                                }
                                AnimatedContent(
                                    targetState = state.cameraStatus == CameraStatusState.NO_PERMISSIONS,
                                    transitionSpec = {
                                        fadeIn(appSpringDefault()) togetherWith fadeOut(appSpringDefault())
                                    }
                                ) { isCameraUnavailable ->
                                    if (isCameraUnavailable) {
                                        Text(
                                            text = stringResource(R.string.upload_image_camera_component_no_permissions_text),
                                            maxLines = 2,
                                            overflow = TextOverflow.Ellipsis,
                                            style = AppTheme.typography.bodyMedium,
                                            color = AppTheme.colorScheme.onSurface
                                        )
                                    } else {
                                        Text(
                                            text = stringResource(R.string.upload_image_camera_component_camera_ready_text),
                                            maxLines = 2,
                                            overflow = TextOverflow.Ellipsis,
                                            style = AppTheme.typography.bodyMedium,
                                            color = AppTheme.colorScheme.onSurface
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CameraControlsComponent(
    onCloseCameraButtonClicked: () -> Unit,
    onTakePhotoButtonClicked: () -> Unit,
    onSwitchCameraButtonClicked: () -> Unit,
    sideButtonSize: Dp = 56.dp,
    mainButtonSize: Dp = 64.dp,
    buttonsSpacing: Dp = 32.dp
) {
    val sideButtonsColor = AppButtonDefaults.textButtonColors().copy(
        containerColor = AppTheme.colorScheme.surface.copy(alpha = 0.6f),
        contentColor = AppTheme.colorScheme.onSurfaceVariant
    )
    val mainButtonColor = AppButtonDefaults.textButtonColors().copy(
        containerColor = AppTheme.colorScheme.surface.copy(alpha = 0.8f),
        contentColor = AppTheme.colorScheme.onSurface
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        AppIconButton(
            modifier = Modifier
                .size(sideButtonSize),
            onClick = onCloseCameraButtonClicked,
            colors = sideButtonsColor
        ) {
            Icon(
                imageVector = AppIcons.Clear,
                contentDescription = null
            )
        }
        Spacer(Modifier.width(buttonsSpacing))
        AppIconButton(
            modifier = Modifier
                .size(mainButtonSize),
            onClick = onTakePhotoButtonClicked,
            colors = mainButtonColor
        ) {
            Icon(
                imageVector = AppIcons.Camerafilled,
                contentDescription = null
            )
        }
        Spacer(Modifier.width(buttonsSpacing))
        AppIconButton(
            modifier = Modifier
                .size(sideButtonSize),
            onClick = onSwitchCameraButtonClicked,
            colors = sideButtonsColor
        ) {
            Icon(
                imageVector = AppIcons.Upload,
                contentDescription = null
            )
        }
    }
}

@Composable
private fun CameraStreamErrorMessage() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier
                .size(AppTheme.size.extraLarge),
            imageVector = AppIcons.CameraUnavailable,
            contentDescription = null,
            tint = AppTheme.colorScheme.onSurface
        )
        Text(
            text = stringResource(R.string.upload_image_camera_component_camera_preview_error_title),
            style = AppTheme.typography.titleMedium,
            color = AppTheme.colorScheme.onSurface
        )
        Text(
            text = stringResource(R.string.upload_image_camera_component_camera_preview_error_text),
            textAlign = TextAlign.Center,
            style = AppTheme.typography.bodyMedium,
            color = AppTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun CameraStreamLoadingMessage() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.upload_image_camera_component_camera_preview_loading_title),
            style = AppTheme.typography.titleMedium,
            color = AppTheme.colorScheme.onSurface
        )
        Text(
            text = stringResource(R.string.upload_image_camera_component_camera_preview_loading_text),
            textAlign = TextAlign.Center,
            style = AppTheme.typography.bodyMedium,
            color = AppTheme.colorScheme.onSurfaceVariant
        )
    }
}

