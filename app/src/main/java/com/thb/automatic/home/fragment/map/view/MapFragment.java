package com.thb.automatic.home.fragment.map.view;

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
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.thb.automatic.R;
import com.thb.automatic.app.ui.MarginItemDecoration;
import com.thb.automatic.home.fragment.map.DaggerMapComponent;
import com.thb.automatic.home.fragment.map.contract.MapContract;
import com.thb.automatic.home.fragment.map.entity.StockInfo;
import com.thb.automatic.home.fragment.map.presenter.MapPresenter;

import java.util.ArrayList;
import java.util.List;

public class MapFragment extends BaseFragment<MapPresenter> implements MapContract.View {

    private View mContentView;

    @BindView(R.id.map_help)
    TextView mHelp;
    @BindView(R.id.map_recycler)
    RecyclerView mRecyclerView;
    ViewAdapter mAdapter;

    private final List<StockInfo> mData = new ArrayList<>();

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerMapComponent
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null == mContentView) {
            mContentView = inflater.inflate(R.layout.fragment_home_map, container, false);
        }

        ViewGroup parent = (ViewGroup) mContentView.getParent();
        if (parent != null) {
            parent.removeView(mContentView);
        }

        return mContentView;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mAdapter = new ViewAdapter(mContext, mData);
        final int color = ArmsUtils.getColor(mContext, R.color.light_gray);
        mRecyclerView.addItemDecoration(new MarginItemDecoration(mContext, color));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(mAdapter);
        mPresenter.loadData();
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    public void updateView(List<StockInfo> infos) {
        mHelp.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        mData.addAll(infos);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateView(String error) {
        mHelp.setText(error);
    }
}
