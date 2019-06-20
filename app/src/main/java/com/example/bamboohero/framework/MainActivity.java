package com.example.bamboohero.framework;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Typeface tf1 = Typeface.create(Typeface.createFromAsset(getAssets(), "font/dot_font_1.ttf"), Typeface.BOLD);    // 폰트1
        Typeface tf2 = Typeface.createFromAsset(getAssets(), "font/garosero_r.ttf");    // 폰트2
        setContentView(new GameView(this, tf1, tf2));
    }
}
