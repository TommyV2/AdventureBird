package com.adventureBird.flappybirdgame;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;


public class MainActivity extends Activity {

    static DataBase mydata;
    static MediaPlayer birdJumpSound,pointSound,hitSound,clickSound;
    RelativeLayout  relativeLayout;
    static String birdModel = "bird";
    static String mapModel = "classic";
    static int currentBirdIndex = 0;
    static int currentMapIndex = 0;
    static int currentDiffIndex = 0;
    static int currentModeIndex = 0;
    static int currentVolumeIndex = 2;
    static int resid = R.drawable.background_classic;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayoutID);
        relativeLayout.setBackgroundResource(resid);
        mydata = new DataBase(this);
        mydata.insertData(0);
        mydata.insertData(0);
        mydata.insertData(0);
        mydata.insertData(0);
        mydata.insertData(0);
        birdJumpSound= MediaPlayer.create(MainActivity.this,R.raw.sfx_wing2);
        pointSound= MediaPlayer.create(MainActivity.this,R.raw.sfx_point);
        hitSound= MediaPlayer.create(MainActivity.this,R.raw.hit);
        clickSound= MediaPlayer.create(MainActivity.this,R.raw.click);
    }

    public void startGame(View view) {
        Intent intent = new Intent(this, StartGame.class);
        startActivity(intent);
        finish();
    }

    public void openSettings(View view) {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
        finish();
    }

    public void openHighScore(View view) {
        Intent intent = new Intent(this, HighScore.class);
        startActivity(intent);
        finish();
    }

    public void clearTable(){
        mydata.clear();
    }



}
