package com.xiaofu.student.entity

data class movie(
        val msg: String,
        val result: List<Result>,
        val retCode: String
)

data class Result(
        val cur: Double,
        val days: Int,
        val name: String,
        val sum: Double
)