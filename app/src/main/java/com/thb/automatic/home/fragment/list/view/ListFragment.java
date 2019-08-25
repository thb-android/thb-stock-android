package com.thb.automatic.home.fragment.list.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.thb.automatic.R;
import com.thb.automatic.home.fragment.list.DaggerListComponent;
import com.thb.automatic.home.fragment.list.contract.ListContract;
import com.thb.automatic.home.fragment.list.presenter.ListPresenter;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;

public class ListFragment extends BaseFragment<ListPresenter> implements ListContract.View, View.OnClickListener {
    private View mContentView;
    private boolean isNeedShowLoading = true;

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerListComponent
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null == mContentView) {
            mContentView = inflater.inflate(R.layout.fragment_home_list, container, false);
        }

        ViewGroup parent = (ViewGroup) mContentView.getParent();
        if (parent != null) {
            parent.removeView(mContentView);
        }
        return mContentView;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mPresenter.getDevices();
    }

    @Override
    public void onRefreshView() {
    }

    @Override
    public void onClick(View v) {
    }


    @Override
    public void showLoading() {
        if (isNeedShowLoading) {
            isNeedShowLoading = false;
        }
    }

    @Override
    public void hideLoading() {
    }

    @Override
    public void setData(@Nullable Object data) {
    }

    @Override
    public void showMessage(@NonNull String message) {
    }

}
