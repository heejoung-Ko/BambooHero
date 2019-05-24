package com.example.bamboohero.Game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;

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

    ArrayList<MinMax> addCoefficients;   // 더하기 공격 계수
    int nowAddCoefficient;               // 현재 공격 계수, atkCoefficients에 index로 넣어서 작동

    ArrayList<Tile> tiles;

    public TileMap(){
        mulCoefficients.add(new MinMax(2, 3));
        mulCoefficients.add(new MinMax(3, 5));
        mulCoefficients.add(new MinMax(5, 10));
        nowMulCoefficient = 0;

        addCoefficients.add(new MinMax(100, 200));
        addCoefficients.add(new MinMax(200, 300));
        addCoefficients.add(new MinMax(300, 500));
        nowAddCoefficient = 0;
    }

    public int getMulCoefficient(){
        return mulCoefficients.get(nowMulCoefficient).get();
    }

    public int getSumCoefficient(){
        return addCoefficients.get(nowAddCoefficient).get();
    }

    public COLOR getColor() {return color;}
    public void setColor(COLOR chageColor) {color = chageColor;}

    public void draw(Canvas canvas)
    {
        for(Tile tile : tiles)
            tile.draw(canvas);
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
        }

        // 오른쪽 슬라이드
        if(downX < upX) {
            Log.i("슬라이드 입력 확인","오른쪽으로 움직였당");
            return true;
        }

        // 왼쪽 슬라이드
        if(downX > upX) {
            Log.i("슬라이드 입력 확인","왼쪽으로 움직였당");
            return true;
        }

        // 아래 슬라이드
        if(downY < upY) {
            Log.i("슬라이드 입력 확인","아래쪽으로 움직였당");
            return true;
        }

        // 위 슬라이드
        if(downX < upX) {
            Log.i("슬라이드 입력 확인","위쪽으로 움직였당");
            return true;
        }

        return false;
    }
}
