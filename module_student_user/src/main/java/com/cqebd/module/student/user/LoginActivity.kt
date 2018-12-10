package com.cqebd.module.student.user

import com.alibaba.android.arouter.facade.annotation.Route
import com.xiaofu.lib.base.activity.BaseActivity


@Route(path = "/ebd/student/login")
class LoginActivity : BaseActivity() {

    override fun isTranslucentMode(): Boolean = true

    override fun isStatusDarkMode(): Boolean = true

    override fun getLayoutRes(): Int = R.layout.activity_login

    override fun initialize() {

    }

}
