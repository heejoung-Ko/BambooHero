package com.example.bamboohero.Game.Tile;

import android.graphics.Canvas;

import com.example.bamboohero.framework.GraphicObject;

public class PlayerTile extends Tile {
    public GraphicObject symbol;

    public PlayerTile(int x, int y, int c) {
        super(x, y);
    }

    public void draw(Canvas canvas){
        super.draw(canvas);
        int x = posX_inTileMap * 310 + 175;
        int y = posY_inTileMap * 310 + 840;
        canvas.drawText("플레이어", x, y, p);
    }

    public void setXY(int x, int y){
        //??
    }

    void Effect(){
    }
}
