package com.xiaofu.student

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter

/**
 *
 * Created by @author xiaofu on 2018/12/10.
 */
class EbdApp : Application() {
    override fun onCreate() {
        super.onCreate()
        ARouter.openDebug()
        ARouter.init(this)
    }
}