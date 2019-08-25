package com.iot.automatic.home.fragment.mine.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.iot.automatic.R;
import com.iot.automatic.home.fragment.mine.DaggerMineComponent;
import com.iot.automatic.home.fragment.mine.contract.MineContract;
import com.iot.automatic.home.fragment.mine.entity.MineEntity;
import com.iot.automatic.home.fragment.mine.presenter.MinePresenter;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;

public class MineFragment extends BaseFragment<MinePresenter> implements MineContract.View {

    private View mContentView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerMineComponent
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null == mContentView) {
            mContentView = inflater.inflate(R.layout.fragment_home_mine, container, false);
        }

        ViewGroup parent = (ViewGroup) mContentView.getParent();
        if (parent != null) {
            parent.removeView(mContentView);
        }
        return mContentView;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void updateView(MineEntity entity) {
    }

    @Override
    public void showMessage(@NonNull String message) {

    }

}
