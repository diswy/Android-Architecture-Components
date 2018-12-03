package com.xiaofu.student

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {
    private val user = MutableLiveData<User>()

    init {
        user.value = User("默认姓名",18)
    }

    fun getUser():LiveData<User>{
        return user
    }

    fun modifyName(){
        user.value = User("修改后的名字",12)

    }

    fun modifyName2(){
        user.value = User("什么鬼",1111)
    }
}