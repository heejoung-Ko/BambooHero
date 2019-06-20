package com.example.bamboohero.Game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.example.bamboohero.Game.Monster.Mon_Slime;
import com.example.bamboohero.Game.Monster.Monster;
import com.example.bamboohero.R;
import com.example.bamboohero.framework.GraphicObject;
import com.example.bamboohero.framework.IState;
import com.example.bamboohero.framework.SpriteAnimation;

import java.util.Random;

public class DungeonState implements IState {

    public int stage_level;
    public int turn;
    public int stage_type;

    Paint p;
    SpriteAnimation sp_StageClear = new SpriteAnimation(AppManager.getInstance().getBitmap(R.drawable.stage_clear));

    public static int SCREEN_WIDTH = 0;
    public static int SCREEN_HEIGHT = 0;

    public static final int STAGE_TYPE_FOREST = 0;
    public static final int STAGE_TYPE_CAVE = 1;
    public static final int STAGE_TYPE_CASTLE = 2;

    public BackGround backGround;

    public Player player;

    public TileMap tileMap;

    public Monster monster;

    public Random randTalk = new Random();

    public GraphicObject damage = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.damage));

    public DungeonState(){
        AppManager.getInstance().m_dungeon = this;
    }

    boolean isStageClear;

    @Override
    public void Init() {
        stage_level = 1;

        player = new Player();
        player.setATk(100);
        player.SetPosition(0,0);
        player.pl_local = 4;

        monster = new Mon_Slime(stage_level);
        monster.type = monster.TYPE_SLIME;
        monster.say = 0;

        stage_type = backGround.TYPE_CAVE;
        backGround = new BackGround(stage_type); // &&if
        turn = 10;

        tileMap = new TileMap();

        p = new Paint();
        p.setTypeface(AppManager.getInstance().getFont1());
        p.setColor(Color.BLACK);

        sp_StageClear.InitSpriteData(108*3, 371*3, 10, 10);
        sp_StageClear.SetPosition(0, 800);
    }

    @Override
    public void Destroy() {

    }

    @Override
    public void Update() {
        long GameTime = System.currentTimeMillis();

        if(isStageClear)
        {
            sp_StageClear.Update(GameTime);
            return;
        }

        monster.Update(GameTime);
        if(monster.state == monster.STATE_OUT){
            isStageClear = true;
        }
        else if(monster.state == monster.STATE_ATTACK){
            player.setATk(0);
            monster.say = 5;
        }
        player.Update(GameTime);
        monster.Update(GameTime);
        tileMap.Update();
    }

    @Override
    public void Render(Canvas canvas) {
        backGround.Draw(canvas);
        p.setTextSize(45);

        player.Draw(canvas);

        damage.Draw(canvas);

        canvas.drawText("공격력 : " + String.valueOf(player.getAtk()),430, 180, p);
        canvas.drawText("HP : " + String.valueOf(monster.getHp()), 430, 310, p);
        p.setTextSize(50);
        canvas.drawText("적 : \" " + monster.talking(monster.say) + " \"",0, 150, p);
        canvas.drawText("남은 턴 : " + turn,0, 200, p);
        canvas.drawText("남은 시간 : " + (3 - (tileMap.nowTime - tileMap.readyTime) / 1000000000),0, 250, p);
        canvas.drawText("현재 층 : " + stage_level,0, 300, p);
        canvas.drawText("플레이어 : " + tileMap.pl_x + tileMap.pl_y,0, 500, p);

        tileMap.draw(canvas);
        monster.Draw(canvas);

        if(isStageClear)
        {
            sp_StageClear.Draw(canvas);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_DPAD_UP) {
            if (monster.state != monster.STATE_ATTACK) {
                player.setATk(player.getAtk() + 100);
                monster.say = randTalk.nextInt(4);
                turn--;
            }
        }
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(isStageClear && event.getAction() == MotionEvent.ACTION_UP)
        {
            stage_level++;
            monster = new Mon_Slime(stage_level);
            player.setATk(100);
            turn = 10;
            monster.say = 0;
            tileMap.reset();

            isStageClear = false;
            return false;
        }
        tileMap.onTouch(event);
        return false;
    }

    public static void setNoSoftKeyScreenInfo(Context context){
        DisplayMetrics dmath = context.getResources().getDisplayMetrics();
        SCREEN_WIDTH = dmath.widthPixels;
    }
}
