package com.iot.automatic.home.activity.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.iot.automatic.IOTBaseActivity;
import com.iot.automatic.R;
import com.iot.automatic.app.ui.TabFragmentHost;
import com.iot.automatic.home.activity.DaggerHomeComponent;
import com.iot.automatic.home.activity.contract.HomeContract;
import com.iot.automatic.home.activity.presenter.HomePresenter;
import com.iot.automatic.home.fragment.list.view.ListFragment;
import com.iot.automatic.home.fragment.map.view.MapFragment;
import com.iot.automatic.home.fragment.mine.view.MineFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import static com.iot.automatic.service.arouter.ARouterPath.HOME_ACTIVITY;
import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 05/17/2019 10:50
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Route(path = HOME_ACTIVITY)
public class HomeActivity extends IOTBaseActivity<HomePresenter> implements HomeContract.View, TabHost.OnTabChangeListener {

    private final int[] tabs = {
            R.string.home_tab_location,
            R.string.home_tab_list,
//            R.string.home_tab_msg,
            R.string.home_tab_mine
    };
    private final int[] tabImgsNormal = {
            R.drawable.app_menu_home_normal,
            R.drawable.app_menu_list_normal,
//            R.drawable.ic_home_tab_msg_normal,
            R.drawable.app_menu_mine_normal
    };
    private final int[] tabImgsPressed = {
            R.drawable.app_menu_home_pressed,
            R.drawable.app_menu_list_pressed,
//            R.drawable.ic_home_tab_msg_pressed,
            R.drawable.app_menu_mine_pressed
    };
    private final Class[] clz = {
            MapFragment.class,
            ListFragment.class,
//            MapFragment.class,
            MineFragment.class
    };

    private TabFragmentHost tabHost = null;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerHomeComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_home; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        tabHost = findViewById(android.R.id.tabhost);
        tabHost.setup(this, super.getSupportFragmentManager(), R.id.contentLayout);
        tabHost.getTabWidget().setDividerDrawable(null);
        tabHost.setOnTabChangedListener(this);
        initTab();
    }

    private void initTab() {
        for (int i = 0; i < tabs.length; i++) {
            final String tabTxt = ArmsUtils.getString(this, tabs[i]);
            final TabHost.TabSpec tabSpec = tabHost.newTabSpec(tabTxt).setIndicator(getTabView(i));
            tabHost.addTab(tabSpec, clz[i], null);
            tabHost.setTag(i);
        }
    }

    private View getTabView(int idx) {
        View view = LayoutInflater.from(this).inflate(R.layout.footer_tabs_layout, null);
        final TextView tabTxt = view.findViewById(R.id.footer_tab_txt);
        tabTxt.setText(ArmsUtils.getString(this, tabs[idx]));
        if (idx == 0) {
            tabTxt.setTextColor(ArmsUtils.getColor(this, R.color.light_bLue));
            ((ImageView) view.findViewById(R.id.footer_tab_img)).setImageResource(tabImgsPressed[idx]);
        } else {
            ((ImageView) view.findViewById(R.id.footer_tab_img)).setImageResource(tabImgsNormal[idx]);
        }
        return view;
    }

    @Override
    public void onTabChanged(String tabId) {
        TabWidget tabWidget = tabHost.getTabWidget();
        final int size = tabWidget.getChildCount();
        for (int i = 0; i < size; i++) {
            View view = tabWidget.getChildAt(i);
            TextView tabTxt = view.findViewById(R.id.footer_tab_txt);
            ImageView tabImg = view.findViewById(R.id.footer_tab_img);
            if (i == tabHost.getCurrentTab()) {
                tabTxt.setTextColor(ArmsUtils.getColor(this, R.color.light_bLue));
                tabImg.setImageResource(tabImgsPressed[i]);
            } else {
                tabTxt.setTextColor(ArmsUtils.getColor(this, R.color.dark_gray));
                tabImg.setImageResource(tabImgsNormal[i]);
            }
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }

}
