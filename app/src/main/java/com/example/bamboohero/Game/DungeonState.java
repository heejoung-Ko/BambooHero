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
    SpriteAnimation sp_CharacterAttack = new SpriteAnimation(AppManager.getInstance().getBitmap(R.drawable.cut_scene));

    public static int SCREEN_WIDTH = 0;
    public static int SCREEN_HEIGHT = 0;

    public static final int STAGE_TYPE_FOREST = 0;
    public static final int STAGE_TYPE_CAVE = 1;
    public static final int STAGE_TYPE_CASTLE = 2;

    public BackGround backGround;

    public Player player;

    public TileMap tileMap;

    public Monster monster;

    public Shop shop = new Shop();
    int shopStage = 5;

    public Random randTalk = new Random();

    public GraphicObject damage = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.damage));

    GraphicObject coin = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.coin));

    public DungeonState(){
        AppManager.getInstance().m_dungeon = this;
    }

    boolean isStageClear;
    boolean isMonsterAttack;
    boolean isPlayerAttack;
    boolean isPlayerAttackSet = true;

    public Paint strokePaint = null;
    public Paint textPaint = null;

    @Override
    public void Init() {
        stage_level = 1;

        damage.SetPosition(0, 100);

        player = new Player();
        player.setATk(100);
        player.SetPosition(0,100);
        player.pl_local = 4;

        monster = new Mon_Slime(stage_level);
        monster.type = monster.TYPE_SLIME;
        monster.say = 0;
        monster.SetPosition(0, 100);

        stage_type = backGround.TYPE_CAVE;
        backGround = new BackGround(stage_type); // &&if
        turn = 10;

        tileMap = new TileMap();

        p = new Paint();
        p.setTypeface(AppManager.getInstance().getFont1());
        p.setColor(Color.BLACK);

        sp_StageClear.InitSpriteData(108*3, 371*3, 10, 10);
        sp_StageClear.SetPosition(0, 800);

        sp_CharacterAttack.InitSpriteData(155*3,371*3,16,12);
        sp_CharacterAttack.SetPosition(0,100);
        sp_CharacterAttack.setM_ready(false);

        textPaint = new Paint();
        textPaint.setTypeface(AppManager.getInstance().getFont2());
        textPaint.setTextSize(70);
        textPaint.setColor(Color.WHITE);

        strokePaint = new Paint();
        strokePaint.setTypeface(AppManager.getInstance().getFont2());
        strokePaint.setTextSize(70);
        strokePaint.setColor(Color.BLACK);
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setStrokeWidth(10);

        isMonsterAttack = false;

        coin.SetPosition(700, 0);
    }

    @Override
    public void Destroy() {

    }

    @Override
    public void Update() {
        long GameTime = System.currentTimeMillis();

        if(stage_level % 10 == shopStage){
            shop.Update(GameTime);
            player.Update(GameTime);
            return;
        }

        tileMap.UpdateTile();

        if(!isPlayerAttackSet)
        {
            sp_CharacterAttack.InitSpriteData(155*3,371*3,16,12);
            sp_CharacterAttack.resetFrameCount();
            sp_CharacterAttack.setM_ready(false);
            isPlayerAttackSet = true;
            isPlayerAttack = true;
        }
        if(isPlayerAttack)
        {
            sp_CharacterAttack.Update(GameTime);
            if (sp_CharacterAttack.getAnimationEnd())
            {
                isPlayerAttack = false;
                isStageClear = true;
            }
            return;
        }

        if(isStageClear)
        {
            sp_StageClear.Update(GameTime);
            return;
        }

        monster.Update(GameTime);
        if(monster.state == monster.STATE_OUT){
            isPlayerAttackSet = false;
        }
        else if(monster.state == monster.STATE_ATTACK){
            player.setATk(0);
            monster.say = 5;
            isMonsterAttack = true;
            return;
        }
        monster.Update(GameTime);
        tileMap.UpdateTime();
    }

    @Override
    public void Render(Canvas canvas) {

        backGround.Draw(canvas);
        p.setTextSize(45);

        player.Draw(canvas);

        coin.Draw(canvas);

        strokePaint.setTextAlign(Paint.Align.LEFT);
        textPaint.setTextAlign(Paint.Align.LEFT);

        strokePaint.setColor(Color.WHITE);
        textPaint.setColor(Color.BLACK);

        strokePaint.setTextSize(100);
        textPaint.setTextSize(100);
        canvas.drawText("STAGE " + stage_level,10, 80, strokePaint);
        canvas.drawText("STAGE " + stage_level,10, 80, textPaint);

        strokePaint.setColor(Color.BLACK);
        textPaint.setColor(Color.WHITE);

        strokePaint.setTextSize(100);
        textPaint.setTextSize(100);
        strokePaint.setTextAlign(Paint.Align.RIGHT);
        textPaint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText("" + player.coin,1080, 80, strokePaint);
        canvas.drawText("" + player.coin,1080, 80, textPaint);

        if(stage_level % 10 == shopStage)
        {
            shop.Draw(canvas);
            return;
        }
        damage.Draw(canvas);

        strokePaint.setColor(Color.WHITE);
        textPaint.setColor(Color.BLACK);

        strokePaint.setTextSize(120);
        textPaint.setTextSize(120);
        canvas.drawText("" + turn + "턴",1000, 700, strokePaint);
        canvas.drawText("" + turn + "턴",1000, 700, textPaint);

        strokePaint.setTextAlign(Paint.Align.LEFT);
        textPaint.setTextAlign(Paint.Align.LEFT);

        strokePaint.setColor(Color.BLACK);
        textPaint.setColor(Color.WHITE);

        strokePaint.setTextSize(150);
        textPaint.setTextSize(150);
        if(isStageClear || isMonsterAttack){
            canvas.drawText("-" , (1080-150) / 2, 700, strokePaint);
            canvas.drawText("-" , (1080-150) / 2, 700, textPaint);
        }
        else {
            canvas.drawText("" + (3 - (tileMap.nowTime - tileMap.readyTime) / 1000000000), (1080 - 150) / 2 + 20, 700, strokePaint);
            canvas.drawText("" + (3 - (tileMap.nowTime - tileMap.readyTime) / 1000000000), (1080 - 150) / 2 + 20, 700, textPaint);
        }
        strokePaint.setTextAlign(Paint.Align.LEFT);
        textPaint.setTextAlign(Paint.Align.LEFT);

        canvas.drawText("ATK : " + String.valueOf(player.getAtk()),420, 270, p);
        canvas.drawText("HP : " + String.valueOf(monster.getHp()), 420, 420, p);

        strokePaint.setTextSize(40);
        textPaint.setTextSize(40);
        canvas.drawText("앞으로",750, 600, strokePaint);
        canvas.drawText("앞으로",750, 600, textPaint);

        tileMap.draw(canvas);

        strokePaint.setTextSize(70);
        textPaint.setTextSize(70);
        canvas.drawText("" + tileMap.num_of_item1,130, 700, strokePaint);
        canvas.drawText("" + tileMap.num_of_item1,130, 700, textPaint);

        canvas.drawText("" + tileMap.num_of_item2,130 + 150, 700, strokePaint);
        canvas.drawText("" + tileMap.num_of_item2,130 + 150, 700, textPaint);

        monster.Draw(canvas);

        if(isPlayerAttack)
        {
            sp_CharacterAttack.Draw(canvas);
        }

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
        if(stage_level % 10 == shopStage){
            shop.onKeyDown(event);
        }
        if(isStageClear && event.getAction() == MotionEvent.ACTION_UP)
        {
            player.coin += stage_level * 7;
            player.coin += player.getAtk() - monster.hp;
            player.coin += turn * 5;

            stage_level++;
            monster = new Mon_Slime(stage_level);
            monster.SetPosition(0, 100);
            player.setATk(100);
            turn = 10;
            monster.say = 0;
            tileMap.reset();

            isStageClear = false;
            return false;
        }
        if(!isPlayerAttack) {
            tileMap.onTouch(event);
        }
        return false;
    }

    public static void setNoSoftKeyScreenInfo(Context context){
        DisplayMetrics dmath = context.getResources().getDisplayMetrics();
        SCREEN_WIDTH = dmath.widthPixels;
    }
}
