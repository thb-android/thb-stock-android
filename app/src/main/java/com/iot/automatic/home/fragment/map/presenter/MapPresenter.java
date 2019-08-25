package com.iot.automatic.home.fragment.map.presenter;

import android.app.Application;
import com.iot.automatic.home.fragment.map.contract.MapContract;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.mvp.BasePresenter;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;
import java.util.List;

@FragmentScope
public class MapPresenter extends BasePresenter<MapContract.Model, MapContract.View> {

    @Inject
    List<String> tempData;
    @Inject
    Application mApplication;
    @Inject
    RxErrorHandler mErrorHandler;

    @Inject
    public MapPresenter(MapContract.Model model, MapContract.View view) {
        super(model, view);
    }

}
