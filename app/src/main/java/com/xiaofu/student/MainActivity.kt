package com.xiaofu.student

import android.graphics.Color
import androidx.lifecycle.ViewModelProviders
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.xiaofu.lib.base.BaseBindActivity
import com.xiaofu.lib.base.BaseToolbarBindActivity
import com.xiaofu.lib.inline.loadUrl
import com.xiaofu.lib.inline.loadUrlHigh
import com.xiaofu.lib.inline.onClick
import com.xiaofu.student.databinding.ActivityMainBinding
import com.xiaofu.student.entity.movie
import com.xiaofu.student.net.ApiService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class MainActivity : BaseBindActivity<ActivityMainBinding>() {
    override fun getView(): Int {
        return R.layout.activity_main
    }

    override fun initialize(binding: ActivityMainBinding) {
//        binding.userModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
//        binding.setLifecycleOwner(this)

        binding.mainTab.addItem(BottomNavigationItem(R.drawable.main_tab_video, "首页").setActiveColor(Color.parseColor("#33e5e5")))
                .addItem(BottomNavigationItem(R.drawable.main_tab_mine, "我的").setActiveColor(Color.parseColor("#33e5e5")))
                .initialise()


//        // 默认使用过滤300ms之内的点击事件
//        btnView.onClick {
//            it.snackbar("Hi there", "取消") { toast("我点击了取消") }
//        }
//
//        iv.loadUrl(this, "http://img.hb.aicdn.com/0601ba0c597f3126da59eed23938c6ba8770d6771d0d5-F0veQO_fw658")
//        iv2.loadUrlHigh(this, "http://img.hb.aicdn.com/0601ba0c597f3126da59eed23938c6ba8770d6771d0d5-F0veQO_fw658")
    }


    suspend fun getTest() {
        log.info { "No.3  开始请求" }
        val deferred: Deferred<movie> = async(Dispatchers.IO) {
            log.info { "No.4  IO线程发生" }
            test()
        }
        log.info { "No.5  协程线程发生" }
        withContext(Dispatchers.Main) {
            log.info { "No.8  等待获取结果" }
            val data = deferred.await()
            log.info { "No.9  获取结果结束" }
            log.info { "No.10 结果：" + data.toString() }
            // Show data in UI
        }
    }

    private suspend fun test(): movie = suspendCoroutine { cont ->
        val retrofit = Retrofit.Builder()
                .baseUrl("http://apicloud.mob.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val service = retrofit.create(ApiService::class.java)
        log.info { "No.6  协程线程发生要开始了" }
        service.getMovie()
                .enqueue(object : Callback<movie> {
                    override fun onFailure(call: Call<movie>, t: Throwable) {
                        cont.resumeWithException(t)
                    }

                    override fun onResponse(call: Call<movie>, response: Response<movie>) {
                        log.info { "No.7  网络请求结束" }
                        log.info(response.body().toString())
                        cont.resume(response.body()!!)
                    }
                })
    }

}
