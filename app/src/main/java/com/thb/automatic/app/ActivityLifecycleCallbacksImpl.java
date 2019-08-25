package com.thb.automatic.app;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.thb.automatic.IOTBaseActivity;
import com.thb.automatic.R;
import com.jess.arms.utils.ArmsUtils;
import timber.log.Timber;

/**
 * ================================================
 * 展示 {@link Application.ActivityLifecycleCallbacks} 的用法
 * <p>
 * Created by MVPArmsTemplate on 05/14/2019 00:49
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public class ActivityLifecycleCallbacksImpl implements Application.ActivityLifecycleCallbacks {

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        Timber.i(activity + " - onActivityCreated");
    }

    @Override
    public void onActivityStarted(Activity activity) {
        Timber.i(activity + " - onActivityStarted");
        if (activity instanceof IOTBaseActivity) {
            final IOTBaseActivity baseActivity = (IOTBaseActivity) activity;
            if (baseActivity.isUseCommonTitle()) {
                final View titleLayout = activity.findViewById(R.id.iot_title);
                if (null != titleLayout) {
                    final int color = baseActivity.getTitleBg();
                    titleLayout.setBackgroundColor(ArmsUtils.getColor(activity, color));
                }

                final TextView title = activity.findViewById(R.id.iot_title_title);
                if (null != title) {
                    title.setText(activity.getTitle());
                }

                final View back = activity.findViewById(R.id.iot_title_back);
                if (null != back) {
                    back.setOnClickListener(v -> {
                        activity.onBackPressed();
                    });
                }

                final ImageView right = activity.findViewById(R.id.iot_title_right_icon);

                if (right != null && baseActivity.getTitleRightIcon() != -1) {
                    right.setVisibility(View.VISIBLE);
                    right.setImageResource(baseActivity.getTitleRightIcon());
                    right.setOnClickListener(v -> {
                        baseActivity.onRightTitleIconClick();
                    });
                }
            }
        }
    }

    @Override
    public void onActivityResumed(Activity activity) {
        Timber.i(activity + " - onActivityResumed");
    }

    @Override
    public void onActivityPaused(Activity activity) {
        Timber.i(activity + " - onActivityPaused");
    }

    @Override
    public void onActivityStopped(Activity activity) {
        Timber.i(activity + " - onActivityStopped");
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        Timber.i(activity + " - onActivitySaveInstanceState");
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        Timber.i(activity + " - onActivityDestroyed");
        //横竖屏切换或配置改变时, Activity 会被重新创建实例, 但 Bundle 中的基础数据会被保存下来,移除该数据是为了保证重新创建的实例可以正常工作
        activity.getIntent().removeExtra("isInitToolbar");
    }
}
