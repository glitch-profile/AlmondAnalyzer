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

public val AppIcons.Name: ImageVector
    get() {
        if (_name != null) {
            return _name!!
        }
        _name = Builder(name = "Name", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd) {
                moveTo(21.0f, 12.0f)
                curveTo(21.265f, 12.0f, 21.52f, 12.105f, 21.707f, 12.293f)
                curveTo(21.895f, 12.48f, 22.0f, 12.735f, 22.0f, 13.0f)
                verticalLineTo(19.0f)
                curveTo(22.0f, 19.229f, 21.921f, 19.45f, 21.778f, 19.628f)
                curveTo(21.634f, 19.806f, 21.434f, 19.929f, 21.211f, 19.977f)
                curveTo(20.987f, 20.025f, 20.754f, 19.996f, 20.55f, 19.893f)
                curveTo(20.346f, 19.79f, 20.183f, 19.62f, 20.089f, 19.412f)
                curveTo(19.483f, 19.783f, 18.788f, 19.986f, 18.078f, 19.999f)
                curveTo(17.367f, 20.013f, 16.666f, 19.837f, 16.046f, 19.489f)
                curveTo(15.426f, 19.142f, 14.909f, 18.635f, 14.55f, 18.022f)
                curveTo(14.191f, 17.409f, 14.001f, 16.711f, 14.001f, 16.0f)
                curveTo(14.001f, 15.289f, 14.191f, 14.591f, 14.55f, 13.978f)
                curveTo(14.909f, 13.365f, 15.426f, 12.858f, 16.046f, 12.511f)
                curveTo(16.666f, 12.163f, 17.367f, 11.987f, 18.078f, 12.001f)
                curveTo(18.788f, 12.014f, 19.483f, 12.217f, 20.089f, 12.588f)
                curveTo(20.168f, 12.413f, 20.296f, 12.264f, 20.458f, 12.16f)
                curveTo(20.619f, 12.056f, 20.808f, 12.0f, 21.0f, 12.0f)
                close()
                moveTo(8.0f, 4.0f)
                curveTo(8.732f, 4.0f, 9.381f, 4.473f, 9.605f, 5.17f)
                lineTo(13.952f, 18.694f)
                curveTo(14.031f, 18.946f, 14.007f, 19.219f, 13.885f, 19.453f)
                curveTo(13.764f, 19.687f, 13.555f, 19.864f, 13.304f, 19.945f)
                curveTo(13.052f, 20.025f, 12.78f, 20.004f, 12.544f, 19.884f)
                curveTo(12.309f, 19.764f, 12.131f, 19.557f, 12.048f, 19.306f)
                lineTo(10.664f, 15.0f)
                horizontalLineTo(5.336f)
                lineTo(3.952f, 19.306f)
                curveTo(3.869f, 19.557f, 3.691f, 19.764f, 3.456f, 19.884f)
                curveTo(3.221f, 20.004f, 2.948f, 20.025f, 2.696f, 19.945f)
                curveTo(2.445f, 19.864f, 2.236f, 19.687f, 2.115f, 19.453f)
                curveTo(1.993f, 19.219f, 1.969f, 18.946f, 2.048f, 18.694f)
                lineTo(6.395f, 5.17f)
                curveTo(6.505f, 4.831f, 6.719f, 4.535f, 7.007f, 4.324f)
                curveTo(7.296f, 4.114f, 7.643f, 4.001f, 8.0f, 4.0f)
                close()
                moveTo(18.0f, 14.0f)
                curveTo(17.47f, 14.0f, 16.961f, 14.211f, 16.586f, 14.586f)
                curveTo(16.211f, 14.961f, 16.0f, 15.47f, 16.0f, 16.0f)
                curveTo(16.0f, 16.53f, 16.211f, 17.039f, 16.586f, 17.414f)
                curveTo(16.961f, 17.789f, 17.47f, 18.0f, 18.0f, 18.0f)
                curveTo(18.53f, 18.0f, 19.039f, 17.789f, 19.414f, 17.414f)
                curveTo(19.789f, 17.039f, 20.0f, 16.53f, 20.0f, 16.0f)
                curveTo(20.0f, 15.47f, 19.789f, 14.961f, 19.414f, 14.586f)
                curveTo(19.039f, 14.211f, 18.53f, 14.0f, 18.0f, 14.0f)
                close()
                moveTo(8.0f, 6.712f)
                lineTo(5.979f, 13.0f)
                horizontalLineTo(10.021f)
                lineTo(8.0f, 6.712f)
                close()
            }
        }
        .build()
        return _name!!
    }

private var _name: ImageVector? = null
