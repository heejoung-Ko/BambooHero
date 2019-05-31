package com.example.bamboohero.Game.Tile;

import android.graphics.Canvas;

import com.example.bamboohero.Game.AppManager;
import com.example.bamboohero.framework.GraphicObject;

public class SumTile extends Tile {
    public GraphicObject symbol;

    public SumTile(int x, int y, int c) {
        super(x, y);
        coefficient = c;
    }

    public void draw(Canvas canvas){
        super.draw(canvas);
        int x = posX_inTileMap * 300;
        int y = posY_inTileMap * 300 + 300;
        canvas.drawText("+" + coefficient, x, y, p);
    }

    void Effect(){
        int atk = AppManager.getInstance().getM_dungeon().player.getAtk();
        atk += coefficient;
        AppManager.getInstance().getM_dungeon().player.setATk(atk);
        super.colorEffect();
    }
}
