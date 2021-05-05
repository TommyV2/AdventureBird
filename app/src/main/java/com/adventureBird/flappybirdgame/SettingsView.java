package com.adventureBird.flappybirdgame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;


public class SettingsView extends View {

    Handler handler;
    Runnable runnable;
    Bitmap[] birds;
    Bitmap background;
    Bitmap[] maps;
    Bitmap topColumn, bottomColumn;
    Bitmap mapChangeButton;
    Bitmap birdChangeButton;
    Bitmap saveButton;
    Bitmap volumeIcon;
    Bitmap[] modes;
    Bitmap[] difficulties;
    Bitmap[] diffBars;
    Bitmap[] volumeBars;


    Display display;
    Point point;
    int dWidth, dHeight;
    final int UPDATE_TIME = 1;
    Rect rect,mapRect,birdRect;

    View startView;

    int gap = 400;
    int minColumn, maxColumn;
    int gameMode;
    int currentMapIndex;
    int currentBirdIndex;
    int currentModeIndex;
    int currentDiffIndex;
    int currentVolumeIndex;

    Context context;
    Intent intent;
    Random random;

    public SettingsView(Context context, int currentBirdIndex, int currentMapIndex, int currentDiffIndex, int currentModeIndex,int currentVolumeIndex) {

        super(context);
        this.context = context;
        intent = new Intent(context, MainActivity.class);
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };
        this.gameMode=gameMode;
        if(context!=null) {
            startView = (View) findViewById(R.id.imageView);

            maps = new Bitmap[6];
            birds = new Bitmap[3];
            modes = new Bitmap[3];
            volumeBars = new Bitmap[7];
            difficulties = new Bitmap[3];
            diffBars= new Bitmap[3];
            this.currentBirdIndex=currentBirdIndex;
            this.currentMapIndex=currentMapIndex;
            this.currentDiffIndex=currentDiffIndex;
            this.currentModeIndex=currentModeIndex;
            this.currentVolumeIndex=currentVolumeIndex;

            background = BitmapFactory.decodeResource(getResources()
                    , R.drawable.background_settings);
            maps[0] = BitmapFactory.decodeResource(getResources()
                    , R.drawable.background_classic);
            maps[1] = BitmapFactory.decodeResource(getResources()
                    , R.drawable.background_night);
            maps[2] = BitmapFactory.decodeResource(getResources()
                    , R.drawable.background_city);
            maps[3] = BitmapFactory.decodeResource(getResources()
                    , R.drawable.background_forest);
            maps[4] = BitmapFactory.decodeResource(getResources()
                    , R.drawable.background_future);
            maps[5] = BitmapFactory.decodeResource(getResources()
                    , R.drawable.background_inferno);
            birds[0] = BitmapFactory.decodeResource(getResources()
                    , R.drawable.bird);
            birds[1] = BitmapFactory.decodeResource(getResources()
                    , R.drawable.pigeon);
            birds[2] = BitmapFactory.decodeResource(getResources()
                    , R.drawable.valentine);
            difficulties[0] = BitmapFactory.decodeResource(getResources()
                    , R.drawable.easy);
            difficulties[1] = BitmapFactory.decodeResource(getResources()
                    , R.drawable.normal);
            difficulties[2] = BitmapFactory.decodeResource(getResources()
                    , R.drawable.hard);
            diffBars[0] = BitmapFactory.decodeResource(getResources()
                    , R.drawable.easybar);
            diffBars[1] = BitmapFactory.decodeResource(getResources()
                    , R.drawable.normalbar);
            diffBars[2] = BitmapFactory.decodeResource(getResources()
                    , R.drawable.hardbar);
            modes[0] = BitmapFactory.decodeResource(getResources()
                    , R.drawable.classic);
            modes[1] = BitmapFactory.decodeResource(getResources()
                    , R.drawable.adventure);
            modes[2] = BitmapFactory.decodeResource(getResources()
                    , R.drawable.explore);


            mapChangeButton =BitmapFactory.decodeResource(getResources()
                    , R.drawable.next);
            birdChangeButton =BitmapFactory.decodeResource(getResources()
                    , R.drawable.next);
            saveButton =BitmapFactory.decodeResource(getResources()
                    , R.drawable.save);

            volumeBars[0] =  BitmapFactory.decodeResource(getResources()
                    , R.drawable.vol0);
            volumeBars[1] =  BitmapFactory.decodeResource(getResources()
                    , R.drawable.vol20);
            volumeBars[2] =  BitmapFactory.decodeResource(getResources()
                    , R.drawable.vol40);
            volumeBars[3] =  BitmapFactory.decodeResource(getResources()
                    , R.drawable.vol50);
            volumeBars[4] =  BitmapFactory.decodeResource(getResources()
                    , R.drawable.vol60);
            volumeBars[5] =  BitmapFactory.decodeResource(getResources()
                    , R.drawable.vol80);
            volumeBars[6] =  BitmapFactory.decodeResource(getResources()
                    , R.drawable.vol100);
            volumeIcon = BitmapFactory.decodeResource(getResources()
                    , R.drawable.sound);


            display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
            point = new Point();
            display.getSize(point);
            dWidth = point.x;
            dHeight = point.y;
            rect = new Rect(0, 0, dWidth, dHeight);
            mapChangeButton = Bitmap.createScaledBitmap(mapChangeButton, (int)(dWidth*0.3), (int)(dWidth*0.2), false);
            birdChangeButton = Bitmap.createScaledBitmap(birdChangeButton, (int)(dWidth*0.3), (int)(dWidth*0.2), false);
            saveButton = Bitmap.createScaledBitmap(saveButton, (int)(dWidth*0.3), (int)(dWidth*0.2), false);
            volumeIcon = Bitmap.createScaledBitmap(volumeIcon, (int)(dWidth*0.3), (int)(dWidth*0.2), false);
            difficulties[0]= Bitmap.createScaledBitmap(difficulties[0], (int)(dWidth*0.3), (int)(dWidth*0.2), false);
            difficulties[1]= Bitmap.createScaledBitmap(difficulties[1], (int)(dWidth*0.3), (int)(dWidth*0.2), false);
            difficulties[2]= Bitmap.createScaledBitmap(difficulties[2], (int)(dWidth*0.3), (int)(dWidth*0.2), false);
            diffBars[0]= Bitmap.createScaledBitmap(diffBars[0], (int)(dWidth*0.3), (int)(dWidth*0.2), false);
            diffBars[1]= Bitmap.createScaledBitmap(diffBars[1], (int)(dWidth*0.3), (int)(dWidth*0.2), false);
            diffBars[2]= Bitmap.createScaledBitmap(diffBars[2], (int)(dWidth*0.3), (int)(dWidth*0.2), false);
            modes[0]= Bitmap.createScaledBitmap(modes[0], (int)(dWidth*0.3), (int)(dWidth*0.2), false);
            modes[1]= Bitmap.createScaledBitmap(modes[1], (int)(dWidth*0.3), (int)(dWidth*0.2), false);
            modes[2]= Bitmap.createScaledBitmap(modes[2], (int)(dWidth*0.3), (int)(dWidth*0.2), false);
            volumeBars[0] = Bitmap.createScaledBitmap(volumeBars[0], (int)(dWidth*0.3), (int)(dWidth*0.2), false);
            volumeBars[1] = Bitmap.createScaledBitmap(volumeBars[1], (int)(dWidth*0.3), (int)(dWidth*0.2), false);
            volumeBars[2] = Bitmap.createScaledBitmap(volumeBars[2], (int)(dWidth*0.3), (int)(dWidth*0.2), false);
            volumeBars[3] = Bitmap.createScaledBitmap(volumeBars[3], (int)(dWidth*0.3), (int)(dWidth*0.2), false);
            volumeBars[4] = Bitmap.createScaledBitmap(volumeBars[4], (int)(dWidth*0.3), (int)(dWidth*0.2), false);
            volumeBars[5] = Bitmap.createScaledBitmap(volumeBars[5], (int)(dWidth*0.3), (int)(dWidth*0.2), false);
            volumeBars[6] = Bitmap.createScaledBitmap(volumeBars[6], (int)(dWidth*0.3), (int)(dWidth*0.2), false);
            mapRect = new Rect((int)(dWidth*0.1), 100, (int)(dWidth*0.1)+400, 600);
            birdRect = new Rect(dWidth-500, 100, dWidth-100, 450);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(background, null, rect, null);
        canvas.drawBitmap(maps[currentMapIndex], null, mapRect, null);
        canvas.drawBitmap(birds[currentBirdIndex], null, birdRect, null);
        canvas.drawBitmap(mapChangeButton, (int)(dWidth*0.1), 600, null);
        canvas.drawBitmap(birdChangeButton, dWidth-500, 600, null);
        canvas.drawBitmap(saveButton, dWidth-500, (int)(dHeight*0.8), null);
        canvas.drawBitmap(difficulties[currentDiffIndex], (int)(dWidth*0.1), 650 + 2*birdChangeButton.getHeight(), null);
        canvas.drawBitmap(diffBars[currentDiffIndex], (int)(dWidth*0.1), 650 + birdChangeButton.getHeight(), null);
        canvas.drawBitmap(modes[currentModeIndex], (int)(dWidth*0.1), (int)(dHeight*0.8), null);
        canvas.drawBitmap(volumeIcon, dWidth-500, 650 + 2*birdChangeButton.getHeight(), null);
        canvas.drawBitmap(volumeBars[currentVolumeIndex], dWidth-500, 650 + birdChangeButton.getHeight(), null);
        handler.postDelayed(runnable, UPDATE_TIME);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:

                if( x > (int)(dWidth*0.1) && x < (int)(dWidth*0.1) + mapChangeButton.getWidth() && y > 600 && y < 600 + mapChangeButton.getHeight() )
                {
                    MainActivity.clickSound.start();
                    currentMapIndex++;
                            if(currentMapIndex>5){
                                currentMapIndex=0;
                            }
                }
                if( x > dWidth-500 && x < dWidth-500 + birdChangeButton.getWidth() && y > 600 && y < 600 + birdChangeButton.getHeight() )
                {
                    MainActivity.clickSound.start();
                    currentBirdIndex++;
                    if(currentBirdIndex>2){
                        currentBirdIndex=0;
                    }
                }
                if( x > (int)(dWidth*0.1) && x < (int)(dWidth*0.1) + birdChangeButton.getWidth() && y > 650 + 2*birdChangeButton.getHeight() && y < 650 + 3*birdChangeButton.getHeight()  )
                {
                    MainActivity.clickSound.start();
                    currentDiffIndex++;
                    if(currentDiffIndex>2){
                        currentDiffIndex=0;
                    }
                }
                if( x > dWidth-500 && x < dWidth-500 + birdChangeButton.getWidth() && y > 650 + 2*birdChangeButton.getHeight() && y < 650 + 3*birdChangeButton.getHeight() )
                {
                    MainActivity.clickSound.start();
                    currentVolumeIndex++;
                    if(currentVolumeIndex>6){
                        currentVolumeIndex=0;
                    }
                }
                if( x > (int)(dWidth*0.1) && x < (int)(dWidth*0.1) + birdChangeButton.getWidth() && y > (int)(dHeight*0.8)  && y < (int)(dHeight*0.8) + birdChangeButton.getHeight() )
                {
                    MainActivity.clickSound.start();
                    currentModeIndex++;
                    if(currentModeIndex>2){
                        currentModeIndex=0;
                    }
                }


                if( x > dWidth-500 && x < dWidth-500 + saveButton.getWidth() && y > (int)(dHeight*0.8) && y < (int)(dHeight*0.8) + saveButton.getHeight() )
                {
                    MainActivity.clickSound.start();
                    MainActivity.birdModel = getBird();
                    MainActivity.mapModel = getMap();
                    MainActivity.currentBirdIndex = currentBirdIndex;
                    MainActivity.currentMapIndex = currentMapIndex;
                    MainActivity.currentModeIndex = currentModeIndex;
                    MainActivity.currentDiffIndex = currentDiffIndex;
                    MainActivity.currentVolumeIndex = currentVolumeIndex;
                    MainActivity.resid = getResid();
                    ((Activity) context).startActivity(intent);
                }
                return true;
        }
        return false;
    }

    public String getMap(){
        switch(currentMapIndex) {
            case 0:
                return "classic";
            case 1:
                return "night";
            case 2:
                return "city";
            case 3:
                return "forest";
            case 4:
                return "future";
            case 5:
                return "inferno";
        }
        return "classic";
    }

    public String getBird(){
        switch(currentBirdIndex) {
            case 0:
                return "bird";
            case 1:
                return "pigeon";
            case 2:
                return "valentine";
        }
        return "bird";
    }
    public int getDifficulty(){
        return currentDiffIndex;
    }

    public int getMode(){
        return currentModeIndex;
    }

    public int getResid(){
        switch(currentMapIndex) {
            case 0:
                return R.drawable.background_classic;
            case 1:
                return R.drawable.background_night;
            case 2:
                return R.drawable.background_city;
            case 3:
                return R.drawable.background_forest;
            case 4:
                return R.drawable.background_future;
            case 5:
                return R.drawable.background_inferno;
        }
        return R.drawable.background_classic;
    }

}
