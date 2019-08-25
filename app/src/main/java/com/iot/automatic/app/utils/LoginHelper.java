package com.iot.automatic.app.utils;

import com.alibaba.android.arouter.launcher.ARouter;
import com.iot.automatic.app.utils.pref.UserPreferences;

import static com.iot.automatic.app.utils.pref.UserPreferences.KEY_TOKEN;
import static com.iot.automatic.service.arouter.ARouterPath.LOGIN_ACTIVITY;

/**
 * <p><p>
 * <p><p>
 * <p>作者：tanghuaibao<p>
 * <p>创建时间：2019-07-20<p>
 * <p>更改时间：2019-07-20<p>
 * <p>版本号：v1.0.0<p>
 */
public final class LoginHelper {

    public static void login() {
        ARouter.getInstance().build(LOGIN_ACTIVITY).navigation();
    }

    public static void loginOut() {
        UserPreferences.getInstance().putString(KEY_TOKEN, null);
        ARouter.getInstance().build(LOGIN_ACTIVITY).navigation();
    }

}
