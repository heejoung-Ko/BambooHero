package com.example.bamboohero.Game.Monster;

import android.graphics.Bitmap;

import com.example.bamboohero.framework.SpriteAnimation;

public class Monster extends SpriteAnimation {
    public int hp;

    public static final int STATE_LIVE = 0;
    public static final int STATE_OUT = 1;
    public static final int STATE_ATTACK = 2;

    public static final int COLOR_RED = 0;
    public static final int COLOR_GREEN = 1;
    public static final int COLOR_BLUE = 3;

    public static final int TYPE_SLIME = 0;

    public int state = STATE_LIVE;
    public int color;
    public int type;
    public int say;
    public int player_power;

    public Monster(Bitmap bitmap) {
        super(bitmap);
    }

    @Override
    public void Update(long gameTime) {
        super.Update(gameTime);

    }

    public void Skill() { // 스킬있는 친구덜
        switch (type){
            default:
                break;
        }
    }

    public String talking(int value){
        switch (value){
            case 0:
                return "어ㅓ앙!";
            case 1:
                return "먐먀";
            case 2:
                return "밈먀";
            case 3:
                return "뮴먀";
            case 4:
                return "몀먀";
            default:
                return "넌 이미 죽어있다";
        }
    }

    public int getHp(){
        return hp;
    }
}
