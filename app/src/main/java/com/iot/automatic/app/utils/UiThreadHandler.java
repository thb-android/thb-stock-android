package com.iot.automatic.app.utils;

import android.os.Handler;
import android.os.Looper;

public final class UiThreadHandler {

    private UiThreadHandler() {
    }

    private static Handler uiHandler = new Handler(Looper.getMainLooper());

    public static boolean post(Runnable r) {
        return uiHandler.post(r);
    }

    public static boolean postDelayed(Runnable r, long delayMillis) {
        return uiHandler.postDelayed(r, delayMillis);
    }

    public static boolean postOnceDelayed(Runnable r, long delayMillis) {
        uiHandler.removeCallbacks(r);
        return uiHandler.postDelayed(r, delayMillis);
    }

    public static void removeCallbacks(Runnable runnable) {
        uiHandler.removeCallbacks(runnable);
    }

    public static Handler getUiHandler() {
        return uiHandler;
    }
}
