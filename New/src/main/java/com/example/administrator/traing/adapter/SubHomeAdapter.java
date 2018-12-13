package com.example.administrator.traing.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.traing.R;
import com.example.administrator.traing.domain.SubBanner;

import java.util.List;

/**
 * Created by Administrator on 2017/8/26.
 */

public class SubHomeAdapter extends RecyclerView.Adapter<SubHomeAdapter.ViewHolder> implements View.OnClickListener {

    private Context context;
    private List<SubBanner.DataBean.CateBean> list;
    private OnItemClickListener mOnItemClickListener = null;

    public SubHomeAdapter(Context context, List<SubBanner.DataBean.CateBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v, (int) v.getTag());
        }
    }

    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_rv_sub, parent, false);
        ViewHolder viewHolder = new ViewHolder(inflate);
        inflate.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.title.setText(list.get(position).getName());

        switch (position) {
            case 0:

                Glide.with(context)
                        .load(R.drawable.news)
                        .centerCrop()
                        .crossFade()
                        .into(holder.img);

                break;
            case 1:

                Glide.with(context)
                        .load(R.drawable.video)
                        .centerCrop()
                        .crossFade()
                        .into(holder.img);

                break;
            case 2:

                Glide.with(context)
                        .load(R.drawable.book)
                        .centerCrop()
                        .crossFade()
                        .into(holder.img);

                break;
            case 3:

                Glide.with(context)
                        .load(R.drawable.business)
                        .centerCrop()
                        .crossFade()
                        .into(holder.img);

                break;
            case 4:

                Glide.with(context)
                        .load(R.drawable.tec)
                        .centerCrop()
                        .crossFade()
                        .into(holder.img);

                break;
            case 5:

                Glide.with(context)
                        .load(R.drawable.life)
                        .centerCrop()
                        .crossFade()
                        .into(holder.img);

                break;

            case 6:

                Glide.with(context)
                        .load(R.drawable.science)
                        .centerCrop()
                        .crossFade()
                        .into(holder.img);

                break;

            case 7:

                Glide.with(context)
                        .load(R.drawable.real)
                        .centerCrop()
                        .crossFade()
                        .into(holder.img);

                break;

        }


        holder.itemView.setTag(position);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private ImageView img;
        private View view;


        ViewHolder(View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.v_bg);
            img = (ImageView) itemView.findViewById(R.id.iv_rv_sub);
            title = (TextView) itemView.findViewById(R.id.tv_rv_title);
        }
    }


}
