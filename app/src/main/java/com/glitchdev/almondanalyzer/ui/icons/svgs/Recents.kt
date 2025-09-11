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

public val AppIcons.Recents: ImageVector
    get() {
        if (_recents != null) {
            return _recents!!
        }
        _recents = Builder(name = "Recents", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd) {
                moveTo(6.0f, 3.0f)
                curveTo(5.735f, 3.0f, 5.48f, 3.105f, 5.293f, 3.293f)
                curveTo(5.105f, 3.48f, 5.0f, 3.735f, 5.0f, 4.0f)
                curveTo(5.0f, 4.265f, 5.105f, 4.52f, 5.293f, 4.707f)
                curveTo(5.48f, 4.895f, 5.735f, 5.0f, 6.0f, 5.0f)
                horizontalLineTo(18.0f)
                curveTo(18.265f, 5.0f, 18.52f, 4.895f, 18.707f, 4.707f)
                curveTo(18.895f, 4.52f, 19.0f, 4.265f, 19.0f, 4.0f)
                curveTo(19.0f, 3.735f, 18.895f, 3.48f, 18.707f, 3.293f)
                curveTo(18.52f, 3.105f, 18.265f, 3.0f, 18.0f, 3.0f)
                horizontalLineTo(6.0f)
                close()
                moveTo(4.18f, 6.0f)
                curveTo(3.89f, 6.0f, 3.603f, 6.063f, 3.339f, 6.186f)
                curveTo(3.076f, 6.308f, 2.842f, 6.486f, 2.654f, 6.708f)
                curveTo(2.467f, 6.929f, 2.33f, 7.189f, 2.253f, 7.469f)
                curveTo(2.175f, 7.749f, 2.16f, 8.043f, 2.208f, 8.329f)
                lineTo(3.874f, 18.329f)
                curveTo(3.952f, 18.796f, 4.193f, 19.22f, 4.554f, 19.526f)
                curveTo(4.915f, 19.832f, 5.374f, 20.0f, 5.847f, 20.0f)
                horizontalLineTo(18.153f)
                curveTo(18.626f, 20.0f, 19.084f, 19.832f, 19.446f, 19.526f)
                curveTo(19.807f, 19.22f, 20.048f, 18.796f, 20.126f, 18.329f)
                lineTo(21.792f, 8.329f)
                curveTo(21.84f, 8.043f, 21.825f, 7.749f, 21.747f, 7.469f)
                curveTo(21.67f, 7.189f, 21.533f, 6.929f, 21.346f, 6.708f)
                curveTo(21.158f, 6.486f, 20.924f, 6.308f, 20.661f, 6.186f)
                curveTo(20.397f, 6.063f, 20.11f, 6.0f, 19.82f, 6.0f)
                horizontalLineTo(4.18f)
                close()
                moveTo(4.18f, 8.0f)
                horizontalLineTo(19.82f)
                lineTo(18.153f, 18.0f)
                horizontalLineTo(5.847f)
                lineTo(4.18f, 8.0f)
                close()
            }
        }
        .build()
        return _recents!!
    }

private var _recents: ImageVector? = null
