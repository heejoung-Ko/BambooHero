package com.example.bamboohero.Game;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import com.example.bamboohero.Game.Tile.DivTile;
import com.example.bamboohero.Game.Tile.MulTile;
import com.example.bamboohero.Game.Tile.PlayerTile;
import com.example.bamboohero.Game.Tile.SubTile;
import com.example.bamboohero.Game.Tile.SumTile;
import com.example.bamboohero.Game.Tile.Tile;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

class MinMax{
    int min;
    int max;

    MinMax(int i_min, int i_max){
        min = i_min;
        max = i_max;
    }

    public static int randomRange(int n1, int n2) {
        return (int) (Math.random() * (n2 - n1 + 1)) + n1;
    }

    public int get(){
        return randomRange(min, max);
    }
}

public class TileMap {

    float downX, upX;   // 슬라이드 입력 읽기 위한 변수
    float downY, upY;   // "

    public enum COLOR {NONE, RED, BLUE, GREEN, YELLOW} // 색 종류
    COLOR color = COLOR.NONE;

    ArrayList<MinMax> mulCoefficients;   // 곱하기 공격 계수
    int nowMulCoefficient;               // 현재 공격 계수, atkCoefficients에 index로 넣어서 작동

    ArrayList<MinMax> sumCoefficients;   // 더하기 공격 계수
    int nowSumCoefficient;               // 현재 공격 계수, atkCoefficients에 index로 넣어서 작동

    CopyOnWriteArrayList<Tile> tiles;

    int pl_x, pl_y;

    long readyTime;

    long nowTime;

    boolean isMove;

    public TileMap(){

        mulCoefficients = new ArrayList<MinMax>();
        sumCoefficients = new ArrayList<MinMax>();

        mulCoefficients.add(new MinMax(2, 3));
        mulCoefficients.add(new MinMax(3, 5));
        mulCoefficients.add(new MinMax(5, 10));
        nowMulCoefficient = 0;

        sumCoefficients.add(new MinMax(100, 200));
        sumCoefficients.add(new MinMax(200, 300));
        sumCoefficients.add(new MinMax(300, 500));
        nowSumCoefficient = 0;

        tiles = new CopyOnWriteArrayList<Tile>();

        reset();

        readyTime = System.nanoTime();

        isMove = false;
    }

    public int getMulCoefficient(){
        return mulCoefficients.get(nowMulCoefficient).get();
    }

    public int getSumCoefficient(){
        return sumCoefficients.get(nowSumCoefficient).get();
    }

    public COLOR getColor() {return color;}
    public void setColor(COLOR chageColor) {color = chageColor;}

    public void draw(Canvas canvas) {
        for(Tile tile : tiles)
            tile.draw(canvas);
    }

    public void AddTile(int x, int y) {
        int i = (int) (Math.random() * 10);

        if(i < 4){
            tiles.add(new SumTile(x, y, getSumCoefficient()));
        }
        else if(i < 6){
            tiles.add(new SubTile(x, y, getSumCoefficient()));
        }
        else if(i<8){
            tiles.add(new MulTile(x, y, getMulCoefficient()));
        }
        else if(i<10){
            tiles.add(new DivTile(x, y, getMulCoefficient()));
        }
    }

    public void AddTile(int x, int y, boolean isPlayer) {
        tiles.add(new PlayerTile(x, y, getMulCoefficient()));
    }


