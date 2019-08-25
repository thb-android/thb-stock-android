package com.iot.automatic.home.fragment.mine.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import com.alibaba.android.arouter.launcher.ARouter;
import com.iot.automatic.R;
import com.iot.automatic.app.ui.MarginItemDecoration;
import com.iot.automatic.app.utils.pref.UserPreferences;
import com.iot.automatic.home.fragment.mine.DaggerMineComponent;
import com.iot.automatic.home.fragment.mine.contract.MineContract;
import com.iot.automatic.home.fragment.mine.entity.MineEntity;
import com.iot.automatic.home.fragment.mine.entity.MineEntity.MineItem;
import com.iot.automatic.home.fragment.mine.presenter.MinePresenter;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.ArrayList;
import java.util.List;

import static com.iot.automatic.app.utils.pref.UserPreferences.KEY_ACCOUNT;
import static com.iot.automatic.service.arouter.ARouterPath.PERSONAL_ACTIVITY;

public class MineFragment extends BaseFragment<MinePresenter> implements MineContract.View {

    @BindView(R.id.mine_user_photo)
    View mUserPhoto;
    @BindView(R.id.mine_user_count)
    TextView mUserCount;
    @BindView(R.id.mine_recycler)
    RecyclerView mRecyclerView;
    ViewAdapter mAdapter;

    private final List<MineItem> mData = new ArrayList<>();

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
        return inflater.inflate(R.layout.fragment_home_mine, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mUserCount.setText(UserPreferences.getInstance().getString(KEY_ACCOUNT, null));
        mAdapter = new ViewAdapter(mContext, mData);
        final int color = ArmsUtils.getColor(mContext, R.color.light_gray);
        mRecyclerView.addItemDecoration(new MarginItemDecoration(mContext, color));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(mAdapter);
        mPresenter.getMineData();
        mUserPhoto.setOnClickListener(v -> ARouter.getInstance().build(PERSONAL_ACTIVITY).navigation());
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void updateView(MineEntity entity) {
        mData.clear();
        mData.addAll(entity.items);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(@NonNull String message) {

    }

}
