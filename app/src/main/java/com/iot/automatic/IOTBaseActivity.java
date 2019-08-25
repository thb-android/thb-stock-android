package com.iot.automatic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.alibaba.android.arouter.utils.TextUtils;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.mvp.IPresenter;
import com.jess.arms.utils.ArmsUtils;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class IOTBaseActivity<P extends IPresenter> extends BaseActivity<P> {

    protected static final List<IOTBaseActivity> activities = Collections.synchronizedList(new ArrayList<>());

    private BasePopupView loadingPopup;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activities.add(this);
    }

    public boolean isUseCommonTitle() {
        return true;
    }

    public int getTitleRightIcon() {
        return -1;
    }

    public int getTitleBg() {
        return R.color.light_bLue;
    }

    public void onRightTitleIconClick() {

    }

    /**
     * 显示加载
     */
    public void showLoading() {
        if (null == loadingPopup) {
            loadingPopup = new XPopup.Builder(this)
                    .dismissOnBackPressed(false)
                    .dismissOnTouchOutside(false)
                    .asLoading();
        }
        loadingPopup.show();
    }

    /**
     * 隐藏加载
     */
    public void hideLoading() {
        if (null != loadingPopup) {
            loadingPopup.dismiss();
        }
    }

    public void showMessage(@NonNull String message) {
        if (!TextUtils.isEmpty(message)) {
            ArmsUtils.snackbarText(message);
        }
    }

    public void launchActivity(@NonNull Intent intent) {
        // TODO: 2019-05-30 暂时不作处理
    }

    public static IOTBaseActivity getTopActivity() {
        try {
            synchronized (activities) {
                if (!activities.isEmpty()) {
                    return activities.get(activities.size() - 1);
                }
            }

            return null;
        } catch (Exception var6) {
            return null;
        }
    }

    /**
     * kill所有的activity
     */
    public static void killAllActivity() {
        killAllActivity(null);
    }

    /**
     * kill所有的activity，除了传入的activity
     */
    public static void killAllActivity(IOTBaseActivity exceptActivity) {
        while (!activities.isEmpty()) {
            Activity a = activities.remove(0);
            if (a == null || exceptActivity != null && a.getClass() == exceptActivity.getClass()) {
                continue;
            }
            a.finish();
        }
    }

    @Override
    public void finish() {
        super.finish();
        activities.remove(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activities.remove(this);
    }
}
