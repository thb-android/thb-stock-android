package com.iot.automatic.home.fragment.mine.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.android.arouter.utils.TextUtils;
import com.iot.automatic.R;
import com.iot.automatic.home.fragment.mine.entity.MineEntity.MineItem;

import java.util.List;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.CustomHolder> {

    private Context mContext;
    private List<MineItem> mData;

    public ViewAdapter(Context context, List<MineItem> data) {
        this.mContext = context;
        this.mData = data;
    }

    @NonNull
    @Override
    public CustomHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.mine_item_layout, parent, false);
        return new CustomHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomHolder holder, int i) {
        final MineItem item = mData.get(i);
        holder.text.setText(item.text);
        holder.icon.setImageResource(item.imgId);
        holder.itemView.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(item.aRouterPath)) {
                ARouter.getInstance().build(item.aRouterPath).navigation();
            }
        });
    }

    @Override
    public int getItemCount() {
        return null == mData ? 0 : mData.size();
    }

    class CustomHolder extends RecyclerView.ViewHolder {

        private ImageView icon;
        private TextView text;

        CustomHolder(View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.mine_item_img);
            text = itemView.findViewById(R.id.mine_item_txt);
        }

    }
}
