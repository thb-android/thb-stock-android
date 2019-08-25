package com.thb.automatic.home.fragment.list.presenter;

import com.thb.automatic.home.fragment.list.contract.ListContract;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.mvp.BasePresenter;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

@FragmentScope
public class ListPresenter extends BasePresenter<ListContract.Model, ListContract.View> {

    @Inject
    RxErrorHandler mErrorHandler;

    @Inject
    public ListPresenter(ListContract.Model model, ListContract.View view) {
        super(model, view);
    }

    public void getDevices() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
    }

}
