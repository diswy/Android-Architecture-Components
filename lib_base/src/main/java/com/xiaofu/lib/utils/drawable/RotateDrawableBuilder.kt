package com.xiaofu.lib.utils.drawable

import android.graphics.drawable.Drawable
import android.graphics.drawable.RotateDrawable

class RotateDrawableBuilder: DrawableWrapperBuilder<RotateDrawableBuilder>() {

    private var pivotX: Float = 0.5f
    private var pivotY: Float = 0.5f
    private var fromDegrees: Float = 0f
    private var toDegrees: Float = 360f

    fun pivotX(x: Float) = apply { pivotX = x }
    fun pivotY(y: Float) = apply { pivotY = y }
    fun fromDegrees(degree: Float) = apply { fromDegrees = degree }
    fun toDegrees(degree: Float) = apply { toDegrees = degree }

    override fun build(): Drawable {
        val rotateDrawable = RotateDrawable()
        drawable?.let {
            com.xiaofu.lib.utils.drawable.setDrawable(rotateDrawable, it)
            apply {
                com.xiaofu.lib.utils.drawable.setPivotX(rotateDrawable, pivotX)
                com.xiaofu.lib.utils.drawable.setPivotY(rotateDrawable, pivotY)
                com.xiaofu.lib.utils.drawable.setFromDegrees(rotateDrawable, fromDegrees)
                com.xiaofu.lib.utils.drawable.setToDegrees(rotateDrawable, toDegrees)
            }
        }
        return rotateDrawable
    }
}