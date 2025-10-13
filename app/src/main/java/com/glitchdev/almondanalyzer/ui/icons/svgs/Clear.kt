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

public val AppIcons.Clear: ImageVector
    get() {
        if (_clear != null) {
            return _clear!!
        }
        _clear = Builder(name = "Clear", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd) {
                moveTo(12.0f, 13.414f)
                lineTo(17.657f, 19.071f)
                curveTo(17.846f, 19.253f, 18.098f, 19.354f, 18.36f, 19.352f)
                curveTo(18.623f, 19.349f, 18.873f, 19.244f, 19.059f, 19.059f)
                curveTo(19.244f, 18.873f, 19.349f, 18.623f, 19.352f, 18.36f)
                curveTo(19.354f, 18.098f, 19.253f, 17.846f, 19.071f, 17.657f)
                lineTo(13.414f, 12.0f)
                lineTo(19.071f, 6.343f)
                curveTo(19.253f, 6.154f, 19.354f, 5.902f, 19.352f, 5.64f)
                curveTo(19.349f, 5.377f, 19.244f, 5.127f, 19.059f, 4.941f)
                curveTo(18.873f, 4.756f, 18.623f, 4.651f, 18.36f, 4.648f)
                curveTo(18.098f, 4.646f, 17.846f, 4.747f, 17.657f, 4.929f)
                lineTo(12.0f, 10.586f)
                lineTo(6.343f, 4.929f)
                curveTo(6.154f, 4.751f, 5.902f, 4.654f, 5.643f, 4.659f)
                curveTo(5.383f, 4.663f, 5.135f, 4.768f, 4.952f, 4.952f)
                curveTo(4.768f, 5.135f, 4.663f, 5.383f, 4.659f, 5.643f)
                curveTo(4.655f, 5.903f, 4.752f, 6.154f, 4.93f, 6.343f)
                lineTo(10.586f, 12.0f)
                lineTo(4.929f, 17.657f)
                curveTo(4.833f, 17.749f, 4.757f, 17.86f, 4.705f, 17.982f)
                curveTo(4.652f, 18.104f, 4.625f, 18.235f, 4.624f, 18.368f)
                curveTo(4.623f, 18.5f, 4.648f, 18.632f, 4.698f, 18.755f)
                curveTo(4.748f, 18.878f, 4.823f, 18.99f, 4.917f, 19.083f)
                curveTo(5.01f, 19.177f, 5.122f, 19.251f, 5.245f, 19.302f)
                curveTo(5.368f, 19.352f, 5.5f, 19.377f, 5.632f, 19.376f)
                curveTo(5.765f, 19.375f, 5.896f, 19.347f, 6.018f, 19.295f)
                curveTo(6.14f, 19.243f, 6.251f, 19.167f, 6.343f, 19.071f)
                lineTo(12.0f, 13.414f)
                close()
            }
        }
        .build()
        return _clear!!
    }

private var _clear: ImageVector? = null
