package com.example.bamboohero.Game.Tile;

import android.graphics.Bitmap;

import com.example.bamboohero.Game.AppManager;

public class SubTile extends Tile {
    public SubTile(Bitmap bitmap) {
        super(bitmap);
        coefficient =  AppManager.getInstance().getM_dungeon().player.getSumCoefficient();
    }
}
