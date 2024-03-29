package com.example.carlosantonio.myapplication;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SlidingDrawer;

/**
 * Created by Carlos Antonio on 09/11/2015.
 */
public class Slider extends Activity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener, SlidingDrawer.OnDrawerOpenListener {

    SlidingDrawer sd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sliding);
        Button handle1 = (Button) findViewById(R.id.handle1);
        Button handle2 = (Button) findViewById(R.id.handle2);
        Button handle3 = (Button) findViewById(R.id.handle3);
        Button handle4 = (Button) findViewById(R.id.handle4);
        CheckBox checkbox = (CheckBox) findViewById(R.id.cbSlidable);
        checkbox.setOnCheckedChangeListener(this);
        sd = (SlidingDrawer) findViewById(R.id.slidingD);
        sd.setOnDrawerOpenListener(this);
        handle1.setOnClickListener(this);
        handle2.setOnClickListener(this);
        handle3.setOnClickListener(this);
        handle4.setOnClickListener(this);
    }

    @Override
    public void onDrawerOpened() {
        //MediaPlayer mp = MediaPlayer.create(this, R.raw.explosion);
       // mp.start();
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (compoundButton.isChecked()){
            sd.lock();
        }else{
            sd.unlock();
        }
    }

    @Override
    public void onClick(View view) {

        switch(view.getId()){
            case R.id.handle1:
                sd.open();
                break;
            case R.id.handle2:

                break;
            case R.id.handle3:
                sd.toggle();
                break;
            case R.id.handle4:
                sd.close();
                break;
        }
    }
}
