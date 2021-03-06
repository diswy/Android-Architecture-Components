package com.xiaofu.lib.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.FrameLayout
import com.xiaofu.lib.view.drawable.Constants
import com.xiaofu.lib.view.drawable.DrawableBuilder
import com.xiaofu.student.lib.base.R

/**
 *
 * Created by @author xiaofu on 2018/12/14.
 */
class FancyGroup @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : FrameLayout(context, attrs, defStyleAttr) {

    private var mDrawable: Drawable? = null

    private var mShape: Int = Constants.RECT// 样式
    private var mBorderType: Int = Constants.LINE// 边框样式
    private var mBorderWidth: Float = 0f// 边框宽度
    private var mBorderColor: Int = Constants.DEFAULT_COLOR// 边框颜色
    private var mSolidNormal: Int = Constants.DEFAULT_COLOR// 实体颜色普通
    private var mSolidPressed: Int = Constants.DEFAULT_COLOR// 实体颜色按压状态
    private var mRipple: Boolean = true// 是否使用水波纹效果

    init {
        if (attrs != null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.FancyGroup)
            mShape = a.getInteger(R.styleable.FancyGroup_fg_shape, Constants.RECT)
            mBorderType = a.getInteger(R.styleable.FancyGroup_fg_border_type, Constants.LINE)
            mBorderWidth = a.getDimension(R.styleable.FancyGroup_fg_border_width, 0f)
            mBorderColor = a.getInteger(R.styleable.FancyGroup_fg_border_color, Constants.DEFAULT_COLOR)
            mSolidNormal = a.getInteger(R.styleable.FancyGroup_fg_solid_normal, Constants.DEFAULT_COLOR)
            mSolidPressed = a.getInteger(R.styleable.FancyGroup_fg_solid_pressed, Constants.DEFAULT_COLOR)
            mRipple = a.getBoolean(R.styleable.FancyGroup_fg_ripple, true)
            a.recycle()

            val builder = DrawableBuilder()
            when (mShape) {
                Constants.RECT -> builder.rectangle()
                Constants.ROUNDED -> builder.rounded()
                Constants.OVAL -> builder.oval()
            }
            when (mBorderType) {
                Constants.LINE -> builder.hairlineBordered()
                Constants.DASH -> builder.dashed()
            }
            mDrawable = builder.strokeWidth(mBorderWidth.toInt())
                    .strokeColor(mBorderColor)
                    .solidColor(mSolidNormal)
                    .solidColorPressed(if (mSolidPressed == Constants.DEFAULT_COLOR) mSolidNormal else mSolidPressed)
                    .ripple(mRipple)
                    .build()

            setWillNotDraw(false)
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (!isInEditMode) {
            background = mDrawable
        }
    }
}