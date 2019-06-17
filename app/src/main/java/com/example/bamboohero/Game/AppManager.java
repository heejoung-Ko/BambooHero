package com.example.bamboohero.Game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;

import com.example.bamboohero.framework.GameView;

// 공유 변수들을 저장하는 클래스
public class AppManager {
    private static AppManager s_instance;
    private GameView m_gameView;
    private Resources m_resources;
    public DungeonState m_dungeon;
    // 오직 하나만 생성하자~~ (싱글톤 방식)

    Typeface font;

    public static AppManager getInstance(){
        if(s_instance == null)
            s_instance = new AppManager();
        return s_instance;
    }

    public void setGameViwe(GameView gameViwe){
        m_gameView = gameViwe;
    }
    public void setResource(Resources resources){
        m_resources = resources;
    }
    public void setM_dungeon(DungeonState dungeon) {m_dungeon = dungeon;}
    public GameView getGameView(){
        return m_gameView;
    }

    public Resources getResources() { return m_resources; }

    public Bitmap getBitmap(int r){return BitmapFactory.decodeResource(m_resources, r);};
    public DungeonState getM_dungeon(){return m_dungeon;}

    public void setFont(Typeface tf){font = tf;}
    public Typeface getFont() {return font;}
}
