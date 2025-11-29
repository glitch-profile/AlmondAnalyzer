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

public val AppIcons.Schedule: ImageVector
    get() {
        if (_shedule != null) {
            return _shedule!!
        }
        _shedule = Builder(name = "Shedule", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
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
                moveTo(19.0f, 7.0f)
                horizontalLineTo(5.0f)
                verticalLineTo(19.0f)
                horizontalLineTo(19.0f)
                verticalLineTo(7.0f)
                close()
                moveTo(14.824f, 9.379f)
                curveTo(15.013f, 9.197f, 15.265f, 9.096f, 15.527f, 9.098f)
                curveTo(15.79f, 9.101f, 16.04f, 9.206f, 16.226f, 9.391f)
                curveTo(16.411f, 9.577f, 16.516f, 9.827f, 16.519f, 10.09f)
                curveTo(16.521f, 10.352f, 16.42f, 10.604f, 16.238f, 10.793f)
                lineTo(11.296f, 15.736f)
                curveTo(11.202f, 15.83f, 11.091f, 15.904f, 10.968f, 15.955f)
                curveTo(10.846f, 16.006f, 10.714f, 16.032f, 10.582f, 16.032f)
                curveTo(10.449f, 16.032f, 10.317f, 16.006f, 10.195f, 15.955f)
                curveTo(10.072f, 15.904f, 9.961f, 15.83f, 9.867f, 15.736f)
                lineTo(7.753f, 13.62f)
                curveTo(7.571f, 13.431f, 7.47f, 13.179f, 7.472f, 12.917f)
                curveTo(7.475f, 12.654f, 7.58f, 12.404f, 7.765f, 12.218f)
                curveTo(7.951f, 12.033f, 8.201f, 11.928f, 8.464f, 11.925f)
                curveTo(8.726f, 11.923f, 8.978f, 12.024f, 9.167f, 12.206f)
                lineTo(10.582f, 13.62f)
                lineTo(14.824f, 9.379f)
                close()
            }
        }
        .build()
        return _shedule!!
    }

private var _shedule: ImageVector? = null
