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

public val AppIcons.Tree: ImageVector
    get() {
        if (_tree != null) {
            return _tree!!
        }
        _tree = Builder(name = "Tree", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd) {
                moveTo(12.707f, 2.293f)
                lineTo(16.707f, 6.293f)
                curveTo(16.859f, 6.445f, 16.958f, 6.641f, 16.989f, 6.853f)
                curveTo(17.021f, 7.065f, 16.983f, 7.282f, 16.882f, 7.471f)
                curveTo(16.635f, 7.934f, 16.249f, 8.246f, 15.872f, 8.458f)
                lineTo(18.707f, 11.293f)
                curveTo(18.895f, 11.481f, 19.0f, 11.735f, 19.0f, 12.0f)
                curveTo(19.0f, 12.265f, 18.895f, 12.519f, 18.707f, 12.707f)
                curveTo(18.229f, 13.185f, 17.625f, 13.477f, 17.073f, 13.659f)
                lineTo(19.707f, 16.293f)
                curveTo(19.828f, 16.414f, 19.915f, 16.563f, 19.962f, 16.727f)
                curveTo(20.008f, 16.891f, 20.012f, 17.064f, 19.973f, 17.23f)
                curveTo(19.934f, 17.396f, 19.853f, 17.549f, 19.737f, 17.675f)
                curveTo(19.622f, 17.801f, 19.477f, 17.895f, 19.315f, 17.949f)
                curveTo(18.623f, 18.178f, 17.91f, 18.338f, 17.196f, 18.481f)
                curveTo(16.654f, 18.589f, 16.021f, 18.697f, 15.316f, 18.787f)
                lineTo(15.949f, 20.684f)
                curveTo(15.999f, 20.834f, 16.013f, 20.994f, 15.989f, 21.151f)
                curveTo(15.965f, 21.308f, 15.904f, 21.456f, 15.811f, 21.585f)
                curveTo(15.719f, 21.713f, 15.597f, 21.818f, 15.456f, 21.89f)
                curveTo(15.315f, 21.962f, 15.158f, 22.0f, 15.0f, 22.0f)
                horizontalLineTo(9.0f)
                curveTo(8.842f, 22.0f, 8.685f, 21.962f, 8.544f, 21.89f)
                curveTo(8.403f, 21.818f, 8.281f, 21.713f, 8.189f, 21.585f)
                curveTo(8.096f, 21.456f, 8.035f, 21.308f, 8.011f, 21.151f)
                curveTo(7.987f, 20.994f, 8.001f, 20.834f, 8.051f, 20.684f)
                lineTo(8.684f, 18.787f)
                curveTo(8.054f, 18.707f, 7.427f, 18.605f, 6.804f, 18.481f)
                curveTo(6.269f, 18.374f, 5.739f, 18.248f, 5.213f, 18.103f)
                lineTo(4.683f, 17.949f)
                curveTo(4.521f, 17.895f, 4.376f, 17.801f, 4.261f, 17.675f)
                curveTo(4.146f, 17.549f, 4.065f, 17.396f, 4.026f, 17.23f)
                curveTo(3.987f, 17.064f, 3.991f, 16.891f, 4.038f, 16.727f)
                curveTo(4.085f, 16.563f, 4.172f, 16.413f, 4.293f, 16.293f)
                lineTo(6.927f, 13.659f)
                curveTo(6.375f, 13.478f, 5.771f, 13.185f, 5.293f, 12.707f)
                curveTo(5.106f, 12.519f, 5.0f, 12.265f, 5.0f, 12.0f)
                curveTo(5.0f, 11.735f, 5.106f, 11.481f, 5.293f, 11.293f)
                lineTo(8.128f, 8.458f)
                curveTo(7.751f, 8.246f, 7.365f, 7.934f, 7.118f, 7.471f)
                curveTo(7.017f, 7.282f, 6.98f, 7.065f, 7.011f, 6.853f)
                curveTo(7.043f, 6.641f, 7.141f, 6.445f, 7.293f, 6.293f)
                lineTo(11.293f, 2.293f)
                curveTo(11.481f, 2.106f, 11.735f, 2.0f, 12.0f, 2.0f)
                curveTo(12.265f, 2.0f, 12.519f, 2.106f, 12.707f, 2.293f)
                close()
                moveTo(13.269f, 18.968f)
                curveTo(12.562f, 19.003f, 11.854f, 19.009f, 11.146f, 18.986f)
                lineTo(10.731f, 18.968f)
                lineTo(10.387f, 20.0f)
                horizontalLineTo(13.613f)
                lineTo(13.269f, 18.968f)
                close()
                moveTo(12.0f, 4.414f)
                lineTo(9.528f, 6.886f)
                lineTo(9.628f, 6.915f)
                lineTo(9.815f, 6.958f)
                lineTo(10.091f, 7.004f)
                curveTo(10.474f, 7.044f, 10.808f, 7.294f, 10.941f, 7.659f)
                curveTo(11.006f, 7.838f, 11.018f, 8.031f, 10.977f, 8.217f)
                curveTo(10.935f, 8.403f, 10.842f, 8.573f, 10.707f, 8.707f)
                lineTo(7.63f, 11.784f)
                curveTo(8.053f, 11.914f, 8.535f, 12.0f, 9.002f, 12.0f)
                curveTo(9.2f, 12.0f, 9.393f, 12.059f, 9.557f, 12.169f)
                curveTo(9.721f, 12.28f, 9.849f, 12.436f, 9.924f, 12.618f)
                curveTo(9.999f, 12.801f, 10.019f, 13.002f, 9.98f, 13.196f)
                curveTo(9.942f, 13.389f, 9.847f, 13.567f, 9.707f, 13.707f)
                lineTo(6.947f, 16.468f)
                lineTo(7.461f, 16.571f)
                curveTo(8.639f, 16.793f, 10.218f, 17.0f, 12.0f, 17.0f)
                curveTo(13.428f, 16.997f, 14.854f, 16.871f, 16.26f, 16.622f)
                lineTo(16.804f, 16.519f)
                lineTo(17.054f, 16.468f)
                lineTo(14.293f, 13.708f)
                curveTo(14.16f, 13.575f, 14.068f, 13.408f, 14.026f, 13.225f)
                curveTo(13.983f, 13.042f, 13.994f, 12.851f, 14.055f, 12.674f)
                curveTo(14.116f, 12.497f, 14.226f, 12.34f, 14.372f, 12.222f)
                curveTo(14.519f, 12.105f, 14.695f, 12.03f, 14.881f, 12.008f)
                lineTo(14.999f, 12.0f)
                curveTo(15.464f, 11.992f, 15.925f, 11.919f, 16.37f, 11.784f)
                lineTo(13.293f, 8.707f)
                curveTo(13.166f, 8.58f, 13.076f, 8.422f, 13.031f, 8.249f)
                curveTo(12.987f, 8.075f, 12.99f, 7.893f, 13.04f, 7.721f)
                curveTo(13.089f, 7.549f, 13.184f, 7.394f, 13.315f, 7.271f)
                curveTo(13.445f, 7.149f, 13.606f, 7.063f, 13.781f, 7.024f)
                lineTo(14.104f, 6.974f)
                lineTo(14.276f, 6.938f)
                curveTo(14.339f, 6.925f, 14.404f, 6.907f, 14.472f, 6.886f)
                lineTo(12.0f, 4.414f)
                close()
            }
        }
        .build()
        return _tree!!
    }

private var _tree: ImageVector? = null
