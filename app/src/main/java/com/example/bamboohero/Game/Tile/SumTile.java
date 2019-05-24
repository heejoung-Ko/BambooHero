package com.example.bamboohero.Game.Tile;

import com.example.bamboohero.Game.AppManager;
import com.example.bamboohero.framework.GraphicObject;

public class SumTile extends Tile {
    public GraphicObject symbol;

    public SumTile( ) {

        coefficient =  AppManager.getInstance().getM_dungeon().tileMap.getSumCoefficient();
    }

    void Effect(){
        int atk = AppManager.getInstance().getM_dungeon().player.getAtk();
        atk += coefficient;
        AppManager.getInstance().getM_dungeon().player.setATk(atk);
        super.colorEffect();
    }
}
