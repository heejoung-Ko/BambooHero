package com.example.bamboohero.Game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.example.bamboohero.Game.Monster.Mon_Slime;
import com.example.bamboohero.Game.Monster.Monster;
import com.example.bamboohero.framework.IState;

import java.util.Random;

public class DungeonState implements IState {

    public int stage_level;
    public int turn;
    public int stage_type;

    public static final int STAGE_TYPE_FOREST = 0;
    public static final int STAGE_TYPE_CAVE = 1;
    public static final int STAGE_TYPE_CASTLE = 2;

    public BackGround backGround;

    public Player player;

    public TileMap tileMap;

    public Monster monster;

    public Random randTalk = new Random();

    @Override
    public void Init() {
        stage_level = 1;
        player = new Player();
        player.setATk(100);
        monster = new Mon_Slime(stage_level);
        monster.type = monster.TYPE_SLIME;
        backGround = new BackGround(0);
        turn = 10;
        monster.say = 0;
    }

    @Override
    public void Destroy() {

    }

    @Override
    public void Update() {
        long GameTime = System.currentTimeMillis();
        //monster.Update(GameTime);
        if(monster.state == monster.STATE_OUT){
            stage_level++;
            monster = new Mon_Slime(stage_level);
            player.setATk(100);
            turn = 10;
            monster.say = 0;
        }
        else if(monster.state == monster.STATE_ATTACK){
            player.setATk(0);
            monster.say = 5;
        }
        turn--;

    }

    @Override
    public void Render(Canvas canvas) {
        Paint p = new Paint();
        backGround = new BackGround(stage_type);
        p.setTextSize(20);
        p.setColor(Color.BLACK);

        canvas.drawText("적의 HP : " + String.valueOf(monster.getHp()), 0, 20, p);
        canvas.drawText("나의 공격력 : " + String.valueOf(player.getAtk()),0, 40, p);
        canvas.drawText("적 : \" " + monster.talking(monster.say) + " \"",0, 60, p);
        canvas.drawText("남은 턴 : " + turn,0, 80, p);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_DPAD_UP) {
            if (monster.state != monster.STATE_ATTACK) {
                player.setATk(player.getAtk() + 100);
                monster.say = randTalk.nextInt(4);
            }
        }
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }
}
