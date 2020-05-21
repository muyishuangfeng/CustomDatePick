package com.yk.datepickers.date

import android.app.Dialog
import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.yk.datepickers.R
import com.yk.datepickers.date.DatePicker.TAG_ONE
import com.yk.datepickers.impl.OnDateChooseListener

/**
 * 时间选择器，弹出框
 */
class DatePickerDialogFragment : DialogFragment() {
    private var mDatePicker: DatePicker? = null
    private var mSelectedYear = -1
    private var mSelectedMonth = -1
    private var mSelectedDay = -1
    private var mOnDateChooseListener: OnDateChooseListener? = null
    private var mIsShowAnimation = true
    fun setOnDateChooseListener(onDateChooseListener: OnDateChooseListener?) {
        mOnDateChooseListener = onDateChooseListener
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_date, container)
        mDatePicker = view.findViewById(R.id.dayPicker_dialog)
        val mCancelButton =
            view.findViewById<Button>(R.id.btn_dialog_date_cancel)
        val mDecideButton =
            view.findViewById<Button>(R.id.btn_dialog_date_decide)
        mCancelButton.setOnClickListener { dismiss() }
        mDecideButton.setOnClickListener {
            if (mOnDateChooseListener != null) {
                mOnDateChooseListener!!.onDateChoose(
                    mDatePicker!!.year,
                    mDatePicker!!.month, mDatePicker!!.day
                )
            }
            dismiss()
        }
        if (mSelectedYear > 0) {
            setSelectedDate()
        }
        return view
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(
            activity!!,
            R.style.DatePickerBottomDialog
        )
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) // 设置Content前设定
        dialog.setContentView(R.layout.dialog_date)
        dialog.setCanceledOnTouchOutside(true) // 外部点击取消
        val window = dialog.window
        if (window != null) {
            if (mIsShowAnimation) {
                window.attributes.windowAnimations = R.style.DatePickerDialogAnim
            }
            val lp = window.attributes
            lp.gravity = Gravity.BOTTOM // 紧贴底部
            lp.width = WindowManager.LayoutParams.MATCH_PARENT // 宽度持平
            lp.dimAmount = 0.35f
            window.attributes = lp
            window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        }
        return dialog
    }

    /**
     * 是否显示动画
     */
    fun showAnimation(show: Boolean) {
        mIsShowAnimation = show
    }

    /**
     * 设置选择的年月日
     *
     * @param year  年份
     * @param month 月份
     * @param day   日期
     */
    fun setSelectedDate(year: Int, month: Int, day: Int) {
        mSelectedYear = year
        mSelectedMonth = month
        mSelectedDay = day
        setSelectedDate()
    }

    /**
     * 设置选择的年月日
     */
    private fun setSelectedDate() {
        if (mDatePicker != null) {
            mDatePicker!!.setDate(mSelectedYear, mSelectedMonth, mSelectedDay, false)
        }
    }
}