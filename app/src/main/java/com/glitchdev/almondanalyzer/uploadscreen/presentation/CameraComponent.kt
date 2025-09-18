package com.glitchdev.almondanalyzer.uploadscreen.presentation

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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.glitchdev.almondanalyzer.R
import com.glitchdev.almondanalyzer.ui.components.AppButton
import com.glitchdev.almondanalyzer.ui.components.AppButtonDefaults
import com.glitchdev.almondanalyzer.ui.components.AppIconButton
import com.glitchdev.almondanalyzer.ui.components.camerapreview.CameraPreview
import com.glitchdev.almondanalyzer.ui.components.camerapreview.CameraPreviewViewModel
import com.glitchdev.almondanalyzer.ui.icons.AppIcons
import com.glitchdev.almondanalyzer.ui.icons.svgs.Camera
import com.glitchdev.almondanalyzer.ui.icons.svgs.CameraUnavailable
import com.glitchdev.almondanalyzer.ui.icons.svgs.Camerafilled
import com.glitchdev.almondanalyzer.ui.icons.svgs.Clear
import com.glitchdev.almondanalyzer.ui.icons.svgs.Upload
import com.glitchdev.almondanalyzer.ui.theme.AppTheme
import com.glitchdev.almondanalyzer.ui.theme.appSpringDefault
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun CameraComponent(
    modifier: Modifier = Modifier,
    state: CameraComponentState,
    onNoPermissionClicked: () -> Unit,
    onExpandButtonClicked: () -> Unit,
    onCollapseButtonClicked: () -> Unit,
    onSwitchCameraButtonClicked: () -> Unit,
    onPhotoTaken: (photoUri: String) -> Unit
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val cameraViewModel: CameraPreviewViewModel = koinViewModel()
    val surfaceRequest by cameraViewModel.surfaceRequests.collectAsState()

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

    LaunchedEffect(lifecycleOwner) {
        cameraViewModel.bindToCamera(
            context = context.applicationContext,
            lifecycleOwner = lifecycleOwner,
            isUseBackCamera = state.isBackCamera
        )
    }

    LaunchedEffect(state.isBackCamera) {
        if (state.cameraStatus == CameraStatusState.READY) {
            println("switching to other camera...")
            cameraViewModel.unbindCamera()
            cameraViewModel.bindToCamera(
                context.applicationContext,
                lifecycleOwner = lifecycleOwner,
                isUseBackCamera = state.isBackCamera
            )
        }
    }

    Box(
        modifier = modifier
            .clipToBounds()
    ) {
        AnimatedVisibility(
            visible = state.isExpanded && state.cameraStatus == CameraStatusState.READY,
            enter = fadeIn(appSpringDefault()),
            exit = fadeOut(appSpringDefault())
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = AppTheme.size.medium),
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
        if (state.cameraStatus == CameraStatusState.READY) {
            CameraPreview(
                modifier = Modifier
                    .fillMaxSize(),
                surfaceRequest = surfaceRequest
            )
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
                onTakePhotoButtonClicked = { onPhotoTaken.invoke("") },
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