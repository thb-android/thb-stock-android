package com.iot.automatic.app.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.iot.automatic.app.Common;
import com.jess.arms.utils.ArmsUtils;

import java.util.List;

import static java.lang.Math.PI;

public final class Utils {
    private static double AXIS = 6378245.0;  //
    private static double OFFSET = 0.00669342162296594323;  //(a^2 - b^2) / a^2
    private static double X_PI = PI * 3000.0 / 180.0;


    private Utils() {
    }

    public static boolean isEmpty(List list) {
        return null == list || list.isEmpty();
    }

    public static String getString(int id) {
        return ArmsUtils.getResources(Common.getInstance().getContext()).getString(id);
    }

    public static String getString(int id, Object... formatArgs) {
        return ArmsUtils.getResources(Common.getInstance().getContext()).getString(id, formatArgs);
    }

    public static void hideSoftKeyboard(View v) {
        final Context ctx = Common.getInstance().getContext();
        InputMethodManager imm = (InputMethodManager) ctx.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0); // 强制隐藏键盘
    }

    /**
     * 获取本地app版本号
     *
     * @param context
     * @return
     */
    public static String getAppVersion(Context context) {
        PackageManager pm = context.getPackageManager();
        String version = null;
        try {
            version = pm.getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }

}
