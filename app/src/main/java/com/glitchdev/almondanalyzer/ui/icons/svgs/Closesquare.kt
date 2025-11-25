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

public val AppIcons.CloseSquare: ImageVector
    get() {
        if (_closesquare != null) {
            return _closesquare!!
        }
        _closesquare = Builder(name = "Closesquare", defaultWidth = 24.0.dp, defaultHeight =
                24.0.dp, viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd) {
                moveTo(3.0f, 5.0f)
                curveTo(3.0f, 4.47f, 3.211f, 3.961f, 3.586f, 3.586f)
                curveTo(3.961f, 3.211f, 4.47f, 3.0f, 5.0f, 3.0f)
                horizontalLineTo(19.0f)
                curveTo(19.53f, 3.0f, 20.039f, 3.211f, 20.414f, 3.586f)
                curveTo(20.789f, 3.961f, 21.0f, 4.47f, 21.0f, 5.0f)
                verticalLineTo(19.0f)
                curveTo(21.0f, 19.53f, 20.789f, 20.039f, 20.414f, 20.414f)
                curveTo(20.039f, 20.789f, 19.53f, 21.0f, 19.0f, 21.0f)
                horizontalLineTo(5.0f)
                curveTo(4.47f, 21.0f, 3.961f, 20.789f, 3.586f, 20.414f)
                curveTo(3.211f, 20.039f, 3.0f, 19.53f, 3.0f, 19.0f)
                verticalLineTo(5.0f)
                close()
                moveTo(19.0f, 5.0f)
                horizontalLineTo(5.0f)
                verticalLineTo(19.0f)
                horizontalLineTo(19.0f)
                verticalLineTo(5.0f)
                close()
                moveTo(8.464f, 8.464f)
                curveTo(8.557f, 8.371f, 8.667f, 8.297f, 8.789f, 8.247f)
                curveTo(8.91f, 8.197f, 9.04f, 8.171f, 9.172f, 8.171f)
                curveTo(9.303f, 8.171f, 9.433f, 8.197f, 9.554f, 8.247f)
                curveTo(9.676f, 8.297f, 9.786f, 8.371f, 9.879f, 8.464f)
                lineTo(12.0f, 10.586f)
                lineTo(14.121f, 8.464f)
                curveTo(14.214f, 8.371f, 14.324f, 8.297f, 14.446f, 8.247f)
                curveTo(14.567f, 8.197f, 14.697f, 8.171f, 14.828f, 8.171f)
                curveTo(14.96f, 8.171f, 15.09f, 8.197f, 15.211f, 8.247f)
                curveTo(15.333f, 8.297f, 15.443f, 8.371f, 15.536f, 8.464f)
                curveTo(15.629f, 8.557f, 15.703f, 8.667f, 15.753f, 8.789f)
                curveTo(15.803f, 8.91f, 15.829f, 9.04f, 15.829f, 9.172f)
                curveTo(15.829f, 9.303f, 15.803f, 9.433f, 15.753f, 9.554f)
                curveTo(15.703f, 9.676f, 15.629f, 9.786f, 15.536f, 9.879f)
                lineTo(13.414f, 12.0f)
                lineTo(15.536f, 14.121f)
                curveTo(15.724f, 14.309f, 15.829f, 14.563f, 15.829f, 14.828f)
                curveTo(15.829f, 15.094f, 15.724f, 15.348f, 15.536f, 15.536f)
                curveTo(15.348f, 15.724f, 15.094f, 15.829f, 14.828f, 15.829f)
                curveTo(14.563f, 15.829f, 14.309f, 15.724f, 14.121f, 15.536f)
                lineTo(12.0f, 13.414f)
                lineTo(9.879f, 15.536f)
                curveTo(9.786f, 15.629f, 9.676f, 15.703f, 9.554f, 15.753f)
                curveTo(9.433f, 15.803f, 9.303f, 15.829f, 9.172f, 15.829f)
                curveTo(9.04f, 15.829f, 8.91f, 15.803f, 8.789f, 15.753f)
                curveTo(8.667f, 15.703f, 8.557f, 15.629f, 8.464f, 15.536f)
                curveTo(8.371f, 15.443f, 8.297f, 15.333f, 8.247f, 15.211f)
                curveTo(8.197f, 15.09f, 8.171f, 14.96f, 8.171f, 14.828f)
                curveTo(8.171f, 14.697f, 8.197f, 14.567f, 8.247f, 14.446f)
                curveTo(8.297f, 14.324f, 8.371f, 14.214f, 8.464f, 14.121f)
                lineTo(10.586f, 12.0f)
                lineTo(8.464f, 9.879f)
                curveTo(8.371f, 9.786f, 8.297f, 9.676f, 8.247f, 9.554f)
                curveTo(8.197f, 9.433f, 8.171f, 9.303f, 8.171f, 9.172f)
                curveTo(8.171f, 9.04f, 8.197f, 8.91f, 8.247f, 8.789f)
                curveTo(8.297f, 8.667f, 8.371f, 8.557f, 8.464f, 8.464f)
                close()
            }
        }
        .build()
        return _closesquare!!
    }

private var _closesquare: ImageVector? = null
