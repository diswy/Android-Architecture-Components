package com.xiaofu.student


import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.alibaba.android.arouter.launcher.ARouter
import com.xiaofu.lib.base.fragment.BaseFragment
import com.xiaofu.lib.base.http.HttpManager
import kotlinx.android.synthetic.main.fragment_blank.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.anko.info
import org.jetbrains.anko.warn


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BlankFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class BlankFragment : BaseFragment() {

    override fun getLayoutRes(): Int = R.layout.fragment_blank

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        info("->：onCreate")
        log.warn { "frag frag:${ARouter.getInstance().build("/ebd/student/user").navigation() as Fragment}" }

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BlankFragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                BlankFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }

    override fun initialize(activity: FragmentActivity) {

        val a = ViewModelProviders.of(activity).get(UserViewModel::class.java)
        log.warn { "frag:$a" }
        a.getUser().observe(this, Observer {
            tvName.text = it.name
        })
        btnChange.setOnClickListener { a.modifyName2() }
//        launch {
//            for (i in 1..50){
//                log.info { "tick tack fragment:$i" }
//                delay(1000L)
//            }
//        }

        info { "abc???zhenshi Fragment=${HttpManager.INSTANCE.retrofit}" }


    }

    override fun bindListener() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
//        log.info { "tick tack fragment!!! destroy" }
    }
}
