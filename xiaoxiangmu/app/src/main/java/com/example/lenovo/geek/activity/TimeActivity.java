package com.example.lenovo.geek.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.geek.R;

public class TimeActivity extends AppCompatActivity {

    private ImageView mIv;
    private Toolbar mToolbar;
    private CalendarView mCalendarview;
    /**
     * 确定
     */
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        initView();
    }

    private void initView() {
        mIv = (ImageView) findViewById(R.id.iv);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mCalendarview = (CalendarView) findViewById(R.id.calendarview);
        mTv = (TextView) findViewById(R.id.tv);
        mCalendarview.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                int months = month + 1;
                Toast.makeText(TimeActivity.this,
                        year + "年" + months + "月" + dayOfMonth + "日",
                        Toast.LENGTH_SHORT).show();
            }
        });

        mCalendarview.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                int months = month + 1;
                Toast.makeText(TimeActivity.this,
                        year + "年" + months + "月" + dayOfMonth + "日",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}
