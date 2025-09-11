package com.glitchdev.almondanalyzer.ui.icons.svgs

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.glitchdev.almondanalyzer.ui.icons.AppIcons

public val AppIcons.Upload: ImageVector
    get() {
        if (_upload != null) {
            return _upload!!
        }
        _upload = Builder(name = "Upload", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(20.0f, 15.0f)
                curveTo(20.265f, 15.0f, 20.52f, 15.105f, 20.707f, 15.293f)
                curveTo(20.895f, 15.48f, 21.0f, 15.735f, 21.0f, 16.0f)
                verticalLineTo(20.0f)
                curveTo(21.0f, 20.53f, 20.789f, 21.039f, 20.414f, 21.414f)
                curveTo(20.039f, 21.789f, 19.53f, 22.0f, 19.0f, 22.0f)
                horizontalLineTo(5.0f)
                curveTo(4.47f, 22.0f, 3.961f, 21.789f, 3.586f, 21.414f)
                curveTo(3.211f, 21.039f, 3.0f, 20.53f, 3.0f, 20.0f)
                verticalLineTo(16.0f)
                curveTo(3.0f, 15.735f, 3.105f, 15.48f, 3.293f, 15.293f)
                curveTo(3.48f, 15.105f, 3.735f, 15.0f, 4.0f, 15.0f)
                curveTo(4.265f, 15.0f, 4.52f, 15.105f, 4.707f, 15.293f)
                curveTo(4.895f, 15.48f, 5.0f, 15.735f, 5.0f, 16.0f)
                verticalLineTo(20.0f)
                horizontalLineTo(19.0f)
                verticalLineTo(16.0f)
                curveTo(19.0f, 15.735f, 19.105f, 15.48f, 19.293f, 15.293f)
                curveTo(19.48f, 15.105f, 19.735f, 15.0f, 20.0f, 15.0f)
                close()
                moveTo(12.884f, 3.474f)
                lineTo(16.95f, 7.54f)
                curveTo(17.045f, 7.632f, 17.122f, 7.743f, 17.174f, 7.865f)
                curveTo(17.226f, 7.987f, 17.254f, 8.118f, 17.255f, 8.251f)
                curveTo(17.256f, 8.383f, 17.231f, 8.515f, 17.181f, 8.638f)
                curveTo(17.131f, 8.761f, 17.056f, 8.873f, 16.962f, 8.966f)
                curveTo(16.868f, 9.06f, 16.757f, 9.135f, 16.634f, 9.185f)
                curveTo(16.511f, 9.235f, 16.379f, 9.26f, 16.247f, 9.259f)
                curveTo(16.114f, 9.258f, 15.983f, 9.231f, 15.861f, 9.178f)
                curveTo(15.739f, 9.126f, 15.628f, 9.05f, 15.536f, 8.954f)
                lineTo(13.0f, 6.42f)
                verticalLineTo(16.0f)
                curveTo(13.0f, 16.265f, 12.895f, 16.52f, 12.707f, 16.707f)
                curveTo(12.52f, 16.895f, 12.265f, 17.0f, 12.0f, 17.0f)
                curveTo(11.735f, 17.0f, 11.48f, 16.895f, 11.293f, 16.707f)
                curveTo(11.105f, 16.52f, 11.0f, 16.265f, 11.0f, 16.0f)
                verticalLineTo(6.419f)
                lineTo(8.464f, 8.954f)
                curveTo(8.275f, 9.136f, 8.023f, 9.237f, 7.761f, 9.235f)
                curveTo(7.498f, 9.232f, 7.248f, 9.127f, 7.062f, 8.942f)
                curveTo(6.877f, 8.756f, 6.772f, 8.506f, 6.769f, 8.243f)
                curveTo(6.767f, 7.981f, 6.868f, 7.729f, 7.05f, 7.54f)
                lineTo(11.116f, 3.474f)
                curveTo(11.232f, 3.358f, 11.37f, 3.266f, 11.522f, 3.203f)
                curveTo(11.673f, 3.14f, 11.836f, 3.108f, 12.0f, 3.108f)
                curveTo(12.164f, 3.108f, 12.327f, 3.14f, 12.478f, 3.203f)
                curveTo(12.63f, 3.266f, 12.768f, 3.358f, 12.884f, 3.474f)
                close()
            }
        }
        .build()
        return _upload!!
    }

private var _upload: ImageVector? = null
