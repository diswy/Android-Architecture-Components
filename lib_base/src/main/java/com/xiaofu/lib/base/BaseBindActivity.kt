package com.xiaofu.lib.base

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * DataBinding基础Activity
 * Created by @author xiaofu on 2018/11/27.
 */
abstract class BaseBindActivity<T : ViewDataBinding> : BaseActivity() {

    /**
     * 对应视图返回的ViewDataBinding
     */
    protected lateinit var binding: T

    override fun setView() {
        binding = DataBindingUtil.setContentView(this, getView())
    }

    override fun initialize() {
        initialize(binding)
    }

    protected open fun initialize(binding: T) {

    }


}