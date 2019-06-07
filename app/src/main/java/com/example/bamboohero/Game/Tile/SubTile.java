package com.example.bamboohero.Game.Tile;

import android.graphics.Canvas;

import com.example.bamboohero.Game.AppManager;

public class SubTile extends Tile {
    public SubTile(int x, int y, int c) {
        super(x, y);
        coefficient = c;
    }

    public void draw(Canvas canvas){
        super.draw(canvas);
        int x = posX_inTileMap * 310 + 175;
        int y = posY_inTileMap * 310 + 840;
        canvas.drawText("-" + coefficient, x, y, p);
    }

    public void Effect(){
        int atk = AppManager.getInstance().getM_dungeon().player.getAtk();
        atk -= coefficient;
        if(atk < 0)
            atk =0;
        AppManager.getInstance().getM_dungeon().player.setATk(atk);
        super.colorEffect();
    }
}
