package com.example.bamboohero.Game.Monster;

import com.example.bamboohero.Game.AppManager;
import com.example.bamboohero.R;

public class Mon_Slime extends Monster {
    public Mon_Slime(int stage){
        super(AppManager.getInstance().getBitmap(R.drawable.mon_slime));
        this.InitSpriteData(453,1112,2,2);
        player_power = 0;
        int i = (int) (Math.random() * 100 * stage);
        hp = stage * 200 + 100 + i + (int)(stage / 10) * 1000; // $$stage 받아와서 범위랜덤
        color = COLOR_RED;// $$랜덤으로

    }

    @Override
    public void Update(long gameTime) {
        player_power = AppManager.getInstance().getM_dungeon().player.getAtk(); // $$플레이어의 현재 공격력 받아오기
        if(player_power >= hp)
            state = STATE_OUT;
        else if(AppManager.getInstance().m_dungeon.turn <= 0)
            state = STATE_ATTACK;
        super.Update(gameTime);
    }
}
