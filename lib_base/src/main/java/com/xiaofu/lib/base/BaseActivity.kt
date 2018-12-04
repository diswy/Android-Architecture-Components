package com.xiaofu.lib.base

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.jetbrains.anko.AnkoLogger
import kotlin.coroutines.CoroutineContext

/**
 * 封装基础层，避免DataBinding过渡滥用
 * 封装部分通用方法
 * Created by @author xiaofu on 2018/12/3.
 */
abstract class BaseActivity : AppCompatActivity(), AnkoLogger, CoroutineScope {

    protected lateinit var TAG: String
    /**
     * 带Tag标签的日志
     * note:真机调试中，info以下级别的日志容易被过滤不显示
     */
    protected val log = AnkoLogger("xiaofu")

    protected lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    /**
     * 获取视图ID
     */
    abstract fun getView(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
        TAG = componentName.className
        /**
         * 不同于style中的半透明状态栏，此设置为全屏沉浸式
         */
        if (isFullScreen()) {
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
        if (isKeepScreenOn()) {
            window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        }
        setView()
        initialize()
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    protected open fun isFullScreen() = false
    protected open fun isKeepScreenOn() = false

    protected open fun setView() {
        setContentView(getView())
    }

    protected open fun initialize() {

    }

    /**
     * 强制显示软键盘
     * @param view 需要是EditText及其子类
     */
    protected open fun showSoftKeyboard(view: View) {
        if (view.requestFocus()) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    /**
     * 强制关闭软键盘
     * @param view 这里可以是任意view
     */
    protected open fun hideSoftKeyboard(view: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}