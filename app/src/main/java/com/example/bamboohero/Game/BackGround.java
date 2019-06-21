package com.example.bamboohero.Game;

import android.graphics.Canvas;

import com.example.bamboohero.R;
import com.example.bamboohero.framework.GraphicObject;

public class BackGround extends GraphicObject {

    static final int TYPE_CAVE = 0;
    static final int TYPE_FOREST = 1;
    static final int TYPE_CASTLE = 2;

    public BackGround(int stage_type){
        super(null);
        super.SetPosition(0, 100);
        m_bitmap = AppManager.getInstance().getBitmap(R.drawable.map_cave);
    }

    public void Draw(Canvas canvas) {
        canvas.drawBitmap(m_bitmap, m_x, m_y, null);
    }
}
