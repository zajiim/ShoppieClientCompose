package com.example.shoppieclient.utils

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection

class BottomNavigationShape(
    private val radius: Dp,
    private val spacing: Dp,
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val radiusPx = with(density) { radius.toPx() }
        val spacingPx = with(density) { spacing.toPx() }
        val leftPointX = size.center.x - radiusPx - 2f * spacingPx
        val leftPointY = 15f

        val leftFartherPointX = size.center.x - radiusPx * 0.3f
        val leftFartherPointY = 15f

        val leftNearPointX = size.center.x - radiusPx * 0.7f
        val leftNearPointY = radiusPx * 0.45f + spacingPx

        val centerPointX = size.center.x
        val centerPointY = radiusPx / 2f + spacingPx

        val rightNearPointX = size.center.x + radiusPx * 0.7f
        val rightNearPointY = radiusPx * 0.45f + spacingPx

        val rightFartherPointX = size.center.x + radiusPx * 0.3f
        val rightFartherPointY = 15f

        val rightPointX = size.center.x + radiusPx + 2f * spacingPx
        val rightPointY = 15f

        val path = Path().apply {
            moveTo(0f, 0f)
            lineTo(leftPointX, leftPointY)
            cubicTo(
                x1 = leftFartherPointX, y1 = leftFartherPointY,
                x2 = leftNearPointX, y2 = leftNearPointY,
                x3 = centerPointX, y3 = centerPointY,
            )
            cubicTo(
                x1 = rightNearPointX, y1 = rightNearPointY,
                x2 = rightFartherPointX, y2 = rightFartherPointY,
                x3 = rightPointX, y3 = rightPointY,
            )
            lineTo(size.width, 0f)
            lineTo(size.width, size.height)
            lineTo(0f, size.height)
            lineTo(0f, 0f)
            close()
        }
        return Outline.Generic(path)
    }
}

fun Modifier.bottomNavClip(radius: Dp, spacing: Dp): Modifier = this.clip(
    BottomNavigationShape(
        radius, spacing
    )
)