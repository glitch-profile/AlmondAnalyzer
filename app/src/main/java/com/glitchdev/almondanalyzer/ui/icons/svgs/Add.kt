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

public val AppIcons.Add: ImageVector
    get() {
        if (_add != null) {
            return _add!!
        }
        _add = Builder(name = "Add", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp, viewportWidth
                = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(11.0f, 20.0f)
                curveTo(11.0f, 20.265f, 11.105f, 20.52f, 11.293f, 20.707f)
                curveTo(11.48f, 20.895f, 11.735f, 21.0f, 12.0f, 21.0f)
                curveTo(12.265f, 21.0f, 12.52f, 20.895f, 12.707f, 20.707f)
                curveTo(12.895f, 20.52f, 13.0f, 20.265f, 13.0f, 20.0f)
                verticalLineTo(13.0f)
                horizontalLineTo(20.0f)
                curveTo(20.265f, 13.0f, 20.52f, 12.895f, 20.707f, 12.707f)
                curveTo(20.895f, 12.52f, 21.0f, 12.265f, 21.0f, 12.0f)
                curveTo(21.0f, 11.735f, 20.895f, 11.48f, 20.707f, 11.293f)
                curveTo(20.52f, 11.105f, 20.265f, 11.0f, 20.0f, 11.0f)
                horizontalLineTo(13.0f)
                verticalLineTo(4.0f)
                curveTo(13.0f, 3.735f, 12.895f, 3.48f, 12.707f, 3.293f)
                curveTo(12.52f, 3.105f, 12.265f, 3.0f, 12.0f, 3.0f)
                curveTo(11.735f, 3.0f, 11.48f, 3.105f, 11.293f, 3.293f)
                curveTo(11.105f, 3.48f, 11.0f, 3.735f, 11.0f, 4.0f)
                verticalLineTo(11.0f)
                horizontalLineTo(4.0f)
                curveTo(3.735f, 11.0f, 3.48f, 11.105f, 3.293f, 11.293f)
                curveTo(3.105f, 11.48f, 3.0f, 11.735f, 3.0f, 12.0f)
                curveTo(3.0f, 12.265f, 3.105f, 12.52f, 3.293f, 12.707f)
                curveTo(3.48f, 12.895f, 3.735f, 13.0f, 4.0f, 13.0f)
                horizontalLineTo(11.0f)
                verticalLineTo(20.0f)
                close()
            }
        }
        .build()
        return _add!!
    }

private var _add: ImageVector? = null
