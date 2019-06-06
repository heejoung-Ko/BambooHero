package com.example.bamboohero.Game;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import com.example.bamboohero.Game.Tile.DivTile;
import com.example.bamboohero.Game.Tile.MulTile;
import com.example.bamboohero.Game.Tile.SubTile;
import com.example.bamboohero.Game.Tile.SumTile;
import com.example.bamboohero.Game.Tile.Tile;

import java.util.ArrayList;

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

    public enum COLOR {NONE, RED, BLUE, GREEN, YELLOW}; // 색 종류
    COLOR color = COLOR.NONE;

    ArrayList<MinMax> mulCoefficients;   // 곱하기 공격 계수
    int nowMulCoefficient;               // 현재 공격 계수, atkCoefficients에 index로 넣어서 작동

    ArrayList<MinMax> sumCoefficients;   // 더하기 공격 계수
    int nowSumCoefficient;               // 현재 공격 계수, atkCoefficients에 index로 넣어서 작동

    ArrayList<Tile> tiles;

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

        tiles = new ArrayList<Tile>();

        for(int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){
                AddTile(i, j);
            }
        }
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


    public boolean onTouch(MotionEvent event) {
        //터치시작
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            downX = event.getX();
            downY = event.getY();
        }
        //터치종료
        else if (event.getAction() == MotionEvent.ACTION_UP) {
            upX = event.getX();
            upY = event.getY();


            if(Math.abs(upX - downX) > 300) {
                // 오른쪽 슬라이드
                if (downX < upX) {
                    Log.i("슬라이드 입력 확인", "오른쪽으로 움직였당");
                    if(AppManager.getInstance().m_dungeon.player.pl_local % 3 != 2)
                        AppManager.getInstance().m_dungeon.player.pl_local++;
                    return true;
                }

                // 왼쪽 슬라이드
                if (downX > upX) {
                    if(AppManager.getInstance().m_dungeon.player.pl_local % 3 != 0)
                        AppManager.getInstance().m_dungeon.player.pl_local--;
                    Log.i("슬라이드 입력 확인", "왼쪽으로 움직였당");
                    return true;
                }
            }

            else if(Math.abs(upY - downY) > 300) {
                // 아래 슬라이드
                if (downY < upY) {
                    if(AppManager.getInstance().m_dungeon.player.pl_local < 6)
                        AppManager.getInstance().m_dungeon.player.pl_local += 3;
                    Log.i("슬라이드 입력 확인", "아래쪽으로 움직였당");
                    return true;
                }

                // 위 슬라이드
                if (downX < upX) {
                    if(AppManager.getInstance().m_dungeon.player.pl_local > 2)
                        AppManager.getInstance().m_dungeon.player.pl_local -= 3;
                    Log.i("슬라이드 입력 확인", "위쪽으로 움직였당");
                    return true;
                }
            }
        }
        return false;
    }

    public void Update(){
        Tile tile = tiles.get(AppManager.getInstance().m_dungeon.player.pl_local);
        
    }
}
