package com.cqebd.student.lib.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * DataBinding基础Activity
 * Created by @author xiaofu on 2018/11/27.
 */
abstract class BaseBindingActivity<out T> : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}