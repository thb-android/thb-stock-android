package com.thb.automatic.app.utils.pref;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.alibaba.android.arouter.utils.TextUtils;
import com.thb.automatic.app.Common;

public abstract class BasePreferences {

    private final SharedPreferences mPref;
    private final Editor mEditor;

    abstract String createSpName();

    public BasePreferences() {
        final Context ctx = Common.getInstance().getContext();
        String name = createSpName();
        if (TextUtils.isEmpty(name)) {
            name = getClass().getSimpleName();
        }
        mPref = ctx.getSharedPreferences(name, Context.MODE_PRIVATE);
        mEditor = mPref.edit();
    }

    public boolean contains(String key) {
        return mPref.contains(key);
    }

    public void putString(String key, String value) {
        if (!TextUtils.isEmpty(value)) {
            mEditor.putString(key, value);
        } else {
            mEditor.remove(key);
        }
        mEditor.apply();
    }

    public String getString(String key, String defValue) {
        return mPref.getString(key, defValue);
    }

    public int getInt(String key, int defValue) {
        return mPref.getInt(key, defValue);
    }

    public void putInt(String key, int value) {
        mEditor.putInt(key, value);
        mEditor.apply();
    }

    public float getFloat(String key, float defValue) {
        return mPref.getFloat(key, defValue);
    }

    public void putFloat(String key, float value) {
        mEditor.putFloat(key, value);
        mEditor.apply();
    }

    public boolean getBoolean(String key, boolean defValue) {
        return mPref.getBoolean(key, defValue);
    }

    public void putBoolean(String key, boolean value) {
        mEditor.putBoolean(key, value);
        mEditor.apply();
    }

    public long getLong(String key, long defValue) {
        return mPref.getLong(key, defValue);
    }

    public void putLong(String key, long value) {
        mEditor.putLong(key, value);
        mEditor.apply();
    }

    public void remove(String key) {
        mEditor.remove(key);
        mEditor.apply();
    }

    public void clear() {
        mEditor.clear();
        mEditor.apply();
    }
}
