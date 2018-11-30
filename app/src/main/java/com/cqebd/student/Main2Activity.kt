package com.cqebd.student

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil.setContentView
import com.cqebd.student.databinding.ActivityMain2Binding
import com.cqebd.student.lib.base.BaseBindingActivity

class Main2Activity : BaseBindingActivity<ActivityMain2Binding>() {
    override fun getView(): Int = R.layout.activity_main2

    override fun initialize(binding: ActivityMain2Binding) {

    }
}
