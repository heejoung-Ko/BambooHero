package com.example.bamboohero.Game;

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

    public enum COLOR {NONE, RED, BLUE, GREEN, YELLOW}; // 색 종류
    COLOR color = COLOR.NONE;

    ArrayList<MinMax> mulCoefficients;   // 곱하기 공격 계수
    int nowMulCoefficient;               // 현재 공격 계수, atkCoefficients에 index로 넣어서 작동

    ArrayList<MinMax> addCoefficients;   // 더하기 공격 계수
    int nowAddCoefficient;               // 현재 공격 계수, atkCoefficients에 index로 넣어서 작동


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

}
