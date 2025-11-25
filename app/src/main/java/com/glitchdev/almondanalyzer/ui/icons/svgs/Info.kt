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

public val AppIcons.Info: ImageVector
    get() {
        if (_info != null) {
            return _info!!
        }
        _info = Builder(name = "Info", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(12.0f, 2.0f)
                curveTo(17.523f, 2.0f, 22.0f, 6.477f, 22.0f, 12.0f)
                curveTo(22.0f, 17.523f, 17.523f, 22.0f, 12.0f, 22.0f)
                curveTo(6.477f, 22.0f, 2.0f, 17.523f, 2.0f, 12.0f)
                curveTo(2.0f, 6.477f, 6.477f, 2.0f, 12.0f, 2.0f)
                close()
                moveTo(12.0f, 4.0f)
                curveTo(9.878f, 4.0f, 7.843f, 4.843f, 6.343f, 6.343f)
                curveTo(4.843f, 7.843f, 4.0f, 9.878f, 4.0f, 12.0f)
                curveTo(4.0f, 14.122f, 4.843f, 16.157f, 6.343f, 17.657f)
                curveTo(7.843f, 19.157f, 9.878f, 20.0f, 12.0f, 20.0f)
                curveTo(14.122f, 20.0f, 16.157f, 19.157f, 17.657f, 17.657f)
                curveTo(19.157f, 16.157f, 20.0f, 14.122f, 20.0f, 12.0f)
                curveTo(20.0f, 9.878f, 19.157f, 7.843f, 17.657f, 6.343f)
                curveTo(16.157f, 4.843f, 14.122f, 4.0f, 12.0f, 4.0f)
                close()
                moveTo(11.99f, 10.0f)
                curveTo(12.548f, 10.0f, 13.0f, 10.452f, 13.0f, 11.01f)
                verticalLineTo(16.134f)
                curveTo(13.191f, 16.244f, 13.34f, 16.414f, 13.424f, 16.617f)
                curveTo(13.508f, 16.821f, 13.523f, 17.046f, 13.466f, 17.259f)
                curveTo(13.409f, 17.471f, 13.283f, 17.659f, 13.109f, 17.793f)
                curveTo(12.934f, 17.927f, 12.72f, 18.0f, 12.5f, 18.0f)
                horizontalLineTo(12.01f)
                curveTo(11.877f, 18.0f, 11.746f, 17.974f, 11.623f, 17.923f)
                curveTo(11.501f, 17.872f, 11.39f, 17.798f, 11.296f, 17.704f)
                curveTo(11.202f, 17.61f, 11.128f, 17.499f, 11.077f, 17.376f)
                curveTo(11.026f, 17.254f, 11.0f, 17.123f, 11.0f, 16.99f)
                verticalLineTo(12.0f)
                curveTo(10.735f, 12.0f, 10.48f, 11.895f, 10.293f, 11.707f)
                curveTo(10.105f, 11.52f, 10.0f, 11.265f, 10.0f, 11.0f)
                curveTo(10.0f, 10.735f, 10.105f, 10.48f, 10.293f, 10.293f)
                curveTo(10.48f, 10.105f, 10.735f, 10.0f, 11.0f, 10.0f)
                horizontalLineTo(11.99f)
                close()
                moveTo(12.0f, 7.0f)
                curveTo(12.265f, 7.0f, 12.52f, 7.105f, 12.707f, 7.293f)
                curveTo(12.895f, 7.48f, 13.0f, 7.735f, 13.0f, 8.0f)
                curveTo(13.0f, 8.265f, 12.895f, 8.52f, 12.707f, 8.707f)
                curveTo(12.52f, 8.895f, 12.265f, 9.0f, 12.0f, 9.0f)
                curveTo(11.735f, 9.0f, 11.48f, 8.895f, 11.293f, 8.707f)
                curveTo(11.105f, 8.52f, 11.0f, 8.265f, 11.0f, 8.0f)
                curveTo(11.0f, 7.735f, 11.105f, 7.48f, 11.293f, 7.293f)
                curveTo(11.48f, 7.105f, 11.735f, 7.0f, 12.0f, 7.0f)
                close()
            }
        }
        .build()
        return _info!!
    }

private var _info: ImageVector? = null
