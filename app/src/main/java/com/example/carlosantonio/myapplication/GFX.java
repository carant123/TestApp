package com.example.carlosantonio.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;

/**
 * Created by Carlos Antonio on 26/10/2015.
 */
public class GFX extends Activity {

    MyBringBack ourView;
    PowerManager.WakeLock wl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //wake looc

        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK,"whatever");

        super.onCreate(savedInstanceState);
        wl.acquire();
        ourView = new MyBringBack(this);
        setContentView(ourView);



    }

    @Override
    protected void onPause() {
        super.onPause();
        wl.release();
    }

}
