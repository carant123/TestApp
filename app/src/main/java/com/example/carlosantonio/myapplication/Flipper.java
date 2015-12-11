package com.example.carlosantonio.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ViewFlipper;

/**
 * Created by Carlos Antonio on 13/11/2015.
 */
public class Flipper extends Activity implements View.OnClickListener {

    ViewFlipper flippy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flipper);
        flippy = (ViewFlipper) findViewById(R.id.viewFlipper);
        flippy.setOnClickListener(this);
        flippy.setFlipInterval(500);
        flippy.startFlipping();
    }

    @Override
    public void onClick(View view) {
        flippy.showNext();
    }
}
