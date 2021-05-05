package com.adventureBird.flappybirdgame;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class HighScore extends Activity {

    HighScoreView highScoreView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        highScoreView = new HighScoreView(this);
        setContentView(highScoreView);


    }

}