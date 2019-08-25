package com.iot.automatic.home.fragment.mine.presenter;

import android.app.Application;
import com.iot.automatic.app.IOTErrorHandleSubscriber;
import com.iot.automatic.home.fragment.mine.contract.MineContract;
import com.iot.automatic.home.fragment.mine.entity.MineEntity;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

@FragmentScope
public class MinePresenter extends BasePresenter<MineContract.Model, MineContract.View> {

    @Inject
    Application mApplication;
    @Inject
    RxErrorHandler mErrorHandler;

    @Inject
    public MinePresenter(MineContract.Model model, MineContract.View view) {
        super(model, view);
    }

    public void getMineData() {
        mModel.getTempData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .doOnSubscribe(disposable -> {
                    mRootView.showLoading();
                })
                .doFinally(() -> {
                    mRootView.hideLoading();
                })
                .subscribe(new IOTErrorHandleSubscriber<MineEntity>(mErrorHandler) {
                    @Override
                    public void onResponse(MineEntity entity) {
                        mRootView.updateView(entity);
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mApplication = null;
    }

}
