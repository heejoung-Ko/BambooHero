package com.example.bamboohero.framework;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameViewThread extends Thread {
    // 접근을 위한 멤버 변수
    private SurfaceHolder m_surfaceHolder;
    private GameView m_gameView;
    // 스레드 실행 상태 멤버 변수
    private boolean m_run = false;

    public GameViewThread(SurfaceHolder surfaceHolder, GameView gameView){
        m_surfaceHolder = surfaceHolder;
        m_gameView = gameView;
    }

    public void setRunning(boolean run){
        m_run = run;
    }

    public void run(){
        Canvas _canvas;
        while (m_run){
            _canvas = null;
            try{
                m_gameView.Update();
               // SurfaceHolder를 통해 Surface에 접근해서 가져옴
               _canvas = m_surfaceHolder.lockCanvas(null);
               synchronized (m_surfaceHolder){
                   m_gameView.myOnDraw(_canvas);
               }
            }finally {
                if(_canvas != null)
                    // Surface를 화면에 표시
                    m_surfaceHolder.unlockCanvasAndPost(_canvas);
            }
        }
    }
}
