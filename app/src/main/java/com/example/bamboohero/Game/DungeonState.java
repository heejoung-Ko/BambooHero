package com.example.bamboohero.Game;

import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.example.bamboohero.framework.IState;

public class DungeonState implements IState {

    public int stage_level;
    public int turn;
    public int stage_type;

    public static final int STAGE_TYPE_FOREST = 0;
    public static final int STAGE_TYPE_CAVE = 1;
    public static final int STAGE_TYPE_CASTLE = 2;

    @Override
    public void Init() {

    }

    @Override
    public void Destroy() {

    }

    @Override
    public void Update() {

    }

    @Override
    public void Render(Canvas canvas) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

}
