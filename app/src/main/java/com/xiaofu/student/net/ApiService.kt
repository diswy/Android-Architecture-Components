package com.xiaofu.student.net

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("boxoffice/day/query")
    fun getMovie(@Query("key") key: String = "28d0461b30a6d", @Query("area") area: String = "CN"): Call<movie>
}