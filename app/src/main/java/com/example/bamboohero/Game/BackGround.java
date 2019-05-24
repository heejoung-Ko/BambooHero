package com.example.bamboohero.Game;

import android.graphics.Canvas;

import com.example.bamboohero.R;
import com.example.bamboohero.framework.GraphicObject;

public class BackGround extends GraphicObject {

    public BackGround(int backtype){
        super(null);
        m_bitmap = AppManager.getInstance().getBitmap(R.drawable.map_cave);
    }

    public void Draw(Canvas canvas) {
        canvas.drawBitmap(m_bitmap, m_x, m_y, null);
    }
}
