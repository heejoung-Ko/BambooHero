package com.example.bamboohero.Game.Tile;

import android.app.AppComponentFactory;
import android.graphics.Bitmap;

import com.example.bamboohero.Game.AppManager;

public class SumTile extends Tile {
    public SumTile(Bitmap bitmap) {
        super(bitmap);
        coefficient =  AppManager.getInstance().getM_dungeon().tileMap.getSumCoefficient();
    }

    void Effect(){
        int atk = AppManager.getInstance().getM_dungeon().player.getAtk();
        atk += coefficient;
        AppManager.getInstance().getM_dungeon().player.setATk(atk);
    }

    void colorEffect(){

    }
}
