package com.example.bamboohero.Game;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.example.bamboohero.R;
import com.example.bamboohero.framework.GameView;
import com.example.bamboohero.framework.GraphicObject;
import com.example.bamboohero.framework.IState;
import com.example.bamboohero.framework.MainActivity;
import com.example.bamboohero.framework.SpriteAnimation;

public class TitleState implements IState {
    SpriteAnimation title = new SpriteAnimation(AppManager.getInstance().getBitmap(R.drawable.title));

    GraphicObject start = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.start));
    GraphicObject end = new GraphicObject(AppManager.getInstance().getBitmap(R.drawable.end));

    @Override
    public void Init() {
        title.InitSpriteData( 299*3, 287 * 3, 2, 2);
        title.SetPosition(109, 200);
    }

    @Override
    public void Destroy() {

    }

    @Override
    public void Update() {
        long gametime = System.currentTimeMillis();
        title.Update(gametime);
    }

    @Override
    public void Render(Canvas canvas) {
        title.Draw(canvas);
        start.Draw(canvas, 273, 1000 + 150);
        end.Draw(canvas, 273, 1250 + 150);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            int x, y;
            x = (int) event.getX();
            y = (int) event.getY();

            Rect r_start = new Rect(273, 1150, 273+178*3 , 1150 + 54 * 3);
            Rect r_end = new Rect(273, 1250 + 150, 273+178*3, 1250 + 150 + 54 * 3);

            if(r_start.left < x && x < r_start.right && r_start.top < y && y < r_start.bottom)
                AppManager.getInstance().getGameView().ChageGameState(new DungeonState());
            else if(r_end.left < x && x < r_end.right && r_end.top < y && y < r_end.bottom) {
                return false;
            }
        }
        return false;
    }
}
