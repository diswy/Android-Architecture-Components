package com.xiaofu.lib.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;

import androidx.appcompat.widget.AppCompatButton;
import top.defaults.drawabletoolbox.DrawableBuilder;

/**
 * Created by @author xiaofu on 2018/12/11.
 */
public class FancyButton extends AppCompatButton {

    private Drawable mDrawable;

    public FancyButton(Context context) {
        super(context);
    }

    public FancyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setBackground(mDrawable);
    }

    private void init() {
        if (!isInEditMode()){
            mDrawable = new DrawableBuilder()
                    .rectangle()
                    .hairlineBordered()
                    .strokeWidth(10)
                    .strokeColor(Color.parseColor("#ff000000"))
                    .strokeColorPressed(Color.parseColor("#33e5e5"))
                    .ripple()
                    .build();
        }
    }
}
