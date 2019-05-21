package com.example.bamboohero.framework;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.bamboohero.Game.AppManager;
import com.example.bamboohero.Game.DungeonState;

public class GameView extends SurfaceView implements SurfaceHolder.Callback{
    private IState m_state;

    private GameViewThread m_thread;

    public GameView(Context context) {
        super(context);
        // 키 입력 처리를 받기 위해
        setFocusable(true);

        AppManager.getInstance().setGameViwe(this);
        AppManager.getInstance().setResource(getResources());

        getHolder().addCallback(this);

        ChageGameState(new DungeonState());

        m_thread = new GameViewThread(getHolder(), this);

    }

    public void myOnDraw(Canvas canvas){
        canvas.drawColor(Color.BLACK);
        m_state.Render(canvas);
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){

    }

    public void surfaceCreated(SurfaceHolder holder){
        // 스레드를 실행 상태로 만듦
        m_thread.setRunning(true);
        // 스레드 실행
        m_thread.start();
    }

    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;
        m_thread.setRunning(false);
        while (retry) {
            try {
                // 스레드 중지
                m_thread.join();
                retry = false;
            } catch (InterruptedException e) {
                // 스레드가 종료되도록 계속 시도
            }
        }
    }

    public void Update() {
        // long gameTime = System.currentTimeMillis();
        m_state.Update();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event){
        m_state.onKeyDown(keyCode, event);
        return true;
    }

    public boolean onTouchEvent(MotionEvent event){
        m_state.onTouchEvent(event);
        return true;
    }

    public void ChageGameState(IState _state){
        if(m_state != null)
            m_state.Destroy();
        _state.Init();
        m_state = _state;
    }
}
