package com.example.carlosantonio.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import java.util.Calendar;

/**
 * Created by Carlos Antonio on 19/11/2015.
 */
public class CalendarioEvento extends Activity implements View.OnClickListener {

    View c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendario);
        c = findViewById(R.id.calendarView);
        c.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

    }
}
