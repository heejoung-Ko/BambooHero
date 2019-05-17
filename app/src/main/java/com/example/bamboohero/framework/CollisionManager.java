package com.example.bamboohero.framework;

import android.graphics.Rect;

public class CollisionManager {
    public static boolean CheckBoxToBox(Rect _rt1, Rect _rt2){
        if(_rt1.intersect(_rt2))
            return true;
        return false;
    }

    public static boolean CollisionCheckPointToBox(int px,int py, Rect rt){
        if(rt.intersect(new Rect(px, py, px, py)))
            return true;


        return false;
    }
}
