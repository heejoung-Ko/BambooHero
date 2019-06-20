package com.example.bamboohero.Game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.example.bamboohero.R;
import com.example.bamboohero.framework.IState;
import com.example.bamboohero.framework.SpriteAnimation;

public class LobbyState implements IState {

    SpriteAnimation LobbyBackground = new SpriteAnimation(AppManager.getInstance().getBitmap(R.drawable.main_background));

    float touch_x=0;
    float touch_y=0;

    Paint p;


    @Override
    public void Init() {
        LobbyBackground.InitSpriteData(659*3,371*3,2,2);
        p = new Paint();
        p.setTypeface(AppManager.getInstance().getFont2());
        p.setTextSize(70);
        p.setColor(Color.BLACK);
    }

    @Override
    public void Update() {
        long GameTime = System.currentTimeMillis();
        LobbyBackground.Update(GameTime);
    }

    @Override
    public void Render(Canvas canvas) {
        LobbyBackground.Draw(canvas);
        canvas.drawText(touch_x+", " + touch_y,0, 100, p);
        p.setTypeface(AppManager.getInstance().getFont1());
        canvas.drawText("얼마얼마",880, 100, p);
    }

    @Override
    public void Destroy() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            touch_x = event.getX();
            touch_y = event.getY();
        }
        if(289 <= touch_x && touch_x <= 289+508 &&
                1104 < touch_y && touch_y <=1104+153) {
            Log.i("터치확인", "던전입장");
            //AppManager.getInstance().setState_number(AppManager.DUNGEON_STATE);
        }
        if(289 <= touch_x && touch_x <= (289+508) &&
                1104+153+65 < touch_y && touch_y <=1104+153+65+153)
            Log.i("터치확인", "업그레이드");
        if(289 <= touch_x && touch_x <= (289+508) &&
                1104+153+65+153+65 < touch_y && touch_y <=(1104+153+65+153+65+153))
            Log.i("터치확인", "나가기");
        if(937 <= touch_x && touch_x <= 1080 &&
                1783 < touch_y && touch_y <=1920)
            Log.i("터치확인", "던전입장");
        return false;
    }
}
