package com.example.bamboohero.Game;

        import android.graphics.Bitmap;

        import com.example.bamboohero.framework.SpriteAnimation;

public class Player extends SpriteAnimation {

    int atk;

    public Player(Bitmap bitmap) {
        super(bitmap);
    }

    public void setATk(int changeAtk) { atk = changeAtk; }
    public int getAtk() { return atk; }
}
