package com.example.administrator.traing.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.traing.R;
import com.example.administrator.traing.domain.Weather;

import java.util.List;

/**
 * Created by Administrator on 2017/9/13.
 */

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.Holder> {

    private Context context;
    private List<Weather.ResultBean.FutureBean> list;

    public WeatherAdapter(Context context, List<Weather.ResultBean.FutureBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_weather, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        Weather.ResultBean.FutureBean futureBean = list.get(position);

        holder.tvWeek.setText(futureBean.getWeek());
        holder.tvNight.setText(futureBean.getNight());
        holder.tvTemperature.setText(futureBean.getTemperature());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        private TextView tvWeek;
        private TextView tvTemperature;
        private TextView tvNight;

        Holder(View itemView) {
            super(itemView);

            tvWeek = (TextView) itemView.findViewById(R.id.tv_week);
            tvNight = (TextView) itemView.findViewById(R.id.tv_night);
            tvTemperature = (TextView) itemView.findViewById(R.id.tv_temperature);

        }
    }

}
