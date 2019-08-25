package com.iot.automatic.home.fragment.map.presenter;

import android.app.Application;
import com.iot.automatic.app.IOTErrorHandleSubscriber;
import com.iot.automatic.app.utils.Utils;
import com.iot.automatic.home.fragment.map.contract.MapContract;
import com.iot.automatic.home.fragment.map.entity.SinaStockInfo;
import com.iot.automatic.service.CommonService;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;
import okhttp3.ResponseBody;

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

    public void testSina() {
        mModel.getSinal(CommonService.SINA_URL + "sz002307,sh600928")
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .doOnSubscribe(disposable -> {
                    mRootView.showLoading();//显示进度条
                })
                .doFinally(() -> {
                    mRootView.hideLoading();//隐藏下拉刷新的进度条
                })
                .subscribe(new IOTErrorHandleSubscriber<ResponseBody>(mErrorHandler) {
                    @Override
                    public void onResponse(ResponseBody response) {
                        List<SinaStockInfo> infos = Utils.pares2Stock(response.byteStream());
                    }
                });
    }


}
