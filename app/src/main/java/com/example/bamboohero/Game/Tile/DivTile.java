package com.example.bamboohero.Game.Tile;

import android.graphics.Bitmap;

import com.example.bamboohero.Game.AppManager;

public class DivTile extends Tile {
    public DivTile() {
        coefficient =  AppManager.getInstance().getM_dungeon().tileMap.getMulCoefficient();
    }

    public void Effect(){
        int atk = AppManager.getInstance().getM_dungeon().player.getAtk();
        atk /= coefficient;
        AppManager.getInstance().getM_dungeon().player.setATk(atk);
        super.colorEffect();
    }
}
