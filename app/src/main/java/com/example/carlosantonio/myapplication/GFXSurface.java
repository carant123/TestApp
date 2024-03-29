package com.example.carlosantonio.myapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by Carlos Antonio on 26/10/2015.
 */
public class GFXSurface extends Activity implements View.OnTouchListener{

    MyBringBackSurface ourSurfaceView;
    float x,y,sX,sY,fX,fY;
    float dX,dY,aniX,aniY,scaledX,scaledY;
    Bitmap test, plus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ourSurfaceView = new MyBringBackSurface(this);
        ourSurfaceView.setOnTouchListener(this);
        x = 0;
        y = 0;
        sX = 0;
        sY = 0;
        fX = 0;
        fY = 0;
        dX = dY = aniX = aniY = scaledX = scaledY = 0;
        test = BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher);
        plus = BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher);
        setContentView(ourSurfaceView);

    }

    @Override
    protected void onPause() {
        super.onPause();
        ourSurfaceView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ourSurfaceView.resume();
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        x = event.getX();
        y = event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                sX = event.getX();
                sY = event.getY();
                dX = dY = aniX = aniY = scaledX = scaledY = 0;
                break;
            case MotionEvent.ACTION_UP:
                fX = event.getX();
                fY = event.getY();
                dX = fX - sX;
                dY = fY - sY;
                scaledX = dX/30;
                scaledY = dY/30;
                x = y = 0;
                break;
        }

        return true;
    }

    public class MyBringBackSurface extends SurfaceView implements Runnable{

        SurfaceHolder ourholder;
        Thread ourThread = null;
        boolean isRunning = false;

        public MyBringBackSurface(Context context) {
            super(context);
            ourholder = getHolder();
        }

        public void pause(){
            isRunning = false;
            while(true){
                try {
                    ourThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
            ourThread = null;
        }

        public void resume(){
            isRunning = true;
            ourThread = new Thread(this);
            ourThread.start();
        }

        @Override
        public void run() {
            while(isRunning){
                if(!ourholder.getSurface().isValid())
                    continue;

                Canvas canvas = ourholder.lockCanvas();
                canvas.drawRGB(02, 02, 150);
                if ( x != 0 && y != 0){
                    canvas.drawBitmap(test,x-(test.getWidth()/2),y-(test.getHeight()/2),null);
                }
                if ( sX != 0 && sY != 0){
                    canvas.drawBitmap(plus,sX-(plus.getWidth()/2),sY-(plus.getHeight()/2),null);
                }
                if ( fX != 0 && fY != 0){
                    canvas.drawBitmap(test,x-(test.getWidth()/2)-aniX,y-(test.getHeight()/2)-aniY,null);
                    canvas.drawBitmap(plus,fX-(plus.getWidth()/2),fY-(plus.getHeight()/2),null);
                }

                aniX = aniX + scaledX;
                aniY = aniY + scaledY;

                ourholder.unlockCanvasAndPost(canvas);

            }

        }
    }
}
