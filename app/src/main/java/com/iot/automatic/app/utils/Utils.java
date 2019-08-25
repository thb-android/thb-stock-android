package com.iot.automatic.app.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.iot.automatic.app.Common;
import com.iot.automatic.home.fragment.map.entity.SinaStockInfo;
import com.jess.arms.utils.ArmsUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public final class Utils {

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

    public static List<SinaStockInfo> pares2Stock(InputStream is) {
        InputStreamReader reader = new InputStreamReader(new BufferedInputStream(is), Charset.forName("gbk"));
        BufferedReader bReader = new BufferedReader(reader);

        List<SinaStockInfo> list = parseSinaStockInfosFromReader(bReader);
        try {
            bReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static List<SinaStockInfo> parseSinaStockInfosFromReader(BufferedReader reader) {
        ArrayList<SinaStockInfo> list = new ArrayList<SinaStockInfo>(10);
        String sourceLine = null;

        try {
            while ((sourceLine = reader.readLine()) != null) {
                list.add(SinaStockInfo.parseStockInfo(sourceLine));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
