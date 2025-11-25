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

public val AppIcons.Alert: ImageVector
    get() {
        if (_alert != null) {
            return _alert!!
        }
        _alert = Builder(name = "Alert", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(13.299f, 3.148f)
                lineTo(21.933f, 18.102f)
                curveTo(22.065f, 18.33f, 22.134f, 18.589f, 22.134f, 18.852f)
                curveTo(22.134f, 19.115f, 22.065f, 19.374f, 21.933f, 19.602f)
                curveTo(21.801f, 19.83f, 21.612f, 20.019f, 21.384f, 20.151f)
                curveTo(21.156f, 20.283f, 20.897f, 20.352f, 20.634f, 20.352f)
                horizontalLineTo(3.366f)
                curveTo(3.103f, 20.352f, 2.844f, 20.283f, 2.616f, 20.151f)
                curveTo(2.388f, 20.019f, 2.199f, 19.83f, 2.067f, 19.602f)
                curveTo(1.935f, 19.374f, 1.866f, 19.115f, 1.866f, 18.852f)
                curveTo(1.866f, 18.589f, 1.935f, 18.33f, 2.067f, 18.102f)
                lineTo(10.701f, 3.148f)
                curveTo(11.278f, 2.148f, 12.721f, 2.148f, 13.299f, 3.148f)
                close()
                moveTo(12.0f, 4.898f)
                lineTo(4.232f, 18.352f)
                horizontalLineTo(19.768f)
                lineTo(12.0f, 4.898f)
                close()
                moveTo(12.0f, 15.0f)
                curveTo(12.265f, 15.0f, 12.52f, 15.105f, 12.707f, 15.293f)
                curveTo(12.895f, 15.48f, 13.0f, 15.735f, 13.0f, 16.0f)
                curveTo(13.0f, 16.265f, 12.895f, 16.52f, 12.707f, 16.707f)
                curveTo(12.52f, 16.895f, 12.265f, 17.0f, 12.0f, 17.0f)
                curveTo(11.735f, 17.0f, 11.48f, 16.895f, 11.293f, 16.707f)
                curveTo(11.105f, 16.52f, 11.0f, 16.265f, 11.0f, 16.0f)
                curveTo(11.0f, 15.735f, 11.105f, 15.48f, 11.293f, 15.293f)
                curveTo(11.48f, 15.105f, 11.735f, 15.0f, 12.0f, 15.0f)
                close()
                moveTo(12.0f, 8.0f)
                curveTo(12.265f, 8.0f, 12.52f, 8.105f, 12.707f, 8.293f)
                curveTo(12.895f, 8.48f, 13.0f, 8.735f, 13.0f, 9.0f)
                verticalLineTo(13.0f)
                curveTo(13.0f, 13.265f, 12.895f, 13.52f, 12.707f, 13.707f)
                curveTo(12.52f, 13.895f, 12.265f, 14.0f, 12.0f, 14.0f)
                curveTo(11.735f, 14.0f, 11.48f, 13.895f, 11.293f, 13.707f)
                curveTo(11.105f, 13.52f, 11.0f, 13.265f, 11.0f, 13.0f)
                verticalLineTo(9.0f)
                curveTo(11.0f, 8.735f, 11.105f, 8.48f, 11.293f, 8.293f)
                curveTo(11.48f, 8.105f, 11.735f, 8.0f, 12.0f, 8.0f)
                close()
            }
        }
        .build()
        return _alert!!
    }

private var _alert: ImageVector? = null
