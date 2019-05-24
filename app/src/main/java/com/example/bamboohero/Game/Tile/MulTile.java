package com.example.bamboohero.Game.Tile;

import com.example.bamboohero.Game.AppManager;

public class MulTile extends Tile {
    public MulTile() {
        coefficient =  AppManager.getInstance().getM_dungeon().tileMap.getMulCoefficient();
    }
    public void Effect(){
        int atk = AppManager.getInstance().getM_dungeon().player.getAtk();
        atk *= coefficient;
        AppManager.getInstance().getM_dungeon().player.setATk(atk);
        super.colorEffect();
    }
}
