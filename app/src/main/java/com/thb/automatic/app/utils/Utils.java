package com.thb.automatic.app.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.jess.arms.utils.ArmsUtils;
import com.thb.automatic.app.Common;
import com.thb.automatic.home.fragment.map.entity.StockInfo;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

    public static List<StockInfo> pares2Stock(InputStream is) {
        InputStreamReader reader = new InputStreamReader(new BufferedInputStream(is), Charset.forName("gbk"));
        BufferedReader bReader = new BufferedReader(reader);

        List<StockInfo> list = parseSinaStockInfosFromReader(bReader);
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

    private static List<StockInfo> parseSinaStockInfosFromReader(BufferedReader reader) {
        ArrayList<StockInfo> list = new ArrayList<StockInfo>(10);
        String sourceLine = null;

        try {
            while ((sourceLine = reader.readLine()) != null) {
                list.add(StockInfo.parseStockInfo(sourceLine));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static String getDirectory() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "a_thb";
    }

    public static String getDateDirectory() {
        return getDirectory() + File.separator + "date";
    }

    public static String getFileByDate(String date) {
        return getDateDirectory() + File.separator + date + ".txt";
    }

    public static String getFileByName(String name) {
        return getDateDirectory() + File.separator + name;
    }
}
