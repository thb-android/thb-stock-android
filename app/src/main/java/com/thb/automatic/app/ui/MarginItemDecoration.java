package com.thb.automatic.app.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.jess.arms.utils.ArmsUtils;

/**
 * RecyclerView分割线，没有margin_left和margin_right
 */
public class MarginItemDecoration extends RecyclerView.ItemDecoration {

    private int dividerHeight;
    private Paint dividerPaint;
    private int margin;

    public MarginItemDecoration(Context context, int color) {
        dividerPaint = new Paint();
        dividerPaint.setColor(color);
        dividerHeight = 1;
        margin = ArmsUtils.dip2px(context, 16);
    }

    /**
     * @param outRect 边界
     * @param view    recyclerView ItemView
     * @param parent  recyclerView
     * @param state   recycler 内部数据管理
     */
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        //设定底部边距为1px
        outRect.set(0, 0, 0, 1);
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int childCount = parent.getChildCount();
        int left = parent.getPaddingLeft() + margin;
        int right = parent.getWidth() - parent.getPaddingRight() - margin;

        for (int i = 0; i < childCount - 1; i++) {
            View view = parent.getChildAt(i);
            float top = view.getBottom();
            float bottom = view.getBottom() + dividerHeight;
            c.drawRect(left, top, right, bottom, dividerPaint);
        }
    }
}