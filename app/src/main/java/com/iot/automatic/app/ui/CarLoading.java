package com.iot.automatic.app.ui;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import com.iot.automatic.R;

public class CarLoading extends FrameLayout {

    private View mProgress;

    public CarLoading(@NonNull Context context) {
        super(context);
        init(context);
    }

    public CarLoading(@NonNull Context context, @NonNull AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CarLoading(@NonNull Context context, @NonNull AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CarLoading(@NonNull Context context, @NonNull AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.car_loading_layout, this);
        mProgress = findViewById(R.id.car_loading_progress);
    }

    public void showLoading() {
        this.setVisibility(VISIBLE);
        ObjectAnimator rotation = ObjectAnimator.ofFloat(mProgress, "rotation", 0f, 359f);

        rotation.setDuration(1000);
        rotation.setRepeatCount(ObjectAnimator.INFINITE);
        rotation.setInterpolator(new LinearInterpolator());
        rotation.start();
    }

    public void stopLoading() {
        mProgress.clearAnimation();
        this.setVisibility(View.GONE);
    }

}
