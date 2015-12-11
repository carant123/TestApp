package com.example.carlosantonio.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

/**
 * Created by Carlos Antonio on 26/10/2015.
 */
public class MyBringBack extends View {

    Bitmap gBall;
    float changingY;

    public MyBringBack(Context context) {
        super(context);
        gBall = BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher);
        changingY = 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);

        Paint textPaint = new Paint();
        textPaint.setARGB(50,254,10,50);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(50);
        canvas.drawText("mybringback",canvas.getWidth()/2,200,textPaint);


        canvas.drawBitmap(gBall, (canvas.getWidth() / 2), changingY, null);
        if (changingY <= canvas.getHeight()){
            changingY += 10;
        }else{
            changingY = 0;
        }

        Rect middleRect = new Rect();
        middleRect.set(0, 400, canvas.getWidth(), 550);
        Paint ourblue = new Paint();
        ourblue.setColor(Color.BLUE);
        canvas.drawRect(middleRect, ourblue);

        //volvera a iniciar el proceso onDraw
        invalidate();
    }
}
