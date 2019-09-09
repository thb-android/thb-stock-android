package com.thb.automatic.home.fragment.list.view;

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
import butterknife.BindView;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.thb.automatic.R;
import com.thb.automatic.app.ui.MarginItemDecoration;
import com.thb.automatic.home.fragment.list.DaggerListComponent;
import com.thb.automatic.home.fragment.list.contract.ListContract;
import com.thb.automatic.home.fragment.list.presenter.ListPresenter;
import com.thb.automatic.home.fragment.map.entity.StockInfo;
import com.thb.automatic.home.fragment.map.view.ViewAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListFragment extends BaseFragment<ListPresenter> implements ListContract.View, View.OnClickListener {
    private View mContentView;

    @BindView(R.id.check_doji)
    CheckBox mCheckBox;
    @BindView(R.id.list_percent_edit)
    EditText mPercentEdit;
    @BindView(R.id.list_result)
    TextView mHelp;
    @BindView(R.id.list_query_btn)
    View mBtnQuery;
    @BindView(R.id.list_recycler)
    RecyclerView mRecyclerView;
    ViewAdapter mAdapter;

    private final List<StockInfo> mData = new ArrayList<>();

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
        mAdapter = new ViewAdapter(mContext, mData);
        final int color = ArmsUtils.getColor(mContext, R.color.light_gray);
        mRecyclerView.addItemDecoration(new MarginItemDecoration(mContext, color));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(mAdapter);
        mBtnQuery.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.list_query_btn) {
            mData.clear();
            mAdapter.notifyDataSetChanged();
            mPresenter.loadData(mPercentEdit.getText().toString(), mCheckBox.isChecked());
            mPercentEdit.setText(null);
        }
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

}
