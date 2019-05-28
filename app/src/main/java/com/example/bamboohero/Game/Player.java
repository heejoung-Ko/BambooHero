package com.example.bamboohero.Game;

        import android.graphics.Bitmap;

        import com.example.bamboohero.R;
        import com.example.bamboohero.framework.SpriteAnimation;

public class Player extends SpriteAnimation {

    int atk;

    public Player( ) {
        super(AppManager.getInstance().getBitmap(R.drawable.test_r));
    }

    public void setATk(int changeAtk) { atk = changeAtk; }
    public int getAtk() { return atk; }
}
