package com.example.administrator.traing.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.administrator.traing.R;
import com.example.administrator.traing.domain.Common;
import com.example.administrator.traing.utlis.DensityUtils;
import com.example.administrator.traing.view.GlideCircleTransform;

import java.util.List;

/**
 * Created by Administrator on 2017/8/25.
 */

public class TabHomeAdapter extends BaseAdapter {
    private final LayoutInflater inflater;
    private Context context;
    private List<Common.DataBean.ListBean> list;

    public static final int HOME_ITEM = 0;
    public static final int OTHER_ITEM = 1;


    public TabHomeAdapter(Context context, List<Common.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0) {
            return HOME_ITEM;
        } else {
            return OTHER_ITEM;
        }
    }


    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        Common.DataBean.ListBean listBean = list.get(position);
        switch (getItemViewType(position)) {

            case HOME_ITEM:

                Holder holder = null;

                if (convertView == null) {

                    holder = new Holder();

                    convertView = inflater.inflate(R.layout.adapter_tab_frist_item, parent, false);

                    holder.tvTitleRecommend = (TextView) convertView.findViewById(R.id.tv_title_recommend);
                    holder.tvNewsDesc = (TextView) convertView.findViewById(R.id.tv_news_desc);
                    holder.iv1 = (ImageView) convertView.findViewById(R.id.iv_1);
                    holder.ivNewsFrom = (ImageView) convertView.findViewById(R.id.iv_news_from);
                    holder.tvNewsFrom = (TextView) convertView.findViewById(R.id.tv_news_from);

                    convertView.setTag(holder);

                } else {

                    holder = (Holder) convertView.getTag();

                }

                holder.tvTitleRecommend.setText(listBean.getTitle());
                holder.tvNewsDesc.setText(listBean.getBrief());
                holder.tvNewsFrom.setText(listBean.getSite_info().getName());

                Glide.with(context)
                        .load(listBean.getHeadpic())
                        .error(R.mipmap.h)
                        .crossFade()
                        .into(holder.iv1);

                Glide.with(context)
                        .load(listBean.getSite_info().getPic())
                        .transform(new GlideCircleTransform(context))
                        .into(holder.ivNewsFrom);


                break;

            case OTHER_ITEM:

                Holder holder2 = null;

                if (convertView == null) {

                    holder = new Holder();

                    convertView = inflater.inflate(R.layout.adapter_tab_common, parent, false);

                    holder.tvTitleRecommend = (TextView) convertView.findViewById(R.id.tv_title_recommend);
                    holder.tvNewsDesc = (TextView) convertView.findViewById(R.id.tv_news_desc);
                    holder.iv1 = (ImageView) convertView.findViewById(R.id.iv_1);
                    holder.iv2 = (ImageView) convertView.findViewById(R.id.iv_2);
                    holder.iv3 = (ImageView) convertView.findViewById(R.id.iv_3);
                    holder.ivNewsFrom = (ImageView) convertView.findViewById(R.id.iv_news_from);
                    holder.tvNewsFrom = (TextView) convertView.findViewById(R.id.tv_news_from);

                    convertView.setTag(holder);

                } else {

                    holder = (Holder) convertView.getTag();

                }


                holder.tvTitleRecommend.setText(listBean.getTitle());
                holder.tvNewsDesc.setText(listBean.getBrief());
                holder.tvNewsFrom.setText(listBean.getSite_info().getName());


                Glide.with(context)
                        .load(listBean.getPrepic1())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .override(DensityUtils.dip2px(context, 75), DensityUtils.dip2px(context, 75))
                        .into(holder.iv1);

                Glide.with(context)
                        .load(listBean.getPrepic2())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .override(DensityUtils.dip2px(context, 75), DensityUtils.dip2px(context, 75))
                        .into(holder.iv2);

                Glide.with(context)
                        .load(listBean.getPrepic3())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .override(DensityUtils.dip2px(context, 75), DensityUtils.dip2px(context, 75))
                        .into(holder.iv3);

                Glide.with(context)
                        .load(listBean.getSite_info().getPic())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .transform(new GlideCircleTransform(context))
                        .into(holder.ivNewsFrom);

                break;
        }

        return convertView;
    }

    private static class Holder {

        private TextView tvTitleRecommend;
        private TextView tvNewsDesc;
        private ImageView iv1;
        private ImageView iv2;
        private ImageView iv3;
        private ImageView ivNewsFrom;
        private TextView tvNewsFrom;

    }


}
