package com.example.bamboohero.framework;

import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;

public interface IState {
    // 상태 생성
    public void Init();
    //상태 소명
    public void Destroy();
    // 지속적으로 수행
    public void Update();
    // 화면 그리기
    public void Render(Canvas canvas);
    // 키 입력 처리
    public boolean onKeyDown(int keyCode, KeyEvent event);
    // 터치 입력 처리
    public boolean onTouchEvent(MotionEvent event);
}
