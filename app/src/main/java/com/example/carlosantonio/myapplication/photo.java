package com.example.carlosantonio.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Carlos Antonio on 20/10/2015.
 */
public class photo extends Activity implements View.OnClickListener{

    ImageButton ib;
    Button b;
    ImageView iv;
    Intent i;
    final static int cameraData = 0;
    Bitmap bmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.captura_camara_layout);
        initialize();
        InputStream is = getResources().openRawResource(R.drawable.ic_launcher);
        bmp = BitmapFactory.decodeStream(is);
        //android:screenOrientation="portrait" evita que cambie de orientacion en las actividades android manifest
    }

    private void initialize(){
        iv = (ImageView) findViewById(R.id.ivReturnedPic);
        ib = (ImageButton) findViewById(R.id.ibTakePic);
        b = (Button) findViewById(R.id.bSetWall);
        b.setOnClickListener(this);
        ib.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bSetWall:
                try {
                    getApplicationContext().setWallpaper(bmp);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.ibTakePic:
               i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
               startActivityForResult(i,cameraData);
                break;


        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){

            //Instancia para almacenar los datos
            Bundle extras = data.getExtras();
            bmp = (Bitmap) extras.get("data");
            iv.setImageBitmap(bmp);
        }
    }
}
