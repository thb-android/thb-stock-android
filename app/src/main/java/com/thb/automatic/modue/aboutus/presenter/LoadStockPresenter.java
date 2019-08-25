package com.thb.automatic.modue.aboutus.presenter;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.thb.automatic.modue.aboutus.contract.LoadStockContract;
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
public class LoadStockPresenter extends BasePresenter<LoadStockContract.Model, LoadStockContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;

    @Inject
    public LoadStockPresenter(LoadStockContract.Model model, LoadStockContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
    }
}
