package com.example.bamboohero.Game.Monster;

import com.example.bamboohero.Game.AppManager;
import com.example.bamboohero.R;

public class Mon_Slime extends Monster {
    public Mon_Slime(int stage){
        super(AppManager.getInstance().getBitmap(R.drawable.test_r));
        hp = 200; // $$stage 받아와서 범위랜덤
        color = COLOR_RED;// $$랜덤으로
        //talk = talking(1); // $$어디로 옮기지 터치이벤트..? 어쨌든 움직임 처리할때로
        SetPosition(100, 100); // $$일단 대충 표시만 해두기

        if(stage < 10) // $$턴제한을 스테이지 기준으로 해놨는데 몬스터 HP 기준으로 하려면 수정해야됨 (HP로 수식 만들던가)
            AppManager.getInstance().getM_dungeon().turn = 10;
        else if(stage < 20)
            AppManager.getInstance().getM_dungeon().turn = 20;
        else if(stage < 30)
            AppManager.getInstance().getM_dungeon().turn = 30;
        else
            AppManager.getInstance().getM_dungeon().turn = 40;
    }

    @Override
    public void Update(long gameTime) {
        super.Update(gameTime);
    }
}
