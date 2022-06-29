package com.example.myapp06;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Chronometer chronometer;
    CalendarView calendarView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chronometer = (Chronometer) findViewById(R.id.chronometer);
        calendarView = (CalendarView) findViewById(R.id.calendar);
        textView = (TextView) findViewById(R.id.tv);

        Date now_date = new Date(System.currentTimeMillis());
        SimpleDateFormat date_year = new SimpleDateFormat("YYYY");
        SimpleDateFormat date_month = new SimpleDateFormat("MM");
        SimpleDateFormat date_day = new SimpleDateFormat("dd");
        String final_date = "year: " + date_year.format(now_date) + ", month: "
                + date_month.format(now_date) + ", day: " + date_day.format(now_date);
        textView.setText(final_date);


        CalendarView.OnDateChangeListener mDateChangeListener = (calendarView1, i, i1, i2) -> {
            textView.setText(new String().format("year: %d, month: %d, day: %d", i, i1+1, i2));
        };

        calendarView.setOnDateChangeListener(mDateChangeListener);
    }
    public void chrono_start_click(View v){
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.setTextColor(Color.rgb(0,0,0));
        chronometer.start();
    }

    public void chrono_stop_click(View v){
        chronometer.stop();
        chronometer.setTextColor(Color.rgb(255,0,0));
    }
}