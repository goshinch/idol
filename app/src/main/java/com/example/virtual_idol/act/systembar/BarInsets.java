package com.example.virtual_idol.act.systembar;

import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

public interface BarInsets {
    int NAVIGATION_BARS = WindowInsetsCompat.Type.navigationBars();
    int STATUS_BAR = WindowInsetsCompat.Type.statusBars();
    int CAPTION_BAR = WindowInsetsCompat.Type.captionBar();
    int IME = WindowInsetsCompat.Type.ime();

    default void systemBarsController(WindowInsetsControllerCompat controller) {
        //가져온 제어 컴펫인스턴스의 hide 메소드로 네비바를 숨긴다.
        controller.hide(NAVIGATION_BARS);
        //스와이프 했을때 일시적으로 나타나는 변수
        controller.setSystemBarsBehavior(controller.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
    }
}


