package com.glitchdev.almondanalyzer.ui.icons.svgs

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.glitchdev.almondanalyzer.ui.icons.AppIcons

public val AppIcons.Back: ImageVector
    get() {
        if (_back != null) {
            return _back!!
        }
        _back = Builder(name = "Back", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            group {
                path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveTo(3.636f, 11.293f)
                    curveTo(3.449f, 11.481f, 3.343f, 11.735f, 3.343f, 12.0f)
                    curveTo(3.343f, 12.265f, 3.449f, 12.519f, 3.636f, 12.707f)
                    lineTo(9.293f, 18.364f)
                    curveTo(9.482f, 18.546f, 9.734f, 18.647f, 9.996f, 18.645f)
                    curveTo(10.259f, 18.642f, 10.509f, 18.537f, 10.695f, 18.352f)
                    curveTo(10.88f, 18.166f, 10.985f, 17.916f, 10.988f, 17.653f)
                    curveTo(10.99f, 17.391f, 10.889f, 17.139f, 10.707f, 16.95f)
                    lineTo(6.757f, 13.0f)
                    horizontalLineTo(20.0f)
                    curveTo(20.265f, 13.0f, 20.52f, 12.895f, 20.707f, 12.707f)
                    curveTo(20.895f, 12.52f, 21.0f, 12.265f, 21.0f, 12.0f)
                    curveTo(21.0f, 11.735f, 20.895f, 11.48f, 20.707f, 11.293f)
                    curveTo(20.52f, 11.105f, 20.265f, 11.0f, 20.0f, 11.0f)
                    horizontalLineTo(6.757f)
                    lineTo(10.707f, 7.05f)
                    curveTo(10.889f, 6.861f, 10.99f, 6.609f, 10.988f, 6.347f)
                    curveTo(10.985f, 6.084f, 10.88f, 5.834f, 10.695f, 5.648f)
                    curveTo(10.509f, 5.463f, 10.259f, 5.358f, 9.996f, 5.355f)
                    curveTo(9.734f, 5.353f, 9.482f, 5.454f, 9.293f, 5.636f)
                    lineTo(3.636f, 11.293f)
                    close()
                }
            }
        }
        .build()
        return _back!!
    }

private var _back: ImageVector? = null
