package com.adventureBird.flappybirdgame;

import android.content.Context;

import org.junit.Test;

import static org.junit.Assert.*;


public class ExampleUnitTest {

    @Test
    public void initialMapTest() {

        SettingsView view = new SettingsView(null, 0, 0, 0, 0,0);
        String map = view.getMap();
        assertEquals(map, "classic");

    }
    @Test
    public void initialBirdTest() {

        SettingsView view = new SettingsView(null, 0, 0, 0, 0,0);
        String map = view.getBird();
        assertEquals(map, "bird");

    }
}