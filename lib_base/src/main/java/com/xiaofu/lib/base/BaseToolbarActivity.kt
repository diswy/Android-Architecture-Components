package com.xiaofu.lib.base

import android.view.LayoutInflater
import android.widget.FrameLayout
import com.xiaofu.student.lib.base.R

/**
 *
 * Created by @author xiaofu on 2018/12/3.
 */
abstract class BaseToolbarActivity : BaseActivity() {

    override fun setView() {
        setContentView(R.layout.activity_base_toolbar_bind)
        val parent = findViewById<FrameLayout>(R.id.container)
        LayoutInflater.from(this).inflate(getView(),parent,true)
    }
}