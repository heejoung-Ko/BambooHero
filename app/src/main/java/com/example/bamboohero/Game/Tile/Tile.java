package com.example.bamboohero.Game.Tile;

import android.app.AppComponentFactory;
import android.graphics.Bitmap;

import com.example.bamboohero.framework.GraphicObject;

public class Tile extends GraphicObject {
    public int posX_inTileMap;
    public int posY_inTileMap;
    public int Coefficient; // 계수

    public Tile(Bitmap bitmap) {
        super(bitmap);
    }

    public void Effect()
    {
    }
}
