package com.example.bamboohero.Game.Tile;

import android.app.AppComponentFactory;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.bamboohero.Game.AppManager;
import com.example.bamboohero.Game.TileMap;
import com.example.bamboohero.framework.GraphicObject;

public class Tile {
    public int posX_inTileMap;
    public int posY_inTileMap;
    public int coefficient; // 계수
    public TileMap.COLOR color;

    // 클래스에서 공용하는 변수들
    // 안바뀜
    static GraphicObject noneColorTile;
    static GraphicObject redColorTile;
    static GraphicObject blueColorTile;
    static GraphicObject greenColorTile;
    static GraphicObject yellowColorTile;

    public void draw(Canvas canvas){
        // 여기다가 컬러 타일 그리기
        switch (color){
            case NONE:
                break;
            case RED:
                break;
            case BLUE:
                break;
            case GREEN:
                break;
            case YELLOW:
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
}
