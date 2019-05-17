package com.example.bamboohero.framework;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.this_is_framework.game.GameState;

// 공유 변수들을 저장하는 클래스
public class AppManager {
    private static AppManager s_instance;

    public GameState m_gameState;

    // 오직 하나만 생성하자~~ (싱글톤 방식)
    public static AppManager getInstance(){
        if(s_instance == null)
            s_instance = new AppManager();
        return s_instance;
    }

    private GameView m_gameView;
    private Resources m_resources;

    void setGameViwe(GameView gameViwe){
        m_gameView = gameViwe;
    }

    void setResource(Resources resources){
        m_resources = resources;
    }

    public GameView getGameView(){
        return m_gameView;
    }

    public Resources getResources() { return m_resources; }

    public Bitmap getBitmap(int r){
        return BitmapFactory.decodeResource(m_resources, r);
    }
}
