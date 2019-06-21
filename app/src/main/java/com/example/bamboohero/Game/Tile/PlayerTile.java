package com.example.bamboohero.Game.Tile;

import android.graphics.Canvas;

import com.example.bamboohero.Game.AppManager;
import com.example.bamboohero.Game.TileMap;
import com.example.bamboohero.R;
import com.example.bamboohero.framework.GraphicObject;
import com.example.bamboohero.framework.SpriteAnimation;

public class PlayerTile extends Tile {
    static GraphicObject symbol = null;

    TileMap.COLOR newColor;

    SpriteAnimation tRed = new SpriteAnimation(AppManager.getInstance().getBitmap(R.drawable.tile_red));
    SpriteAnimation tBlue = new SpriteAnimation(AppManager.getInstance().getBitmap(R.drawable.tile_blue));
    SpriteAnimation tGreen = new SpriteAnimation(AppManager.getInstance().getBitmap(R.drawable.tile_green));
    SpriteAnimation tYellow = new SpriteAnimation(AppManager.getInstance().getBitmap(R.drawable.tile_yellow));

    public PlayerTile(int x, int y, int c) {
        super();
        colorChoice(x, y);

        if(symbol == null){
            symbol = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.tile_player));
        }
    }

    public void draw(Canvas canvas){
        super.draw(canvas);

        if(opening || closing)
            return;

        int x = posX_inTileMap * 320 + 60;
        int y = posY_inTileMap * 320 + 640 + 100;
        symbol.Draw(canvas, x, y);
        x = posX_inTileMap * 310 + 160;
        y = posY_inTileMap * 310 + 840 + 100;
        canvas.drawText("Player", x - 70, y + 105, strokePaint);
        canvas.drawText("Player", x - 70, y + 105, textPaint);
    }
    public boolean Update(){
        if(time == 0)
            time = System.currentTimeMillis();
        long nowtime =  System.currentTimeMillis();
        if (opening || closing)
            if(colorTile.Update(nowtime - time, true))
            {
                if(opening)
                    opening = false;
                else {
                    closing = false;
                    Move();
                    opening = true;
                }
            }
            return false;
    }
    public  void Close(int x, int y, TileMap.COLOR c){
        closing = true;
        newX = x;
        newY = y;
        newColor = c;
    }

    public void colorChoice(int x, int y){
        int i = (int) (Math.random() * 4);

        switch (i) {
            case 0:
                color = TileMap.COLOR.RED;
                colorTile = tRed;
                break;
            case 1:
                color = TileMap.COLOR.BLUE;
                colorTile = tBlue;
                break;
            case 2:
                color = TileMap.COLOR.GREEN;
                colorTile = tGreen;
                break;
            case 3:
                color = TileMap.COLOR.YELLOW;
                colorTile = tYellow;
                break;
        }

        setPos(x, y);

        colorTile.InitSpriteData(106*3, 106*3, 30, 6);
    }

    public void Move(){
        color = newColor;

        switch (color){
            case RED:
                colorTile = tRed;
                break;
            case BLUE:
                colorTile = tBlue;
                break;
            case GREEN:
                colorTile = tGreen;
                break;
            case YELLOW:
                colorTile = tYellow;
                break;
            case NONE:
                break;
        }
        setPos(newX, newY);
        colorTile.InitSpriteData(106*3, 106*3, 30, 6);
    }
}
