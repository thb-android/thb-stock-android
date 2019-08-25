package com.iot.automatic.home.fragment.map.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.iot.automatic.R;
import com.iot.automatic.home.fragment.map.DaggerMapComponent;
import com.iot.automatic.home.fragment.map.contract.MapContract;
import com.iot.automatic.home.fragment.map.presenter.MapPresenter;
import com.iot.automatic.service.event.MapLayerSelectEvent;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MapFragment extends BaseFragment<MapPresenter> implements MapContract.View, View.OnClickListener {

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerMapComponent
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_map, null);
    }


    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        final View view = getView();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Subscribe
    public void onEvent(MapLayerSelectEvent event) {
        if (event.type == MapLayerSelectEvent.HOME_MAP_TYPE) {
        }
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View v) {

    }

}
