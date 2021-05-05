package com.adventureBird.flappybirdgame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import com.adventureBird.flappybirdgame.R;

import java.util.Arrays;

public class HighScoreView extends View {

    Context context;
    Cursor res;
    Intent intent;
    Handler handler;
    Runnable runnable;
    MainActivity main;
    Bitmap background, returnButton, resetButton;
    Bitmap zero, one, two, three, four, five, six, seven, eight, nine;
    Bitmap digit;
    Rect rect;
    Display display;
    Point point;
    final int UPDATE_TIME = 1;
    int dWidth;
    int dHeight;
    int scoreX,scoreY;
    String [] highScoreArray;

    public HighScoreView(Context context) {

        super(context);
        this.context = context;
        intent = new Intent(context, MainActivity.class);
        main = new MainActivity();
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };
        background = BitmapFactory.decodeResource(getResources()
                , R.drawable.background_settings);
        returnButton = BitmapFactory.decodeResource(getResources()
                , R.drawable.returnbtn);
        resetButton = BitmapFactory.decodeResource(getResources()
                , R.drawable.reset);
        highScoreArray = new String[5];
        display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
        point = new Point();
        display.getSize(point);
        dWidth = point.x;
        dHeight = point.y;
        rect = new Rect(0, 0, dWidth, dHeight);
        returnButton = Bitmap.createScaledBitmap(returnButton, (int)(dWidth*0.3), (int)(dWidth*0.2), false);
        resetButton = Bitmap.createScaledBitmap(resetButton, (int)(dWidth*0.3), (int)(dWidth*0.2), false);
        zero = BitmapFactory.decodeResource(getResources(), R.drawable.zero);
        one = BitmapFactory.decodeResource(getResources(), R.drawable.one);
        two = BitmapFactory.decodeResource(getResources(), R.drawable.two);
        three = BitmapFactory.decodeResource(getResources(), R.drawable.three);
        four = BitmapFactory.decodeResource(getResources(), R.drawable.four);
        five = BitmapFactory.decodeResource(getResources(), R.drawable.five);
        six = BitmapFactory.decodeResource(getResources(), R.drawable.six);
        seven = BitmapFactory.decodeResource(getResources(), R.drawable.seven);
        eight = BitmapFactory.decodeResource(getResources(), R.drawable.eight);
        nine = BitmapFactory.decodeResource(getResources(), R.drawable.nine);

        zero = Bitmap.createScaledBitmap(zero, 80, 110, false);
        one = Bitmap.createScaledBitmap(one, 80, 110, false);
        two = Bitmap.createScaledBitmap(two, 80, 110, false);
        three = Bitmap.createScaledBitmap(three, 80, 110, false);
        four = Bitmap.createScaledBitmap(four, 80, 110, false);
        five = Bitmap.createScaledBitmap(five, 80, 110, false);
        six = Bitmap.createScaledBitmap(six, 80, 110, false);
        seven = Bitmap.createScaledBitmap(seven, 80, 110, false);
        eight = Bitmap.createScaledBitmap(eight, 80, 110, false);
        nine = Bitmap.createScaledBitmap(nine, 80, 110, false);
        res = MainActivity.mydata.getAllData();


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(background, null, rect, null);
        drawHighScoreArray(canvas);
        canvas.drawBitmap(returnButton, (int)(dWidth*0.6), (int)(dHeight*0.8), null);
        canvas.drawBitmap(resetButton, (int)(dWidth*0.2), (int)(dHeight*0.8), null);
        handler.postDelayed(runnable, UPDATE_TIME);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:

                if( x > (int)(dWidth*0.6) && x < (int)(dWidth*0.6) + returnButton.getWidth() && y > (int)(dHeight*0.8) && y < (int)(dHeight*0.8) + returnButton.getHeight() )
                {
                    ((Activity) context).startActivity(intent);
                }
                if( x > (int)(dWidth*0.2) && x < (int)(dWidth*0.2) + resetButton.getWidth() && y > (int)(dHeight*0.8) && y < (int)(dHeight*0.8) + resetButton.getHeight() )
                {
                    main.clearTable();
                    res = MainActivity.mydata.getAllData();
                }
                return true;
        }
        return false;
    }

    public void drawHighScoreArray(Canvas canvas){
        res.moveToFirst();
        Arrays.fill(highScoreArray, "0");
        for(int i=0;i<5;i++){
            highScoreArray[i] = res.getString(1);
            res.moveToNext();
        }
        for(int i=0;i<5;i++){
            scoreY = dHeight * 1 / 8+ i*(zero.getHeight()+10);
            drawScore(canvas, highScoreArray[i],scoreY);
        }
    }

    public void drawScore(Canvas canvas, String score , int scoreY) {
        digit = zero;
        int numberOfDigits = score.length();
        scoreX = dWidth / 4 - numberOfDigits * zero.getWidth() / 2;
        for (int i = 0; i < numberOfDigits; i++) {
            switch (score.charAt(i)) {
                case '0':
                    digit = zero;
                    break;
                case '1':
                    digit = one;
                    break;
                case '2':
                    digit = two;
                    break;
                case '3':
                    digit = three;
                    break;
                case '4':
                    digit = four;
                    break;
                case '5':
                    digit = five;
                    break;
                case '6':
                    digit = six;
                    break;
                case '7':
                    digit = seven;
                    break;
                case '8':
                    digit = eight;
                    break;
                case '9':
                    digit = nine;
                    break;
            }
            canvas.drawBitmap(digit, scoreX + i * zero.getWidth(), scoreY, null);
        }
    }
}
