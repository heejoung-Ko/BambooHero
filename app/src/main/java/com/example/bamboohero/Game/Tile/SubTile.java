package com.example.bamboohero.Game.Tile;

import android.graphics.Canvas;

import com.example.bamboohero.Game.AppManager;
import com.example.bamboohero.R;
import com.example.bamboohero.framework.GraphicObject;

public class SubTile extends Tile {
    static GraphicObject symbol = null;

    public SubTile(int x, int y, int c) {
        super();
        colorChoice(x, y);
        coefficient = c;
        if(symbol == null){
            symbol = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.tile_sub));
        }
    }

    public void draw(Canvas canvas){
        super.draw(canvas);

        if(opening || closing)
            return;

        int x = posX_inTileMap * 320 + 60;
        int y = posY_inTileMap * 320 + 640;
        symbol.Draw(canvas, x, y);
        x = posX_inTileMap * 310 + 160;
        y = posY_inTileMap * 310 + 840;
        canvas.drawText("-" + coefficient, x - 70, y + 105, strokePaint);
        canvas.drawText("-" + coefficient, x - 70, y + 105, textPaint);
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
