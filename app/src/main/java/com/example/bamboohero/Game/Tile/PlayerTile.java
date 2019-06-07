package com.example.bamboohero.Game.Tile;

import android.graphics.Canvas;

import com.example.bamboohero.framework.GraphicObject;

public class PlayerTile extends Tile {
    public GraphicObject symbol;

    int newX, newY;

    public PlayerTile(int x, int y, int c) {
        super(x, y);
    }

    public void draw(Canvas canvas){
        super.draw(canvas);
        int x = posX_inTileMap * 310 + 160;
        int y = posY_inTileMap * 310 + 840;
        canvas.drawText("Player", x, y, p);
    }

    public void setnewXY(int x, int y){
        newX = x * 310 + 160;
        newY = y * 310 + 840;
    }
}
