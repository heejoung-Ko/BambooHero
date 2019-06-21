package com.example.bamboohero.Game.Tile;

import android.app.AppComponentFactory;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.example.bamboohero.Game.AppManager;
import com.example.bamboohero.Game.TileMap;
import com.example.bamboohero.R;
import com.example.bamboohero.framework.GraphicObject;
import com.example.bamboohero.framework.SpriteAnimation;

import java.sql.Time;

public class Tile{
    public int posX_inTileMap;
    public int posY_inTileMap;
    public int coefficient; // 계수

    public TileMap.COLOR color;

    public SpriteAnimation colorTile;

    public boolean opening = false;
    public boolean closing = false;

    long time = 0;

    public int newX, newY;

    // 클래스에서 공용하는 변수들
    // 안바뀜

    static Paint strokePaint = null;
    static Paint textPaint = null;

    public Tile(){
        if(textPaint == null) {
            textPaint = new Paint();
            textPaint.setTypeface(AppManager.getInstance().getFont2());
            textPaint.setTextSize(70);
            textPaint.setColor(Color.WHITE);
        }

        if(strokePaint == null) {
            strokePaint = new Paint();
            strokePaint.setTypeface(AppManager.getInstance().getFont2());
            strokePaint.setTextSize(70);
            strokePaint.setColor(Color.BLACK);
            strokePaint.setStyle(Paint.Style.STROKE);
            strokePaint.setStrokeWidth(10);
        }

        opening = true;
    }

      public void draw(Canvas canvas){
        if(opening)
            colorTile.DrawReverse(canvas);
        else
            colorTile.Draw(canvas);
    }

    void colorEffect(){
        // 타일 맵의 현재 컬러를 받아오기
        TileMap.COLOR nowColor = AppManager.getInstance().getM_dungeon().tileMap.getColor();

        // 타일 맵의 현재 컬러와 이 타일의 컬러가 같을 경우 추가 공격력
        if(nowColor == color)
        {
            int atk = AppManager.getInstance().getM_dungeon().player.getAtk();
            atk += 100;  // 이거 나중에 조정
            AppManager.getInstance().getM_dungeon().player.setATk(atk);
        }
    }

    public void setPos(int changeX, int changeY){
        posX_inTileMap = changeX;
        posY_inTileMap = changeY;

        int tx = posX_inTileMap * 320 + 60 ;
        int ty = posY_inTileMap * 320 + 640 + 100;
        colorTile.SetPosition(tx, ty);
    }

    public int getX(){ return  posX_inTileMap; }
    public int getY(){ return  posY_inTileMap; }

    public void Effect(){}

    public boolean Update(){
        if(time == 0)
            time = System.currentTimeMillis();
        long nowtime =  System.currentTimeMillis();

        if (opening || closing)
            if(colorTile.Update(nowtime - time, true))
            {
                opening = false;
                if(closing)
                {
                    closing = false;
                    return true;
                }
            }
            return  false;
    }

    public void Close(int x, int y){
        closing = true;
        newX = x;
        newY = y;
    }

    public void colorChoice(int x, int y){
        int i = (int) (Math.random() * 4);

        switch (i) {
            case 0:
                color = TileMap.COLOR.RED;
                colorTile = new SpriteAnimation(AppManager.getInstance().getBitmap(R.drawable.tile_red));
                break;
            case 1:
                color = TileMap.COLOR.BLUE;
                colorTile = new SpriteAnimation(AppManager.getInstance().getBitmap(R.drawable.tile_blue));
                break;
            case 2:
                color = TileMap.COLOR.GREEN;
                colorTile = new SpriteAnimation(AppManager.getInstance().getBitmap(R.drawable.tile_green));
                break;
            case 3:
                color = TileMap.COLOR.YELLOW;
                colorTile = new SpriteAnimation(AppManager.getInstance().getBitmap(R.drawable.tile_yellow));
                break;
        }

        setPos(x, y);

        colorTile.InitSpriteData(106*3, 106*3, 30, 6);
    }
}
