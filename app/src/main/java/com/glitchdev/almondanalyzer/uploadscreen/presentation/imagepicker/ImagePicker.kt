package com.glitchdev.almondanalyzer.uploadscreen.presentation.imagepicker

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.glitchdev.almondanalyzer.R
import com.glitchdev.almondanalyzer.ui.components.AppButton
import com.glitchdev.almondanalyzer.ui.components.AppTextButton
import com.glitchdev.almondanalyzer.ui.components.ImageGallery
import com.glitchdev.almondanalyzer.ui.theme.AppTheme

@Composable
fun ImagePicker(
    modifier: Modifier = Modifier,
    state: ImagePickerState,
    onRefreshImagesList: () -> Unit,
    onRequestPermissions: () -> Unit,
    onOpenImage: (imageUri: Uri) -> Unit,
    onSelectImage: (imageUri: Uri) -> Unit,
    onUnselectImage: (imageUri: Uri) -> Unit,
    onClearSelection: () -> Unit
) {
    Box(modifier = modifier) {
        if (!state.hasPermissions && state.images.isEmpty()) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
                    .padding(AppTheme.size.medium),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.upload_image_gallery_component_no_permissions_text),
                    textAlign = TextAlign.Center,
                    style = AppTheme.typography.titleMedium,
                    color = AppTheme.colorScheme.onSurface
                )
                Spacer(Modifier.height(AppTheme.size.small))
                AppButton(
                    onClick = onRequestPermissions
                ) {
                    Text(
                        text = stringResource(R.string.upload_image_gallery_component_no_permissions_label)
                    )
                }
            }
        } else if (state.images.isEmpty()) {
            Text(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(AppTheme.size.medium),
                text = stringResource(R.string.upload_image_gallery_component_no_images_text),
                textAlign = TextAlign.Center,
                style = AppTheme.typography.titleMedium,
                color = AppTheme.colorScheme.onSurface
            )
        } else {
            var showTempOnlyWarning by rememberSaveable { mutableStateOf(true) }
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                if (!state.hasPermissions && showTempOnlyWarning) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(AppTheme.size.medium),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(R.string.upload_image_gallery_component_temp_only_warning_text),
                            textAlign = TextAlign.Center,
                            style = AppTheme.typography.titleMedium,
                            color = AppTheme.colorScheme.onSurface
                        )
                        Spacer(Modifier.height(AppTheme.size.small))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            AppTextButton(onClick = {showTempOnlyWarning = false}) {
                                Text(text = stringResource(R.string.upload_image_gallery_component_temp_only_warning_hide_label))
                            }
                            Spacer(Modifier.width(AppTheme.size.medium))
                            AppButton(onClick = onRequestPermissions) {
                                Text(text = stringResource(R.string.upload_image_gallery_component_temp_only_warning_fix_label))
                            }
                        }
                    }
                }
                ImageGallery(
                    modifier = Modifier.fillMaxSize(),
                    imageUris = state.images,
                    selectedImagesUri = state.selectedImages,
                    onClickOnImage = { imageUri ->
                        if (!state.selectedImages.contains(imageUri)) onSelectImage.invoke(imageUri)
                        else onUnselectImage.invoke(imageUri)
                    },
                    onHoldOnImage = { imageUri ->
                        onOpenImage.invoke(imageUri)
                    }
                )
            }
        }
    }
}