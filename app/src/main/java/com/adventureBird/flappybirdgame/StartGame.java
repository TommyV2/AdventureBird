package com.adventureBird.flappybirdgame;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class StartGame extends Activity {

    GameView gameView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameView = new GameView(this, MainActivity.currentDiffIndex,  MainActivity.currentModeIndex, MainActivity.birdModel, MainActivity.mapModel, MainActivity.currentVolumeIndex);
        setContentView(gameView);

    }
}
