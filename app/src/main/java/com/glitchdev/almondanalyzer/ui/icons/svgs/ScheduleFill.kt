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

public val AppIcons.ScheduleFill: ImageVector
    get() {
        if (_schedulefill != null) {
            return _schedulefill!!
        }
        _schedulefill = Builder(name = "Schedulefill", defaultWidth = 24.0.dp, defaultHeight =
                24.0.dp, viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(16.0f, 3.0f)
                curveTo(16.265f, 3.0f, 16.52f, 3.105f, 16.707f, 3.293f)
                curveTo(16.895f, 3.48f, 17.0f, 3.735f, 17.0f, 4.0f)
                verticalLineTo(5.0f)
                horizontalLineTo(19.0f)
                curveTo(19.505f, 5.0f, 19.991f, 5.19f, 20.361f, 5.534f)
                curveTo(20.73f, 5.877f, 20.957f, 6.347f, 20.995f, 6.85f)
                lineTo(21.0f, 7.0f)
                verticalLineTo(19.0f)
                curveTo(21.0f, 19.505f, 20.81f, 19.991f, 20.466f, 20.361f)
                curveTo(20.123f, 20.73f, 19.653f, 20.957f, 19.15f, 20.995f)
                lineTo(19.0f, 21.0f)
                horizontalLineTo(5.0f)
                curveTo(4.495f, 21.0f, 4.009f, 20.81f, 3.639f, 20.466f)
                curveTo(3.269f, 20.123f, 3.043f, 19.653f, 3.005f, 19.15f)
                lineTo(3.0f, 19.0f)
                verticalLineTo(7.0f)
                curveTo(3.0f, 6.495f, 3.19f, 6.009f, 3.533f, 5.639f)
                curveTo(3.877f, 5.269f, 4.347f, 5.043f, 4.85f, 5.005f)
                lineTo(5.0f, 5.0f)
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
                moveTo(14.824f, 9.379f)
                lineTo(10.582f, 13.621f)
                lineTo(9.167f, 12.207f)
                curveTo(8.978f, 12.025f, 8.726f, 11.924f, 8.464f, 11.926f)
                curveTo(8.201f, 11.929f, 7.951f, 12.034f, 7.765f, 12.219f)
                curveTo(7.58f, 12.405f, 7.475f, 12.655f, 7.472f, 12.918f)
                curveTo(7.47f, 13.18f, 7.571f, 13.432f, 7.753f, 13.621f)
                lineTo(9.867f, 15.736f)
                curveTo(9.961f, 15.83f, 10.072f, 15.904f, 10.195f, 15.955f)
                curveTo(10.317f, 16.006f, 10.449f, 16.032f, 10.582f, 16.032f)
                curveTo(10.714f, 16.032f, 10.846f, 16.006f, 10.968f, 15.955f)
                curveTo(11.091f, 15.904f, 11.202f, 15.83f, 11.296f, 15.736f)
                lineTo(16.238f, 10.793f)
                curveTo(16.333f, 10.701f, 16.41f, 10.59f, 16.462f, 10.468f)
                curveTo(16.514f, 10.346f, 16.542f, 10.215f, 16.543f, 10.082f)
                curveTo(16.544f, 9.95f, 16.519f, 9.818f, 16.469f, 9.695f)
                curveTo(16.419f, 9.572f, 16.344f, 9.46f, 16.25f, 9.367f)
                curveTo(16.156f, 9.273f, 16.045f, 9.198f, 15.922f, 9.148f)
                curveTo(15.799f, 9.098f, 15.667f, 9.073f, 15.535f, 9.074f)
                curveTo(15.402f, 9.075f, 15.271f, 9.102f, 15.149f, 9.155f)
                curveTo(15.027f, 9.207f, 14.916f, 9.283f, 14.824f, 9.379f)
                close()
            }
        }
        .build()
        return _schedulefill!!
    }

private var _schedulefill: ImageVector? = null
