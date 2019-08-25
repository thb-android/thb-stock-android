package com.thb.automatic.home.fragment.map.presenter;

import android.app.Application;
import android.util.Log;
import com.thb.automatic.app.IOTErrorHandleSubscriber;
import com.thb.automatic.app.utils.Utils;
import com.thb.automatic.home.fragment.map.contract.MapContract;
import com.thb.automatic.home.fragment.map.entity.StockInfo;
import com.thb.automatic.service.CommonService;
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
                        List<StockInfo> infos = Utils.pares2Stock(response.byteStream());
                        mRootView.updateView(infos);
                    }
                });
    }


}
