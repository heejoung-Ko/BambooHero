package com.example.bamboohero.Game;

import android.util.Log;

import com.example.bamboohero.R;
import com.example.bamboohero.framework.SpriteAnimation;

public class Player extends SpriteAnimation {

    int atk;
    int pl_local; //현재위치
    int sprite_type;

    int coin;

    static final int STATE_NOMAL = 0;
    static final int STATE_SMILE = 1;
    static final int STATE_GOOD = 2;
    static final int STATE_BAD = 3;

    public Player() {
        super(AppManager.getInstance().getBitmap(R.drawable.char_gom));
        this.InitSpriteData(453,1112,2,2);
        atk = 0;
        coin = 0;
        Log.i("플레이어", "그려짐");
    }

    public void setATk(int changeAtk) { atk = changeAtk; }
    public int getAtk() { return atk; }

    public void Update(long GameTime) {
        super.Update(GameTime);
    }
}
