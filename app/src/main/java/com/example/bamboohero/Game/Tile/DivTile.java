package com.example.bamboohero.Game.Tile;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.bamboohero.Game.AppManager;

public class DivTile extends Tile {
    public DivTile(int x, int y, int c) {
        super(x, y);
        coefficient = c;
    }

    public void draw(Canvas canvas){
        super.draw(canvas);
        int x = posX_inTileMap * 300;
        int y = posY_inTileMap * 300 + 300;
        canvas.drawText("/" + coefficient, posX_inTileMap, posY_inTileMap, p);
    }

    public void Effect(){
        int atk = AppManager.getInstance().getM_dungeon().player.getAtk();
        atk /= coefficient;
        AppManager.getInstance().getM_dungeon().player.setATk(atk);
        super.colorEffect();
    }
}
