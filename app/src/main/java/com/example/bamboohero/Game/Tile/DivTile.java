package com.example.bamboohero.Game.Tile;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.bamboohero.Game.AppManager;
import com.example.bamboohero.R;
import com.example.bamboohero.framework.GraphicObject;

public class DivTile extends Tile {
    static GraphicObject symbol = null;
    public DivTile(int x, int y, int c) {
        super();
        colorChoice(x, y);
        coefficient = c;
        if(symbol == null){
            symbol = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.tile_div));
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
        float c = (float) ((coefficient % 2) * 0.5 )+ (int)(coefficient / 2);
        canvas.drawText("÷" + c, x - 70, y + 105, strokePaint);
        canvas.drawText("÷" + c, x - 70, y + 105, textPaint);
    }

    public void Effect(){
        int atk = AppManager.getInstance().getM_dungeon().player.getAtk();
        float c = (float) ((coefficient % 2) * 0.5 )+ (int)(coefficient / 2);
        atk /= c;
        if(atk < 0)
            atk =0;
        AppManager.getInstance().getM_dungeon().player.setATk(atk);
        super.colorEffect();
    }
}
