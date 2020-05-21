package com.yk.datepickers.impl

/**
 * 日期选择接口
 */
interface OnDateChooseListener {
    fun onDateChoose(year: Int, month: Int, day: Int)
}