package com.yk.datepickers.impl

/**
 * 日期选择接口
 */
interface OnDateSelectedListener {

    fun onDateSelected(year: Int, month: Int, day: Int)
}