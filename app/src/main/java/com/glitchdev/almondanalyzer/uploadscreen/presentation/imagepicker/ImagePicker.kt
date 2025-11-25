package com.glitchdev.almondanalyzer.uploadscreen.presentation.imagepicker

import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.glitchdev.almondanalyzer.R
import com.glitchdev.almondanalyzer.ui.components.AppButton
import com.glitchdev.almondanalyzer.ui.components.AppButtonDefaults
import com.glitchdev.almondanalyzer.ui.components.AppTextButton
import com.glitchdev.almondanalyzer.ui.components.ImageGallery
import com.glitchdev.almondanalyzer.ui.icons.AppIcons
import com.glitchdev.almondanalyzer.ui.icons.svgs.Alert
import com.glitchdev.almondanalyzer.ui.theme.AppTheme
import com.glitchdev.almondanalyzer.ui.theme.appSpringDefault

@Composable
fun ImagePicker(
    modifier: Modifier = Modifier,
    state: ImagePickerState,
    contentPadding: PaddingValues = PaddingValues.Zero,
    onHideTempOnlyWarning: () -> Unit,
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
                    text = stringResource(R.string.upload_image_gallery_component_no_permissions_title),
                    textAlign = TextAlign.Center,
                    style = AppTheme.typography.titleSmall,
                    color = AppTheme.colorScheme.onSurface
                )
                Text(
                    text = stringResource(R.string.upload_image_gallery_component_no_permissions_text),
                    textAlign = TextAlign.Center,
                    style = AppTheme.typography.bodyMedium,
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
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .padding(AppTheme.size.medium),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.upload_image_gallery_component_no_images_title),
                    textAlign = TextAlign.Center,
                    style = AppTheme.typography.titleSmall,
                    color = AppTheme.colorScheme.onSurface
                )
                Text(
                    text = stringResource(R.string.upload_image_gallery_component_no_images_text),
                    textAlign = TextAlign.Center,
                    style = AppTheme.typography.bodyMedium,
                    color = AppTheme.colorScheme.onSurface
                )
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                AnimatedVisibility(
                    visible = !state.hasPermissions && state.isShowTempOnlyWarning,
                    enter = expandVertically(appSpringDefault()),
                    exit = shrinkVertically(appSpringDefault())
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = AppTheme.size.medium)
                            .padding(bottom = AppTheme.size.medium)
                            .background(
                                color = AppTheme.colorScheme.background,
                                shape = AppTheme.shape.small
                            )
                            .padding(
                                horizontal = AppTheme.size.medium,
                                vertical = AppTheme.size.small
                            ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = AppIcons.Alert,
                            contentDescription = null,
                            tint = AppTheme.colorScheme.onSurface
                        )
                        Spacer(Modifier.width(AppTheme.size.large))
                        Column {
                            Text(
                                text = stringResource(R.string.upload_image_gallery_component_temp_only_warning_title),
                                style = AppTheme.typography.titleSmall,
                                color = AppTheme.colorScheme.onSurface
                            )
                            Text(
                                text = stringResource(R.string.upload_image_gallery_component_temp_only_warning_text),
                                style = AppTheme.typography.bodyMedium,
                                color = AppTheme.colorScheme.onSurface
                            )
                            Spacer(Modifier.height(AppTheme.size.extraSmall))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                AppTextButton(
                                    modifier = Modifier
                                        .height(AppButtonDefaults.minCompactButtonHeight),
                                    contentPadding = AppButtonDefaults.textButtonCompactPadding,
                                    onClick = onHideTempOnlyWarning,
                                    colors = AppButtonDefaults.textButtonColors(
                                        contentColor = AppTheme.colorScheme.notation
                                    )
                                ) {
                                    Text(text = stringResource(R.string.upload_image_gallery_component_temp_only_warning_hide_label))
                                }
                                Spacer(Modifier.width(AppTheme.size.medium))
                                AppTextButton(
                                    modifier = Modifier
                                        .height(AppButtonDefaults.minCompactButtonHeight),
                                    contentPadding = AppButtonDefaults.textButtonCompactPadding,
                                    onClick = onRequestPermissions
                                ) {
                                    Text(text = stringResource(R.string.upload_image_gallery_component_temp_only_warning_fix_label))
                                }
                            }
                        }
                    }
                }
                Column {
                    ImageGallery(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = contentPadding,
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
}