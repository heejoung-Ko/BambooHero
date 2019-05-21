package com.example.bamboohero.Game.Tile;

import android.graphics.Bitmap;

import com.example.bamboohero.Game.AppManager;

public class MulTile extends Tile {
    public MulTile(Bitmap bitmap) {
        super(bitmap);
        coefficient =  AppManager.getInstance().getM_dungeon().player.getMulCoefficient();
    }
}
