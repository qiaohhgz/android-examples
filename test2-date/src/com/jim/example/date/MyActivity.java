package com.jim.example.date;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.*;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    private static final int SHOW_DATAPICK = 0;
    private static final int DATE_DIALOG_ID = 1;
    private static final int SHOW_TIMEPICK = 2;
    private static final int TIME_DIALOG_ID = 3;

    private EditText showDate = null;
    private Button pickDate = null;
    private EditText showTime = null;
    private Button pickTime = null;

    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMinute;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        initializeViews();

        setDateTime();
        setTimeOfDay();
    }

    /**
     * 初始化控件和UI视图
     */
    private void initializeViews() {
        showDate = (EditText) findViewById(R.id.showDate);
        pickDate = (Button) findViewById(R.id.pickDate);
        showTime = (EditText) findViewById(R.id.showTime);
        pickTime = (Button) findViewById(R.id.pickTime);

        pickDate.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message msg = new Message();
                msg.what = SHOW_DATAPICK;
                dateAndTimeHandler.sendMessage(msg);
            }
        });

        pickTime.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message msg = new Message();
                msg.what = SHOW_TIMEPICK;
                dateAndTimeHandler.sendMessage(msg);
            }
        });

        Button btn = (Button) findViewById(R.id.myBtn);
        btn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView txt = (TextView) findViewById(R.id.myTxt);
                txt.setText(DateFormat.getDateTimeInstance().format(new Date()));
            }
        });
    }

    /**
     * 设置日期
     */
    private void setDateTime() {
        final Calendar c = Calendar.getInstance();

        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        updateDateDisplay();
    }

    /**
     * 更新日期显示
     */
    private void updateDateDisplay() {
        showDate.setText(new StringBuilder().append(mYear).append("-")
                .append((mMonth + 1) < 10 ? "0" + (mMonth + 1) : (mMonth + 1)).append("-")
                .append((mDay < 10) ? "0" + mDay : mDay));
    }

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            updateDateDisplay();
        }
    };

    /**
     * 设置时间
     */
    private void setTimeOfDay() {
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        updateTimeDisplay();
    }

    /**
     * 更新时间显示
     */
    private void updateTimeDisplay() {
        showTime.setText(new StringBuilder().append(mHour).append(":")
                .append((mMinute < 10) ? "0" + mMinute : mMinute));
    }


    private TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
            mHour = hourOfDay;
            mMinute = minute;
            updateTimeDisplay();
        }
    };

    protected Dialog onCreateDialog(int id) {
        System.out.println("on create dialog");
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, dateSetListener, mYear, mMonth, mDay);
            case TIME_DIALOG_ID:
                return new TimePickerDialog(this, timeSetListener, mHour, mMinute, true);
        }
        return null;
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        System.out.println("on prepare dialog");
        switch (id) {
            case DATE_DIALOG_ID:
                ((DatePickerDialog) dialog).updateDate(mYear, mMonth, mDay);
                break;
            case TIME_DIALOG_ID:
                ((TimePickerDialog) dialog).updateTime(mHour, mMinute);
                break;
        }
    }

    /**
     * 处理日期和时间控件的Handler
     */
    Handler dateAndTimeHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_DATAPICK:
                    showDialog(DATE_DIALOG_ID);
                    break;
                case SHOW_TIMEPICK:
                    showDialog(TIME_DIALOG_ID);
                    break;
            }
        }
    };

}
