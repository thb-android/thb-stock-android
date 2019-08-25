package com.iot.automatic.home.fragment.list.presenter;

import com.iot.automatic.app.IOTErrorHandleSubscriber;
import com.iot.automatic.app.service.Result;
import com.iot.automatic.app.utils.pref.UserPreferences;
import com.iot.automatic.home.fragment.list.contract.ListContract;
import com.iot.automatic.service.entity.DeviceInfo;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;

import javax.inject.Inject;
import java.util.List;

import static com.iot.automatic.app.utils.pref.UserPreferences.KEY_USER_NAME;

@FragmentScope
public class ListPresenter extends BasePresenter<ListContract.Model, ListContract.View> {

    @Inject
    RxErrorHandler mErrorHandler;

    @Inject
    public ListPresenter(ListContract.Model model, ListContract.View view) {
        super(model, view);
    }

    public void getDevices() {
        final String userId = UserPreferences.getInstance().getString(KEY_USER_NAME, "");
        mModel.getDevices(userId)
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
                .subscribe(new IOTErrorHandleSubscriber<Result<List<DeviceInfo>>>(mErrorHandler) {
                    @Override
                    public void onResponse(Result<List<DeviceInfo>> temp) {
                        mRootView.onRefreshView();
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
    }

}
