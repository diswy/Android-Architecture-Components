package com.xiaofu.student

import android.graphics.Color
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.xiaofu.lib.base.activity.BaseBindActivity
import com.xiaofu.student.databinding.ActivityMainBinding
import com.xiaofu.student.entity.movie
import com.xiaofu.student.net.ApiService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import org.jetbrains.anko.info
import org.jetbrains.anko.warn
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class MainActivity : BaseBindActivity<ActivityMainBinding>(), BottomNavigationBar.OnTabSelectedListener {

    override fun onTabReselected(position: Int) {}

    override fun onTabUnselected(position: Int) {}

    override fun onTabSelected(position: Int) {
        log.info("onTabSelected pos :$position")
        replaceFragments(position)
    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_main
    }

    override fun initialize(binding: ActivityMainBinding) {
//        binding.userModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
//        binding.setLifecycleOwner(this)
        val a = ViewModelProviders.of(this).get(UserViewModel::class.java)
        log.warn { "frag activity:$a" }
        a.getUser().observe(this, Observer {
            testTv.text = it.name
        })
        testBtn.setOnClickListener {
            a.modifyName()
        }

        binding.mainTab.addItem(BottomNavigationItem(R.drawable.main_tab_video, "首页").setActiveColor(Color.parseColor("#33e5e5")))
                .addItem(BottomNavigationItem(R.drawable.main_tab_mine, "我的").setActiveColor(Color.parseColor("#33e5e5")))
                .setBarBackgroundColor(R.color.colorPrimary)
                .initialise()
        binding.mainTab.selectTab(0)

        binding.mainTab.setTabSelectedListener(this)
        replaceFragments(0)

//        launch {
//            for (i in 1..50){
//                log.info { "what  launch:$this" }
//                delay(1000L)
//            }
//        }
//
//        GlobalScope.launch {
//            for (i in 1..50){
//                log.info { "what  GlobalScope:$this" }
//                delay(1000L)
//            }
//        }
//        launch(Dispatchers.Main) {
//            for (i in 1..50){
//                log.info { "what  launch  Main:$this" }
//                testTv.text = i.toString()
//                delay(1000L)
//            }
//        }

//        // 默认使用过滤300ms之内的点击事件
//        btnView.onClick {
//            it.snackbar("Hi there", "取消") { toast("我点击了取消") }
//        }
//
//        iv.loadUrl(this, "http://img.hb.aicdn.com/0601ba0c597f3126da59eed23938c6ba8770d6771d0d5-F0veQO_fw658")
//        iv2.loadUrlHigh(this, "http://img.hb.aicdn.com/0601ba0c597f3126da59eed23938c6ba8770d6771d0d5-F0veQO_fw658")
    }

    override fun onDestroy() {
        super.onDestroy()
        log.info { "tick tack activity destroy" }

    }

    private fun replaceFragments(position: Int) {
        supportFragmentManager.beginTransaction().apply {
            when (position) {
                0 -> replace(R.id.home_activity_frag_container, BlankFragment.newInstance("",""))
//                1 -> replace(R.id.home_activity_frag_container, fragment2)
//                2 -> replace(R.id.home_activity_frag_container, fragment3)
//                3 -> replace(R.id.home_activity_frag_container, fragment4)
//                4 -> replace(R.id.home_activity_frag_container, fragment5)
//                else -> replace(R.id.home_activity_frag_container, fragment6)
            }
        }.commitAllowingStateLoss()
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
