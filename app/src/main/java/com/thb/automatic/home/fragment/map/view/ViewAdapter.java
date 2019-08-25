package com.thb.automatic.home.fragment.map.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.alibaba.android.arouter.launcher.ARouter;
import com.thb.automatic.R;
import com.thb.automatic.home.fragment.map.entity.StockInfo;

import java.util.List;

import static com.thb.automatic.app.service.Constant.key_stock_symbol;
import static com.thb.automatic.service.arouter.ARouterPath.K_LINE_ACTIVITY;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.CustomHolder> {

    private Context mContext;
    private List<StockInfo> mData;

    public ViewAdapter(Context context, List<StockInfo> data) {
        this.mContext = context;
        this.mData = data;
    }

    @NonNull
    @Override
    public CustomHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.map_item_layout, parent, false);
        return new CustomHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomHolder holder, int i) {
        final StockInfo item = mData.get(i);
        holder.text.setText(String.format("%s   %s", item.name, item.symbol));
        holder.itemView.setOnClickListener(v -> {
            ARouter.getInstance().build(K_LINE_ACTIVITY).withString(key_stock_symbol, item.symbol).navigation();
        });
    }

    @Override
    public int getItemCount() {
        return null == mData ? 0 : mData.size();
    }

    class CustomHolder extends RecyclerView.ViewHolder {

        private TextView text;

        CustomHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.map_item_txt);
        }

    }
}
