package com.example.bamboohero.Game.Monster;

import com.example.bamboohero.Game.AppManager;
import com.example.bamboohero.R;

public class Mon_Slime extends Monster {
    public Mon_Slime(int stage){
        super(AppManager.getInstance().getBitmap(R.drawable.mon_slime));
        player_power = 0;
        hp = 200; // $$stage 받아와서 범위랜덤
        color = COLOR_RED;// $$랜덤으로
        SetPosition(100, 100); // $$일단 대충 표시만 해두기

    }

    @Override
    public void Update(long gameTime) {
        player_power = AppManager.getInstance().getM_dungeon().player.getAtk(); // $$플레이어의 현재 공격력 받아오기
        if(player_power >= hp)
            state = STATE_OUT;
        else if(AppManager.getInstance().getM_dungeon().turn == 0)
            state = STATE_ATTACK;
        super.Update(gameTime);
    }
}