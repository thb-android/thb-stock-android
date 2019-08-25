package com.iot.automatic.home.fragment.map.view;

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
import com.iot.automatic.home.fragment.map.entity.OperationEntity;

import java.util.List;

import static com.iot.automatic.app.service.Constant.DEVICE_ID;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.CustomHolder> {

    private Context mContext;
    private List<OperationEntity> mData;
    private String mDeviceId;

    public ViewAdapter(Context context, List<OperationEntity> data) {
        this.mContext = context;
        this.mData = data;
    }

    @NonNull
    @Override
    public CustomHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.home_map_operation_item_layout, parent, false);
        return new CustomHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomHolder holder, int i) {
        holder.operation.setText(mData.get(i).title);
        holder.icon.setImageResource(mData.get(i).icon);
        final String route = mData.get(i).route;
        holder.itemView.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(route)) {
                ARouter.getInstance().build(route).withString(DEVICE_ID, mDeviceId).navigation();
            }
        });
    }

    @Override
    public int getItemCount() {
        return null == mData ? 0 : mData.size();
    }

    public void setDeviceId(String deviceId) {
        this.mDeviceId = deviceId;
    }

    class CustomHolder extends RecyclerView.ViewHolder {

        private TextView operation;
        private ImageView icon;

        CustomHolder(View itemView) {
            super(itemView);
            operation = itemView.findViewById(R.id.home_map_item_operation);
            icon = itemView.findViewById(R.id.home_map_item_operation_icon);
        }
    }
}
