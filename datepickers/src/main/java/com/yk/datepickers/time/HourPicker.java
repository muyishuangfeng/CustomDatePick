package com.yk.datepickers.time;

import android.content.Context;
import android.util.AttributeSet;


import com.yk.datepickers.WheelPicker;
import com.yk.datepickers.impl.OnHourSelectedListener;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 小时选择
 */
public class HourPicker extends WheelPicker<Integer> {

    private OnHourSelectedListener mOnHourSelectedListener;

    public HourPicker(Context context) {
        this(context, null);
    }

    public HourPicker(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HourPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setItemMaximumWidthText("00");
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMinimumIntegerDigits(2);
        setDataFormat(numberFormat);
        updateHour();
        setOnWheelChangeListener(new OnWheelChangeListener<Integer>() {
            @Override
            public void onWheelSelected(Integer item, int position) {
                if (mOnHourSelectedListener != null) {
                    mOnHourSelectedListener.onHourSelected(item);
                }
            }
        });
    }

    private void updateHour() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            list.add(i);
        }
        setDataList(list);
    }

    public void setSelectedHour(int hour) {
        setSelectedHour(hour, true);
    }

    public void setSelectedHour(int hour, boolean smootScroll) {
        setCurrentPosition(hour, smootScroll);
    }

    public void setOnHourSelectedListener(OnHourSelectedListener onHourSelectedListener) {
        mOnHourSelectedListener = onHourSelectedListener;
    }


}
