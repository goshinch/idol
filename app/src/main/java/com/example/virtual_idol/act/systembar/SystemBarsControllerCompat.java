package com.example.virtual_idol.act.systembar;

import android.view.Window;

import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import com.example.virtual_idol.components.LOG;

public class SystemBarsControllerCompat extends WindowController implements LOG {
    private Window window;
    private WindowInsetsControllerCompat controllerCompat;
    private static SystemBarsControllerCompat instance;

    public static SystemBarsControllerCompat getIstance() {
        if (instance == null)
            instance = new SystemBarsControllerCompat();
        return instance;
    }

    @Override
    public WindowInsetsControllerCompat windowController(Window window) {
        //해당 액티비티의 윈도우를 전체화면으로,, 정확히는 시스템 바의 뒤에 영역까지 포함해서 전체 화면으로 설정한다.
        WindowCompat.setDecorFitsSystemWindows(window, false);
        //제어 인스턴스 생성
        if (this.window != window) {
            this.window = window;
            controllerCompat = new WindowInsetsControllerCompat(window, window.getDecorView());
        }
        return controllerCompat;
    }
}
