package com.example.bamboohero.Game;

import android.app.usage.UsageEvents;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import com.example.bamboohero.R;
import com.example.bamboohero.framework.GraphicObject;
import com.example.bamboohero.framework.SpriteAnimation;

import java.util.ArrayList;

public class Shop {
    ArrayList<Integer> prise_of_mul = new ArrayList<Integer>();
    ArrayList<Integer> prise_of_sum = new ArrayList<Integer>();
    int prise_of_item1, num_of_item1;
    int prise_of_item2, num_of_item2;

    SpriteAnimation shoper = new SpriteAnimation(AppManager.getInstance().getBitmap(R.drawable.shop));
    GraphicObject shop_ui = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.shop_ui));

   public Shop(){
       prise_of_mul.add(500);
       prise_of_mul.add(1200);
       prise_of_mul.add(3200);
       prise_of_mul.add(5000);

       prise_of_sum.add(500);
       prise_of_sum.add(1000);
       prise_of_sum.add(1500);
       prise_of_sum.add(3000);

       prise_of_item1 = 1100;
       num_of_item1 = 3;

       prise_of_item2 = 1500;
       num_of_item2 = 2;

       shoper.SetPosition(0, 100);
       shoper.InitSpriteData(453,1112,2,2);
   }

   public void Draw(Canvas canvas){
       shoper.Draw(canvas);
       shop_ui.Draw(canvas);

       Paint p = AppManager.getInstance().getM_dungeon().p;

       p.setTextSize(70);
       canvas.drawText("+/- 배율 증가",100, 770, p);
       p.setTextSize(50);
       int min, max;
       min = AppManager.getInstance().m_dungeon.tileMap.sumCoefficients.get(AppManager.getInstance().m_dungeon.tileMap.nowSumCoefficient).min;
       max = AppManager.getInstance().m_dungeon.tileMap.sumCoefficients.get(AppManager.getInstance().m_dungeon.tileMap.nowSumCoefficient).max;
       canvas.drawText(min + "~" + max,100, 850, p);

       canvas.drawText("C: " + prise_of_sum.get(AppManager.getInstance().m_dungeon.tileMap.nowSumCoefficient),750, 750, p);
       canvas.drawRect(new Rect(750, 800 - 30,1000, 900 - 30), p);
       p.setColor(Color.WHITE);
       canvas.drawRect(new Rect(750 + 5, 800 - 30 + 5,1000 -5 , 900 - 30 -5), p);
       p.setColor(Color.BLACK);
       canvas.drawText("BUY", 850 - 20, 850 - 10, p);


       if(AppManager.getInstance().m_dungeon.tileMap.nowSumCoefficient < 5){
           canvas.drawText("->",375, 850, p);

           min = AppManager.getInstance().m_dungeon.tileMap.sumCoefficients.get(AppManager.getInstance().m_dungeon.tileMap.nowSumCoefficient + 1).min;
           max = AppManager.getInstance().m_dungeon.tileMap.sumCoefficients.get(AppManager.getInstance().m_dungeon.tileMap.nowSumCoefficient + 1).max;

           canvas.drawText(min + "~" + max,450, 850, p);
       }
       else {
           canvas.drawText("MAX",450, 850, p);
       }

       p.setTextSize(70);
       canvas.drawText("×/÷ 배율 증가",100, 1020, p);
       p.setTextSize(50);
       float fmin, fmax;
       min = AppManager.getInstance().m_dungeon.tileMap.mulCoefficients.get(AppManager.getInstance().m_dungeon.tileMap.nowMulCoefficient).min;
       max = AppManager.getInstance().m_dungeon.tileMap.mulCoefficients.get(AppManager.getInstance().m_dungeon.tileMap.nowMulCoefficient).max;
       fmin =  (float) ((min % 2) * 0.5 )+ (int)(min / 2);
       fmax =  (float) ((max % 2) * 0.5 )+ (int)(max / 2);
       canvas.drawText(fmin + "~" + fmax,100, 1100, p);
       if(AppManager.getInstance().m_dungeon.tileMap.nowSumCoefficient < 5){
           canvas.drawText("->",375, 1100, p);
           min = AppManager.getInstance().m_dungeon.tileMap.mulCoefficients.get(AppManager.getInstance().m_dungeon.tileMap.nowMulCoefficient + 1).min;
           max = AppManager.getInstance().m_dungeon.tileMap.mulCoefficients.get(AppManager.getInstance().m_dungeon.tileMap.nowMulCoefficient + 1).max;
           fmin =  (float) ((min % 2) * 0.5 )+ (int)(min / 2);
           fmax =  (float) ((max % 2) * 0.5 )+ (int)(max / 2);
           canvas.drawText(fmin + "~" + fmax,450, 1100, p);
       }
       else {
           canvas.drawText("MAX",450, 1050, p);
       }

       canvas.drawText("C: " + prise_of_mul.get(AppManager.getInstance().m_dungeon.tileMap.nowMulCoefficient),750, 1000, p);
       canvas.drawRect(new Rect(750, 1050 - 30,1000, 1150 - 30), p);
       p.setColor(Color.WHITE);
       canvas.drawRect(new Rect(750 + 5, 1050 - 30 + 5,1000 -5 , 1150 - 30 -5), p);
       p.setColor(Color.BLACK);
       canvas.drawText("BUY", 850 - 20, 1100 - 10, p);

       canvas.drawRect(new Rect(415, 1650,415 + 250, 1750 ), p);
       p.setColor(Color.WHITE);
       canvas.drawRect(new Rect(415 + 5, 1650 + 5,415 + 250 -5 , 1750 -5), p);
       p.setColor(Color.BLACK);
       canvas.drawText("나가기", 415 + 100 - 45, 1720 - 10, p);

       p.setTextSize(60);
       canvas.drawText("아이템: 3턴 증가",100 + 150, 1020 + 250 - 20, p);
       AppManager.getInstance().m_dungeon.tileMap.item1.Draw(canvas, 100 - 30, 1020 + 250 - 120);
       p.setTextSize(40);
       canvas.drawText("소지 갯수: " + AppManager.getInstance().m_dungeon.tileMap.num_of_item1,250, 1100 + 250 -20, p);

       if(num_of_item1 > 0)
           canvas.drawText("C: " + (prise_of_item1 - (num_of_item1 * 200)),750, 1000 + 230, p);
       else
           canvas.drawText("SOLD OUT",750, 1000 + 230, p);
       canvas.drawRect(new Rect(750, 1050 - 30 + 230,1000, 1150 - 30 + 230), p);
       p.setColor(Color.WHITE);
       canvas.drawRect(new Rect(750 + 5, 1050 - 30 + 5 + 230,1000 -5 , 1150 - 30 -5 + 230), p);
       p.setColor(Color.BLACK);
       canvas.drawText("BUY", 850 - 20, 1100 - 10 + 230, p);

       p.setTextSize(60);
       canvas.drawText("아이템: 타일 리셋",100 + 150, 1020 + 500 - 20, p);
       AppManager.getInstance().m_dungeon.tileMap.item2.Draw(canvas, 100 - 30, 1020 + 500 - 120);
       p.setTextSize(40);
       canvas.drawText("소지 갯수: " + AppManager.getInstance().m_dungeon.tileMap.num_of_item2,250, 1100 + 500 -20, p);

       if(num_of_item2 > 0)
           canvas.drawText("C: " + (prise_of_item2 - (num_of_item2 * 500)),750, 1000 + 480, p);
       else
           canvas.drawText("SOLD OUT",750, 1000 + 480, p);
       canvas.drawRect(new Rect(750, 1050 - 30 + 480,1000, 1150 - 30 + 480), p);
       p.setColor(Color.WHITE);
       canvas.drawRect(new Rect(750 + 5, 1050 - 30 + 5 + 480,1000 -5 , 1150 - 30 -5 + 480), p);
       p.setColor(Color.BLACK);
       canvas.drawText("BUY", 850 - 20, 1100 - 10 + 480, p);
   }

   public void Update(long GameTime){
       shoper.Update(GameTime);
   }

   public void Refill(){
       num_of_item2 = 2;
       num_of_item1 = 3;
   }

   public void onKeyDown(MotionEvent event){
       if(event.getAction() == MotionEvent.ACTION_UP) {
           int x = (int) event.getX();
           int y = (int) event.getY();
           Log.i("누른다 ? ", "눌럿다!!");

           Rect sum = new Rect(750, 800 - 30, 1000, 900 - 30);
           Rect mul = new Rect(750, 1050 - 30, 1000, 1150 - 30);

           if (sum.left < x && x < sum.right && sum.top < y && y < sum.bottom) {
               if (prise_of_sum.get(AppManager.getInstance().m_dungeon.tileMap.nowSumCoefficient) <= AppManager.getInstance().m_dungeon.player.coin) {
                   AppManager.getInstance().m_dungeon.player.coin -= prise_of_sum.get(AppManager.getInstance().m_dungeon.tileMap.nowSumCoefficient);
                   AppManager.getInstance().m_dungeon.tileMap.nowSumCoefficient++;
               }
           }

           if (mul.left < x && x < mul.right && mul.top < y && y < mul.bottom) {
               if (prise_of_mul.get(AppManager.getInstance().m_dungeon.tileMap.nowMulCoefficient) <= AppManager.getInstance().m_dungeon.player.coin) {
                   AppManager.getInstance().m_dungeon.player.coin -= prise_of_mul.get(AppManager.getInstance().m_dungeon.tileMap.nowMulCoefficient);
                   AppManager.getInstance().m_dungeon.tileMap.nowMulCoefficient++;
               }
           }

           Rect exit = new Rect(415, 1650,415 + 250, 1750 );
           if (exit.left < x && x < exit.right && exit.top < y && y < exit.bottom) {
               AppManager.getInstance().m_dungeon.stage_level++;
               Refill();
           }

           Rect item1 = new Rect(750, 1050 - 30 + 230,1000, 1150 - 30 + 230);
           Rect item2 = new Rect(750, 1050 - 30 + 480,1000, 1150 - 30 + 480);

           if (item1.left < x && x < item1.right && item1.top < y && y < item1.bottom) {
               if (num_of_item1 > 0 && (prise_of_item1 - (num_of_item1 * 200) <= AppManager.getInstance().m_dungeon.player.coin)) {
                   AppManager.getInstance().m_dungeon.player.coin -= prise_of_item1 - (num_of_item1 * 200);
                   num_of_item1--;
                   AppManager.getInstance().m_dungeon.tileMap.num_of_item1++;
               }
           }
           if (item2.left < x && x < item2.right && item2.top < y && y < item2.bottom) {
               if (num_of_item2 > 0 && (prise_of_item2 - (num_of_item2 * 500) <= AppManager.getInstance().m_dungeon.player.coin)) {
                   AppManager.getInstance().m_dungeon.player.coin -= prise_of_item2 - (num_of_item2 * 500);
                   num_of_item2--;
                   AppManager.getInstance().m_dungeon.tileMap.num_of_item2++;
               }
           }
       }
   }
}
