package com.glitchdev.almondanalyzer.ui.icons.svgs

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.EvenOdd
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.glitchdev.almondanalyzer.ui.icons.AppIcons

public val AppIcons.Calendar: ImageVector
    get() {
        if (_calendar != null) {
            return _calendar!!
        }
        _calendar = Builder(name = "Calendar", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd) {
                moveTo(16.0f, 3.0f)
                curveTo(16.265f, 3.0f, 16.52f, 3.105f, 16.707f, 3.293f)
                curveTo(16.895f, 3.48f, 17.0f, 3.735f, 17.0f, 4.0f)
                verticalLineTo(5.0f)
                horizontalLineTo(19.0f)
                curveTo(19.53f, 5.0f, 20.039f, 5.211f, 20.414f, 5.586f)
                curveTo(20.789f, 5.961f, 21.0f, 6.47f, 21.0f, 7.0f)
                verticalLineTo(19.0f)
                curveTo(21.0f, 19.53f, 20.789f, 20.039f, 20.414f, 20.414f)
                curveTo(20.039f, 20.789f, 19.53f, 21.0f, 19.0f, 21.0f)
                horizontalLineTo(5.0f)
                curveTo(4.47f, 21.0f, 3.961f, 20.789f, 3.586f, 20.414f)
                curveTo(3.211f, 20.039f, 3.0f, 19.53f, 3.0f, 19.0f)
                verticalLineTo(7.0f)
                curveTo(3.0f, 6.47f, 3.211f, 5.961f, 3.586f, 5.586f)
                curveTo(3.961f, 5.211f, 4.47f, 5.0f, 5.0f, 5.0f)
                horizontalLineTo(7.0f)
                verticalLineTo(4.0f)
                curveTo(7.0f, 3.735f, 7.105f, 3.48f, 7.293f, 3.293f)
                curveTo(7.48f, 3.105f, 7.735f, 3.0f, 8.0f, 3.0f)
                curveTo(8.265f, 3.0f, 8.52f, 3.105f, 8.707f, 3.293f)
                curveTo(8.895f, 3.48f, 9.0f, 3.735f, 9.0f, 4.0f)
                verticalLineTo(5.0f)
                horizontalLineTo(15.0f)
                verticalLineTo(4.0f)
                curveTo(15.0f, 3.735f, 15.105f, 3.48f, 15.293f, 3.293f)
                curveTo(15.48f, 3.105f, 15.735f, 3.0f, 16.0f, 3.0f)
                close()
                moveTo(8.0f, 7.0f)
                horizontalLineTo(5.0f)
                verticalLineTo(9.0f)
                horizontalLineTo(19.0f)
                verticalLineTo(7.0f)
                horizontalLineTo(16.0f)
                horizontalLineTo(8.0f)
                close()
                moveTo(5.0f, 11.0f)
                verticalLineTo(19.0f)
                horizontalLineTo(19.0f)
                verticalLineTo(11.0f)
                horizontalLineTo(5.0f)
                close()
                moveTo(7.0f, 13.0f)
                curveTo(7.0f, 12.735f, 7.105f, 12.48f, 7.293f, 12.293f)
                curveTo(7.48f, 12.105f, 7.735f, 12.0f, 8.0f, 12.0f)
                horizontalLineTo(8.01f)
                curveTo(8.275f, 12.0f, 8.53f, 12.105f, 8.717f, 12.293f)
                curveTo(8.905f, 12.48f, 9.01f, 12.735f, 9.01f, 13.0f)
                curveTo(9.01f, 13.265f, 8.905f, 13.52f, 8.717f, 13.707f)
                curveTo(8.53f, 13.895f, 8.275f, 14.0f, 8.01f, 14.0f)
                horizontalLineTo(8.0f)
                curveTo(7.735f, 14.0f, 7.48f, 13.895f, 7.293f, 13.707f)
                curveTo(7.105f, 13.52f, 7.0f, 13.265f, 7.0f, 13.0f)
                close()
                moveTo(8.0f, 15.0f)
                curveTo(7.735f, 15.0f, 7.48f, 15.105f, 7.293f, 15.293f)
                curveTo(7.105f, 15.48f, 7.0f, 15.735f, 7.0f, 16.0f)
                curveTo(7.0f, 16.265f, 7.105f, 16.52f, 7.293f, 16.707f)
                curveTo(7.48f, 16.895f, 7.735f, 17.0f, 8.0f, 17.0f)
                horizontalLineTo(8.01f)
                curveTo(8.275f, 17.0f, 8.53f, 16.895f, 8.717f, 16.707f)
                curveTo(8.905f, 16.52f, 9.01f, 16.265f, 9.01f, 16.0f)
                curveTo(9.01f, 15.735f, 8.905f, 15.48f, 8.717f, 15.293f)
                curveTo(8.53f, 15.105f, 8.275f, 15.0f, 8.01f, 15.0f)
                horizontalLineTo(8.0f)
                close()
                moveTo(11.0f, 13.0f)
                curveTo(11.0f, 12.735f, 11.105f, 12.48f, 11.293f, 12.293f)
                curveTo(11.48f, 12.105f, 11.735f, 12.0f, 12.0f, 12.0f)
                horizontalLineTo(12.01f)
                curveTo(12.275f, 12.0f, 12.53f, 12.105f, 12.717f, 12.293f)
                curveTo(12.905f, 12.48f, 13.01f, 12.735f, 13.01f, 13.0f)
                curveTo(13.01f, 13.265f, 12.905f, 13.52f, 12.717f, 13.707f)
                curveTo(12.53f, 13.895f, 12.275f, 14.0f, 12.01f, 14.0f)
                horizontalLineTo(12.0f)
                curveTo(11.735f, 14.0f, 11.48f, 13.895f, 11.293f, 13.707f)
                curveTo(11.105f, 13.52f, 11.0f, 13.265f, 11.0f, 13.0f)
                close()
                moveTo(12.0f, 15.0f)
                curveTo(11.735f, 15.0f, 11.48f, 15.105f, 11.293f, 15.293f)
                curveTo(11.105f, 15.48f, 11.0f, 15.735f, 11.0f, 16.0f)
                curveTo(11.0f, 16.265f, 11.105f, 16.52f, 11.293f, 16.707f)
                curveTo(11.48f, 16.895f, 11.735f, 17.0f, 12.0f, 17.0f)
                horizontalLineTo(12.01f)
                curveTo(12.275f, 17.0f, 12.53f, 16.895f, 12.717f, 16.707f)
                curveTo(12.905f, 16.52f, 13.01f, 16.265f, 13.01f, 16.0f)
                curveTo(13.01f, 15.735f, 12.905f, 15.48f, 12.717f, 15.293f)
                curveTo(12.53f, 15.105f, 12.275f, 15.0f, 12.01f, 15.0f)
                horizontalLineTo(12.0f)
                close()
                moveTo(15.0f, 13.0f)
                curveTo(15.0f, 12.735f, 15.105f, 12.48f, 15.293f, 12.293f)
                curveTo(15.48f, 12.105f, 15.735f, 12.0f, 16.0f, 12.0f)
                horizontalLineTo(16.01f)
                curveTo(16.275f, 12.0f, 16.53f, 12.105f, 16.717f, 12.293f)
                curveTo(16.905f, 12.48f, 17.01f, 12.735f, 17.01f, 13.0f)
                curveTo(17.01f, 13.265f, 16.905f, 13.52f, 16.717f, 13.707f)
                curveTo(16.53f, 13.895f, 16.275f, 14.0f, 16.01f, 14.0f)
                horizontalLineTo(16.0f)
                curveTo(15.735f, 14.0f, 15.48f, 13.895f, 15.293f, 13.707f)
                curveTo(15.105f, 13.52f, 15.0f, 13.265f, 15.0f, 13.0f)
                close()
                moveTo(16.0f, 15.0f)
                curveTo(15.735f, 15.0f, 15.48f, 15.105f, 15.293f, 15.293f)
                curveTo(15.105f, 15.48f, 15.0f, 15.735f, 15.0f, 16.0f)
                curveTo(15.0f, 16.265f, 15.105f, 16.52f, 15.293f, 16.707f)
                curveTo(15.48f, 16.895f, 15.735f, 17.0f, 16.0f, 17.0f)
                horizontalLineTo(16.01f)
                curveTo(16.275f, 17.0f, 16.53f, 16.895f, 16.717f, 16.707f)
                curveTo(16.905f, 16.52f, 17.01f, 16.265f, 17.01f, 16.0f)
                curveTo(17.01f, 15.735f, 16.905f, 15.48f, 16.717f, 15.293f)
                curveTo(16.53f, 15.105f, 16.275f, 15.0f, 16.01f, 15.0f)
                horizontalLineTo(16.0f)
                close()
            }
        }
        .build()
        return _calendar!!
    }

private var _calendar: ImageVector? = null
