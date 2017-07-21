package com.swetabh.whetherapidemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.swetabh.whetherapidemo.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by swets on 22-07-2017.
 */

public class WhetherAdapter extends RecyclerView.Adapter<WhetherAdapter.ViewHolder> {

    private final Context mContext;
    private List<com.swetabh.whetherapidemo.models.List> mDataList;

    public WhetherAdapter(Context context, List<com.swetabh.whetherapidemo.models.List> dataList) {
        mContext = context;
        mDataList = dataList;
    }

    @Override
    public WhetherAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_whether_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WhetherAdapter.ViewHolder holder, int position) {
        com.swetabh.whetherapidemo.models.List model = mDataList.get(position);
        holder.vhHumidity.setText(mContext.getString(R.string.humidity, model.getMain().getHumidity()));
        holder.vhPressure.setText(mContext.getString(R.string.pressure, model.getMain().getPressure()));
        holder.vhDescription.setText(mContext.getString(R.string.description, model.getWeather().get(0).getDescription()));
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.humidity)
        TextView vhHumidity;
        @BindView(R.id.pressure)
        TextView vhPressure;
        @BindView(R.id.description)
        TextView vhDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
