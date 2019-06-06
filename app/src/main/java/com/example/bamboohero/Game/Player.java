package com.example.bamboohero.Game;

import com.example.bamboohero.R;
import com.example.bamboohero.framework.SpriteAnimation;

public class Player extends SpriteAnimation {

    int atk;
    int pl_local; //현재위치
    int pl_old_local;

    public Player( ) {
        super(AppManager.getInstance().getBitmap(R.drawable.char_gom));
        atk = 0;
    }

    public void setATk(int changeAtk) { atk = changeAtk; }
    public int getAtk() { return atk; }
}
