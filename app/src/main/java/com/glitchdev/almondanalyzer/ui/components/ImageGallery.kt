package com.glitchdev.almondanalyzer.ui.components

import android.net.Uri
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.glitchdev.almondanalyzer.ui.theme.AppTheme

@Composable
fun ImageGallery(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues =PaddingValues.Zero,
    lazyGridState: LazyGridState? = null,
    imageUris: List<Uri>,
    selectedImagesUri: List<Uri>,
    onClickOnImage: (image: Uri) -> Unit,
    onHoldOnImage: (image: Uri) -> Unit,
    enableClickEvents: Boolean = true,
    maxColumns: Int = 3
) {
    LazyVerticalGrid(
        modifier = modifier,
        contentPadding = contentPadding,
        state = lazyGridState ?: rememberLazyGridState(),
        columns = GridCells.Fixed(maxColumns)
    ) {
        items(
            items = imageUris,
            key = { it }
        ) { imageUri ->
            val isSelected = remember(selectedImagesUri) {
                selectedImagesUri.contains(imageUri)
            }
            val transition = updateTransition(isSelected)
            val padding by transition.animateDp(transitionSpec = { spring(stiffness = Spring.StiffnessHigh) }) { state ->
                if (state) 8.dp else 1.dp
            }
            val shapeCurvatureDp by transition.animateDp(transitionSpec = { spring(stiffness = Spring.StiffnessHigh) }) { state ->
                if (state) 12.dp else 0.dp
            }
            val borderWidth by transition.animateDp(transitionSpec = { spring(stiffness = Spring.StiffnessHigh) }) { state ->
                if (state) 4.dp else 0.dp
            }
            AsyncImage(
                modifier = Modifier
                    .combinedClickable(
                        enabled = enableClickEvents,
                        onClick = { onClickOnImage.invoke(imageUri) },
                        onLongClick = { onHoldOnImage.invoke(imageUri) }
                    )
                    .aspectRatio(1f)
                    .padding(padding)
                    .clip(RoundedCornerShape(shapeCurvatureDp))
                    .run {
                        if (shapeCurvatureDp.value > 0.1f) {
                            this.border(
                                width = borderWidth,
                                color = AppTheme.colorScheme.primary,
                                shape = RoundedCornerShape(shapeCurvatureDp)
                            )
                        } else {this}
                    },
                model = imageUri,
                contentScale = ContentScale.Crop,
                filterQuality = FilterQuality.Low,
                contentDescription = null
            )
        }
    }
}