    public boolean onTouch(MotionEvent event) {
        if(isMove)
            return false;

        //터치시작
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            downX = event.getX();
            downY = event.getY();
        }
        //터치종료
        else if (event.getAction() == MotionEvent.ACTION_UP) {
            upX = event.getX();
            upY = event.getY();

            if(Math.abs(upY - downY) < 100 && Math.abs(upX - downX) > 100) {
                // 오른쪽 슬라이드
                if (downX < upX) {
                    Log.i("슬라이드 입력 확인", "오른쪽으로 움직였당");
                    if(pl_x < 2)
                    {
                        Tile tile = null;
                        Tile pl = null;
                        int x = 0, y = 0;
                        for(Tile t:tiles)
                        {
                            x = t.getX();
                            y = t.getY();
                            if(x == pl_x && pl_y == y)
                                pl = t;
                            if(x == pl_x + 1 && pl_y == y)
                            {
                                tile = t;;
                            }
                        }
                        tile.Effect();
                        pl.color = tile.color;
                        tiles.remove(tile);
                        tile = null;
                        pl_x += 1;
                        pl.setPos(pl_x, pl_y);
                        AddTile(pl_x - 1, pl_y);
                        AppManager.getInstance().getM_dungeon().turn -= 1;
                        readyTime = System.nanoTime();
                        isMove = true;
                    }
                    return true;
                }

                // 왼쪽 슬라이드
                if (downX > upX) {
                    Log.i("슬라이드 입력 확인", "왼쪽으로 움직였당");
                    if(pl_x > 0)
                    {
                        Tile tile = null;
                        Tile pl = null;
                        int x = 0, y = 0;
                        for(Tile t:tiles)
                        {
                            x = t.getX();
                            y = t.getY();
                            if(x == pl_x && pl_y == y)
                                pl = t;
                            if(x == pl_x - 1 && pl_y == y)
                            {
                                tile = t;;
                            }
                        }
                        tile.Effect();
                        pl.color = tile.color;
                        tiles.remove(tile);
                        tile = null;
                        pl_x -= 1;
                        pl.setPos(pl_x, pl_y);
                        AddTile(pl_x + 1, pl_y);
                        AppManager.getInstance().getM_dungeon().turn -= 1;
                        readyTime = System.nanoTime();
                        isMove = true;
                    }
                    return true;
                }
            }

            else if(Math.abs(upY - downY) > 100 && Math.abs(upX - downX) < 100) {
                // 아래 슬라이드
                if (downY < upY) {
                    Log.i("슬라이드 입력 확인", "아래쪽으로 움직였당");
                    if(pl_y < 2)
                    {
                        Tile tile = null;
                        Tile pl = null;
                        int x = 0, y = 0;
                        for(Tile t:tiles)
                        {
                            x = t.getX();
                            y = t.getY();
                            if(x == pl_x && pl_y == y)
                                pl = t;
                            if(x == pl_x && pl_y + 1 == y)
                            {
                                tile = t;;
                            }
                        }
                        tile.Effect();
                        pl.color = tile.color;
                        tiles.remove(tile);
                        tile = null;
                        pl_y += 1;
                        pl.setPos(pl_x, pl_y);
                        AddTile(pl_x, pl_y - 1);
                        AppManager.getInstance().getM_dungeon().turn -= 1;
                        readyTime = System.nanoTime();
                        isMove = true;
                    }
                    return true;
                }

                // 위 슬라이드
                if (downY > upY) {
                    Log.i("슬라이드 입력 확인", "위쪽으로 움직였당");
                    if(pl_y > 0)
                    {
                        Tile tile = null;
                        Tile pl = null;
                        int x = 0, y = 0;
                        for(Tile t:tiles)
                        {
                            x = t.getX();
                            y = t.getY();
                            if(x == pl_x && pl_y == y)
                                pl = t;
                            if(x == pl_x && pl_y - 1 == y)
                            {
                                tile = t;;
                            }
                        }
                        tile.Effect();
                        pl.color = tile.color;
                        tiles.remove(tile);
                        tile = null;
                        pl_y -= 1;
                        pl.setPos(pl_x, pl_y);
                        AddTile(pl_x, pl_y + 1);
                        AppManager.getInstance().getM_dungeon().turn -= 1;
                        readyTime = System.nanoTime();
                        isMove = true;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public void Update(){
        nowTime = System.nanoTime();
        if(isMove){
            if((nowTime - readyTime) / 1000000000 > 0)
            {
                isMove = false;
                readyTime = System.nanoTime();
            }
        }
        else if((nowTime - readyTime) / 1000000000 > 3)
        {
            AppManager.getInstance().getM_dungeon().turn -= 1;
            readyTime = System.nanoTime();
        }
    }

    public void reset(){
        tiles.clear();
        for(int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){
                if(i == 1 && j == 1){
                    AddTile(i, j, true);
                    pl_x = 1;
                    pl_y = 1;
                }
                else AddTile(i, j);
            }
        }

    }
}
