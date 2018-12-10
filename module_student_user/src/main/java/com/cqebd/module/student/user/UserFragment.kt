package com.cqebd.module.student.user


import androidx.fragment.app.FragmentActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.cqebd.module.student.user.databinding.FragmentUserBinding
import com.xiaofu.lib.base.fragment.BaseBindFragment

/**
 *
 */
@Route(path = "/ebd/student/user")
class UserFragment : BaseBindFragment<FragmentUserBinding>() {

    override fun getLayoutRes(): Int = R.layout.fragment_user

    override fun initialize(activity: FragmentActivity, binding: FragmentUserBinding) {

    }

}
