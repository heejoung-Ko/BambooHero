package com.example.bamboohero.Game;

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

    public int state = STATE_LIVE;
    public int color;
    public int type; //일반 : 0  스킬있는 몬스터 : 1 ~ n

    public String talk;

    public Monster(Bitmap bitmap) {
        super(bitmap);
    }

    @Override
    public void Update(long gameTime) {
        int power = 100; // $$플레이어의 현재 공격력 받아오기

        if(power >= hp)
          state = STATE_OUT;
        else if(AppManager.getInstance().getM_dungeon().turn == 0)
            state = STATE_ATTACK;
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
            case 1:
                return "먐먀";
            case 2:
                return "밈먀";
            case 3:
                return "뮴먀";
            case 4:
                return "몀먀";
            default:
                return "뭠마";
        }
    }
}
