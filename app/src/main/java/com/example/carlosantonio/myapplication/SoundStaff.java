package com.example.carlosantonio.myapplication;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Carlos Antonio on 09/11/2015.
 */
public class SoundStaff extends Activity implements View.OnClickListener, View.OnLongClickListener {

    SoundPool sp;
    int explosion = 0;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = new View(this);
        v.setOnClickListener(this);
        v.setOnLongClickListener(this);
        setContentView(v);
        //das un sonido
        sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        //explosion = sp.load(this, R.raw.explosion,1);
        //mp = MediaPlayer.create(this, R.raw.backgroundmusic);

    }

    @Override
    public void onClick(View view) {

        if(explosion != 0)
        sp.play(explosion,1,1,0,0,1);
    }

    //si se mantiene apretado
    @Override
    public boolean onLongClick(View view) {
        mp.start();
        return false;
    }
}
