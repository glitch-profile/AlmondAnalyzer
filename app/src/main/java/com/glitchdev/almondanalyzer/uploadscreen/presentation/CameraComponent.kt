package com.glitchdev.almondanalyzer.uploadscreen.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.glitchdev.almondanalyzer.R
import com.glitchdev.almondanalyzer.ui.components.camerapreview.CameraPreview
import com.glitchdev.almondanalyzer.ui.components.camerapreview.CameraPreviewViewModel
import com.glitchdev.almondanalyzer.ui.icons.AppIcons
import com.glitchdev.almondanalyzer.ui.icons.svgs.Camera
import com.glitchdev.almondanalyzer.ui.icons.svgs.CameraUnavailable
import com.glitchdev.almondanalyzer.ui.theme.AppTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun CameraComponent(
    modifier: Modifier = Modifier,
    state: CameraComponentState,
    onNoPermissionClicked: () -> Unit,
    onExpandClicked: () -> Unit,
    onCollapseClicked: () -> Unit,
    onPhotoTaken: (photoUri: String) -> Unit
) {

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val cameraViewModel: CameraPreviewViewModel = koinViewModel()
    val surfaceRequest by cameraViewModel.surfaceRequests.collectAsState()

    val cameraTintAlpha by animateFloatAsState(
        targetValue = when (state) {
            CameraComponentState.NO_PERMISSIONS -> 1f
            CameraComponentState.INITIALIZING -> 1f
            CameraComponentState.COMPACT -> 0.8f
            CameraComponentState.EXPANDED -> 0f
        },
        animationSpec = spring(stiffness = Spring.StiffnessMedium)
    )

    LaunchedEffect(lifecycleOwner) {
        cameraViewModel.bindToCamera(
            context = context.applicationContext,
            lifecycleOwner = lifecycleOwner
        )
    }

    Box(
        modifier = modifier
            .clipToBounds()
    ) {
        if (state != CameraComponentState.NO_PERMISSIONS && state != CameraComponentState.INITIALIZING) {
            CameraPreview(
                modifier = Modifier
                    .fillMaxSize(),
                surfaceRequest = surfaceRequest
            )
        }

        if (cameraTintAlpha != 0f) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(cameraTintAlpha)
                    .background(AppTheme.colorScheme.surface)
                    .clickable(
                        enabled = state != CameraComponentState.INITIALIZING,
                        interactionSource = null,
                        onClick = {
                            when (state) {
                                CameraComponentState.NO_PERMISSIONS -> onNoPermissionClicked.invoke()
                                CameraComponentState.COMPACT -> onExpandClicked.invoke()
                                CameraComponentState.EXPANDED -> onCollapseClicked.invoke()
                                else -> Unit
                            }
                        }
                    )
            ) {
                Crossfade(
                    targetState = state != CameraComponentState.INITIALIZING,
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
                                targetState = state == CameraComponentState.NO_PERMISSIONS,
                                animationSpec = spring(stiffness = Spring.StiffnessMedium)
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
                                    .animateContentSize(animationSpec = spring(stiffness = Spring.StiffnessMedium))
                            ) {
                                AnimatedContent(
                                    targetState = state == CameraComponentState.NO_PERMISSIONS,
                                    transitionSpec = {
                                        (fadeIn(spring(stiffness = Spring.StiffnessMedium))
                                                togetherWith fadeOut(spring(stiffness = Spring.StiffnessMedium)))
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
                                    targetState = state == CameraComponentState.NO_PERMISSIONS,
                                    transitionSpec = {
                                        (fadeIn(spring(stiffness = Spring.StiffnessMedium))
                                                togetherWith fadeOut(spring(stiffness = Spring.StiffnessMedium)))
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