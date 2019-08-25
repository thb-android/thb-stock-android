package com.iot.automatic;

import android.support.multidex.MultiDex;
import com.iot.automatic.app.Common;
import com.jess.arms.base.BaseApplication;

public class MainApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        Common.getInstance().setContext(this);
    }
}
