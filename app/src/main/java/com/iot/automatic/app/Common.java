package com.iot.automatic.app;

import android.content.Context;

public class Common {

    private static Common mInstance;
    private Context context;

    private Common() {

    }

    public static synchronized Common getInstance() {
        if (null == mInstance) {
            mInstance = new Common();
        }
        return mInstance;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

}
