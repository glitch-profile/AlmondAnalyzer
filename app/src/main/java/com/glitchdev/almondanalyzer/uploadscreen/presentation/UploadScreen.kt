package com.glitchdev.almondanalyzer.uploadscreen.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.glitchdev.almondanalyzer.R
import com.glitchdev.almondanalyzer.ui.components.AppButton
import com.glitchdev.almondanalyzer.ui.components.AppButtonDefaults
import com.glitchdev.almondanalyzer.ui.components.AppIconButton
import com.glitchdev.almondanalyzer.ui.components.AppTextDivider
import com.glitchdev.almondanalyzer.ui.icons.AppIcons
import com.glitchdev.almondanalyzer.ui.icons.svgs.CloseSquare
import com.glitchdev.almondanalyzer.ui.theme.AppTheme
import com.glitchdev.almondanalyzer.ui.theme.appSpringDefault
import com.glitchdev.almondanalyzer.uploadscreen.presentation.cameracomponent.CameraComponent
import com.glitchdev.almondanalyzer.uploadscreen.presentation.cameracomponent.CameraComponentState
import com.glitchdev.almondanalyzer.uploadscreen.presentation.imagepicker.ImagePicker
import com.glitchdev.almondanalyzer.uploadscreen.presentation.imagepicker.ImagePickerState

@Composable
fun UploadScreen(
    cameraState: CameraComponentState,
    imagePickerState: ImagePickerState,
    onUpdateCameraPermissions: (isPermissionGranted: Boolean) -> Unit,
    onUpdateCameraFullscreenMode: (isExpanded: Boolean) -> Unit,
    onUpdateCameraStreamStatus: (isAvailable: Boolean) -> Unit,
    onSwitchSelectedCamera: (isBackCamera: Boolean) -> Unit,
    onPhotoTaken: (imageUri: Uri) -> Unit,
    onOpenImagePreview: (imagePreview: Uri) -> Unit,
    onUpdateImagePickerPermissions: (isPermissionsGranted: Boolean) -> Unit,
    onHideTempOnlyWarning: () -> Unit,
    onUpdateImagesList: () -> Unit,
    onSelectImage: (imageUri: Uri) -> Unit,
    onUnselectImage: (imageUri: Uri) -> Unit,
    onClearImageSelection: () -> Unit,
    onUploadImages: () -> Unit
) {
    val context = LocalContext.current
    val density = LocalDensity.current

    val cameraPermission = Manifest.permission.CAMERA
    val imagePickerPermission = remember {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) Manifest.permission.READ_MEDIA_IMAGES
        else Manifest.permission.READ_EXTERNAL_STORAGE
    }

    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { onUpdateCameraPermissions.invoke(it) }
    )

    val imagePickerPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = {
            onUpdateImagePickerPermissions.invoke(it)
        }
    )

    var maxScreenHeight by remember { mutableStateOf(Dp.Unspecified) }

    LaunchedEffect(null) {
        val isHasCameraAccess = ContextCompat.checkSelfPermission(context, cameraPermission) ==
                PackageManager.PERMISSION_GRANTED
        onUpdateCameraPermissions.invoke(isHasCameraAccess)
        val isHasImagesAccess = ContextCompat.checkSelfPermission(context, imagePickerPermission) ==
                PackageManager.PERMISSION_GRANTED
        onUpdateImagePickerPermissions.invoke(isHasImagesAccess)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .onSizeChanged { newSize ->
                maxScreenHeight = with(density) { newSize.height.toDp() }
            }
    ) {
        val cameraWindowHeight by animateDpAsState(
            targetValue = if (cameraState.isExpanded) maxScreenHeight else 200.dp,
            animationSpec = appSpringDefault()
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = cameraWindowHeight)
        ) {
            CameraComponent(
                modifier = Modifier
                    .fillMaxSize(),
                state = cameraState,
                onNoPermissionClicked = { cameraPermissionLauncher.launch(cameraPermission) },
                onExpandButtonClicked = { onUpdateCameraFullscreenMode.invoke(true) },
                onCollapseButtonClicked = { onUpdateCameraFullscreenMode.invoke(false) },
                onSwitchCameraButtonClicked = { onSwitchSelectedCamera.invoke(!cameraState.isBackCamera) },
                onUpdateCameraStreamStatus = onUpdateCameraStreamStatus,
                onPhotoTaken = onPhotoTaken,
            )
        }
        if (cameraWindowHeight != maxScreenHeight) {
            var bottomMenuHeight by remember { mutableStateOf(0.dp) }
            val density = LocalDensity.current
            AppTextDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = AppTheme.size.medium,
                        vertical = AppTheme.size.small
                    ),
                text = stringResource(R.string.upload_image_import_divider_text)
            )
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                ImagePicker(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentPadding = PaddingValues(bottom = bottomMenuHeight),
                    state = imagePickerState,
                    onHideTempOnlyWarning = onHideTempOnlyWarning,
                    onRefreshImagesList = onUpdateImagesList,
                    onRequestPermissions = { imagePickerPermissionLauncher.launch(imagePickerPermission) },
                    onOpenImage = onOpenImagePreview,
                    onSelectImage = onSelectImage,
                    onUnselectImage = onUnselectImage,
                    onClearSelection = { onClearImageSelection }
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .onSizeChanged { newSize ->
                            bottomMenuHeight = with(density) { newSize.height.toDp() }
                        }
                ) {
                    UploadButtonsMenu(
                        selectedImages = imagePickerState.selectedImages,
                        onClearImageSelection = onClearImageSelection,
                        onUploadImages = onUploadImages
                    )
                }
            }
        }
    }
}

@Composable
private fun UploadButtonsMenu(
    selectedImages: List<Uri>,
    onClearImageSelection: () -> Unit,
    onUploadImages: () -> Unit
) {
    var maxHiddenOffset by remember { mutableFloatStateOf(300f) }
    val offset by animateFloatAsState(
        if (selectedImages.isNotEmpty()) 0f else maxHiddenOffset,
        animationSpec = appSpringDefault()
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .onSizeChanged { size ->
                maxHiddenOffset = size.height.toFloat()
            }
            .graphicsLayer {
                translationY = offset
            }
            .padding(AppTheme.size.medium)
    ) {
        AppIconButton(
            modifier = Modifier
                .size(48.dp),
            onClick = onClearImageSelection,
            shape = AppButtonDefaults.shape(),
            colors = AppButtonDefaults.textButtonColors(
                containerColor = AppTheme.colorScheme.surface.copy(0.8f)
            )
        ) {
            Icon(
                imageVector = AppIcons.CloseSquare,
                contentDescription = null
            )
        }
        Spacer(Modifier.width(AppTheme.size.medium))
        AppButton(
            modifier = Modifier
                .height(48.dp)
                .weight(1f),
            enabled = selectedImages.isNotEmpty(),
            onClick = onUploadImages,
            shape = AppButtonDefaults.shape()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(stringResource(R.string.upload_image_import_upload_images_label))
                Text(
                    text = pluralStringResource(
                        R.plurals.upload_image_import_upload_images_sublabel,
                        count = selectedImages.size,
                        selectedImages.size
                    ),
                    style = AppTheme.typography.bodyMedium,
                    color = AppTheme.colorScheme.onPrimaryVariant
                )
            }
        }
    }
}