package com.xiaofu.student

import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import com.xiaofu.lib.base.BaseToolbarBindActivity
import com.xiaofu.student.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : BaseToolbarBindActivity<ActivityMainBinding>() {
    override fun getView(): Int {
        return R.layout.activity_main
    }

    override fun initialize(binding: ActivityMainBinding) {
        binding.userModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        binding.setLifecycleOwner(this)

        val v = findViewById<TextView>(R.id.tv)

        asyncShowData()
    }

    fun asyncShowData() = launch { // Is invoked in UI context with Activity's job as a parent
        // actual implementation
        println("--->>>开始")
        showIOData()
    }

    suspend fun showIOData() {
        println("--->>>开始showIOData")
        val deferred :Deferred<String> = async(Dispatchers.IO) {
            // impl
            for (i in 30 downTo 1){
                println("--->>>$i")
                delay(1000L)
            }
            println("--->>>over")
            "--->>>end"
        }
        println("--->>>中间")
        withContext(Dispatchers.Main) {
            println("--->>>withContext 开始")
            val data = deferred.await()
            println("--->>>withContext 结束")
            println("--->>>withContext $data")
            // Show data in UI
        }

        println("--->>>彻底结束")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("--->>>真的结束了")
    }

}
