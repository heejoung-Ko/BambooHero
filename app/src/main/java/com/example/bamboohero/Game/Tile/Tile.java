package com.example.bamboohero.Game.Tile;

import android.app.AppComponentFactory;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.bamboohero.Game.AppManager;
import com.example.bamboohero.Game.TileMap;
import com.example.bamboohero.R;
import com.example.bamboohero.framework.GraphicObject;

public class Tile {
    public int posX_inTileMap;
    public int posY_inTileMap;
    public int coefficient; // 계수
    public TileMap.COLOR color;

    // 클래스에서 공용하는 변수들
    // 안바뀜
    static GraphicObject noneColorTile = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.tile_blue));
    static GraphicObject redColorTile =  new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.tile_red));
    static GraphicObject blueColorTile =  new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.tile_blue));
    static GraphicObject greenColorTile =  new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.tile_green));
    static GraphicObject yellowColorTile = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.tile_yellow));

    static Paint p = new Paint();

    public Tile(int x, int y){

        p.setTextSize(50);
        p.setColor(Color.BLACK);

        setPos(x, y);

        int i = (int) (Math.random() * 4);

        switch (i) {
            case 0:
                color = TileMap.COLOR.RED;
                break;
            case 1:
                color = TileMap.COLOR.BLUE;
                break;
            case 2:
                color = TileMap.COLOR.GREEN;
                break;
            case 3:
                color = TileMap.COLOR.YELLOW;
                break;
        }
    }

    public void draw(Canvas canvas){
        // 여기다가 컬러 타일 그리기

        int x = (posX_inTileMap * 300);
        int y = (posY_inTileMap * 300) + 300;
        switch (color){
            case NONE:
                noneColorTile.Draw(canvas, x, y);
                break;
            case RED:
                redColorTile.Draw(canvas, x, y);
                break;
            case BLUE:
                blueColorTile.Draw(canvas, x, y);
                break;
            case GREEN:
                greenColorTile.Draw(canvas, x, y);
                break;
            case YELLOW:
                yellowColorTile.Draw(canvas, x, y);
                break;
        }
    }

    void colorEffect(){
        // 타일 맵의 현재 컬러를 받아오기
        TileMap.COLOR nowColor = AppManager.getInstance().getM_dungeon().tileMap.getColor();

        // 타일 맵의 현재 컬러와 이 타일의 컬러가 같을 경우 추가 공격력
        if(nowColor == color)
        {
            int atk = AppManager.getInstance().getM_dungeon().player.getAtk();
            atk += 10;  // 이거 나중에 조정
            AppManager.getInstance().getM_dungeon().player.setATk(atk);
        }
    }

    void setPos(int changeX, int changeY){
        posX_inTileMap = changeX;
        posY_inTileMap = changeY;
    }
    void getPos(int x, int y){
        x = posX_inTileMap;
        y = posX_inTileMap;
    }
}
