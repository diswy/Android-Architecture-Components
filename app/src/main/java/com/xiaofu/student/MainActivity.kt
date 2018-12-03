package com.xiaofu.student

import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.xiaofu.lib.base.BaseToolbarBindActivity
import com.xiaofu.student.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.design.longSnackbar
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.progressDialog
import org.jetbrains.anko.toast

class MainActivity : BaseToolbarBindActivity<ActivityMainBinding>() {
    override fun getView(): Int {
        return R.layout.activity_main
    }

    override fun initialize(binding: ActivityMainBinding) {
        binding.userModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        binding.setLifecycleOwner(this)

        val v = findViewById<TextView>(R.id.tv)
    }

}
