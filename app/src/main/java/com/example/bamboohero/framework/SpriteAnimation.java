package com.example.bamboohero.framework;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class SpriteAnimation extends GraphicObject {
    public SpriteAnimation(Bitmap bitmap) {
        super(bitmap);
        m_rect = new Rect(0, 0, 0, 0);
        m_frameTime = 0;
        m_currentFrame = 0;
    }

    private Rect m_rect;
    private int m_fps;
    private int m_noOfFrames;
    private int m_currentFrame;

    private long m_frameTime;

    private int m_spriteHeight;
    private int m_spriteWidth;

    protected boolean m_ready = true;
    protected boolean m_end = false;

    public void InitSpriteData(int height, int width, int fps, int frameCount){
        m_spriteHeight = height;
        m_spriteWidth = width;
        m_rect.top = 0;
        m_rect.bottom = m_spriteHeight;
        m_rect.left = 0;
        m_rect.right = m_spriteWidth;
        m_fps = 1000 / fps;             // 다음 스프라이트가 그려지기 까지의 간격
        m_noOfFrames = frameCount;
    }

    public void Draw(Canvas canvas) {
        Rect dest = new Rect(m_x, m_y, m_x + m_spriteWidth, m_y + m_spriteHeight);
        canvas.drawBitmap(m_bitmap, m_rect, dest, null);
    }

    public void Update(long gameTime) {
        if (gameTime > m_frameTime + m_fps) {
            m_frameTime = gameTime;
            m_currentFrame += 1;

            if (m_currentFrame >= m_noOfFrames) {
                m_currentFrame = 0;
            }
        }
        m_rect.left = m_currentFrame * m_spriteWidth;
        m_rect.right = m_rect.left + m_spriteWidth;
    }
}
