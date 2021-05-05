package com.adventureBird.flappybirdgame;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class Settings extends Activity {

    SettingsView settingsView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settingsView = new SettingsView(this, MainActivity.currentBirdIndex, MainActivity.currentMapIndex, MainActivity.currentDiffIndex,  MainActivity.currentModeIndex, MainActivity.currentVolumeIndex);
        setContentView(settingsView);


    }

}
