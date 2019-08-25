package com.iot.automatic.modue.aboutus.presenter;

import com.iot.automatic.app.IOTErrorHandleSubscriber;
import com.iot.automatic.modue.aboutus.contract.AboutUsContract;
import com.iot.automatic.modue.aboutus.entity.AboutUsEntity;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 05/15/2019 19:52
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
public class AboutUsPresenter extends BasePresenter<AboutUsContract.Model, AboutUsContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;

    @Inject
    public AboutUsPresenter(AboutUsContract.Model model, AboutUsContract.View rootView) {
        super(model, rootView);
    }

    public void getSetData() {
        mModel.getAboutUsData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .doOnSubscribe(disposable -> {
                    mRootView.showLoading();
                })
                .doFinally(() -> {
                    mRootView.hideLoading();
                })
                .subscribe(new IOTErrorHandleSubscriber<AboutUsEntity>(mErrorHandler) {
                    @Override
                    public void onResponse(AboutUsEntity entity) {
                        mRootView.updateView(entity);
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
    }
}
