package com.thb.automatic.home.fragment.map.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.thb.automatic.R;
import com.thb.automatic.app.ui.MarginItemDecoration;
import com.thb.automatic.app.utils.Utils;
import com.thb.automatic.home.fragment.map.DaggerMapComponent;
import com.thb.automatic.home.fragment.map.contract.MapContract;
import com.thb.automatic.home.fragment.map.entity.StockInfo;
import com.thb.automatic.home.fragment.map.presenter.MapPresenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;

public class MapFragment extends BaseFragment<MapPresenter> implements MapContract.View {

    private View mContentView;

    @BindView(R.id.check_no_top_line)
    CheckBox mCheckBox;
    @BindView(R.id.percent_edit)
    EditText mPercentEdit;
    @BindView(R.id.date_30)
    CheckBox mDate30;
    @BindView(R.id.date_30_percent_edit)
    EditText mDatePercentEdit;
    @BindView(R.id.check_low_price)
    CheckBox mLowPrice;
    @BindView(R.id.map_result)
    TextView mHelp;
    @BindView(R.id.map_query_btn)
    View mBtnQuery;
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
        mBtnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mData.clear();
                mAdapter.notifyDataSetChanged();
                mPresenter.loadData(mPercentEdit.getText().toString(), mCheckBox.isChecked(), mDatePercentEdit.getText().toString(), mDate30.isChecked(), mLowPrice.isChecked());
                mPercentEdit.setText(null);
                Utils.hideSoftKeyboard(v);
            }
        });
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    public void updateView(List<StockInfo> infos) {
        mRecyclerView.setVisibility(View.VISIBLE);
        mData.addAll(infos);
        Collections.sort(mData, (o1, o2) -> Double.compare(o1.changepercent, o2.changepercent));
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateView(String error) {
        mHelp.setText(error);
    }

    @Override
    public void resetListView(List<StockInfo> infos) {
        mRecyclerView.setVisibility(View.VISIBLE);
        mData.clear();
        mData.addAll(infos);
        Collections.sort(mData, (o1, o2) -> Double.compare(o1.changepercent, o2.changepercent));
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public List<StockInfo> getStockInfos() {
        return mData;
    }
}
