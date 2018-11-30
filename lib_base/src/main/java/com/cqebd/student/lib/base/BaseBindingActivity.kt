package com.cqebd.student.lib.base

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * DataBinding基础Activity
 * Created by @author xiaofu on 2018/11/27.
 */
abstract class BaseBindingActivity<T : ViewDataBinding> : AppCompatActivity() {

    /**
     * 对应视图返回的ViewDataBinding
     */
    private lateinit var binding: T

    /**
     * 获取视图ID
     */
    abstract fun getView(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (isFullScreen()) {
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
        if (isKeepScreenOn()) {
            window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        }
        setView()
        initialize(binding)
    }

    protected open fun isFullScreen() = false
    protected open fun isKeepScreenOn() = false

    protected open fun setView() {
        binding = DataBindingUtil.setContentView(this, getView())
    }

    protected open fun initialize(binding: T){

    }


}