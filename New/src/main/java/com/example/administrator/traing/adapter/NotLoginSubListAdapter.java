package com.example.administrator.traing.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.traing.R;
import com.example.administrator.traing.db.NewsSubDao;
import com.example.administrator.traing.db.NotLoginSub;
import com.example.administrator.traing.db.NotLoginSubDao;
import com.example.administrator.traing.utlis.SnackBarUtil;
import com.example.administrator.traing.view.GlideCircleTransform;

import java.util.List;

/**
 * Created by Administrator on 2017/9/17.
 */

public class NotLoginSubListAdapter extends BaseAdapter {
    private final LayoutInflater inflater;
    private Context context;
    private List<NotLoginSub> listBeen;
    private NewsSubDao newsSubDao;
    private NotLoginSubDao notLoginSubDao;

    public NotLoginSubListAdapter(Context context, List<NotLoginSub> listBeen) {

        this.context = context;
        this.listBeen = listBeen;
        inflater = LayoutInflater.from(context);
        newsSubDao = new NewsSubDao(context);
        notLoginSubDao = new NotLoginSubDao(context);

    }

    @Override
    public int getCount() {
        return listBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return listBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View itemView, ViewGroup parent) {

        Holder holder = null;

        if (itemView == null) {

            holder = new Holder();
            itemView = inflater.inflate(R.layout.adapter_sub_all, parent, false);
            holder.tvTitle = (TextView) itemView.findViewById(R.id.tv_title_recommend);
            holder.tvNewsDesc = (TextView) itemView.findViewById(R.id.tv_news_desc);
            holder.ivNewsFrom = (ImageView) itemView.findViewById(R.id.iv_news_from);
            holder.btnSub = (ImageButton) itemView.findViewById(R.id.btn_sub);
            holder.ivSiteClass = (TextView) itemView.findViewById(R.id.tv_title_class);

            itemView.setTag(holder);

        } else {

            holder = (Holder) itemView.getTag();

        }

        final NotLoginSub notLoginSub = listBeen.get(position);


        holder.tvTitle.setText(notLoginSub.getName());
        holder.tvNewsDesc.setText(notLoginSub.getDescription());

        if (notLoginSub.getType() != null) {

            holder.ivSiteClass.setText(notLoginSub.getType());

        }

        Glide.with(context)
                .load(notLoginSub.getPic())
                .crossFade()
                .centerCrop()
                .transform(new GlideCircleTransform(context))
                .into(holder.ivNewsFrom);

        holder.btnSub.setBackground(context.getResources().getDrawable(R.drawable.ic_add_circle_black_48dp));

        final View finalItemView = itemView;

        holder.btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SnackBarUtil.showTSnack("取消订阅", finalItemView, R.drawable.ic_info_black_24dp, Color.parseColor("#fafafa"));

                v.setBackground(context.getResources().getDrawable(R.drawable.ic_add_circle_outline_black_48dp));

                newsSubDao.deleteByTitle(notLoginSub.getSite_id());

                notLoginSubDao.deleteByName(notLoginSub.getName());

                listBeen.remove(position);

                notifyDataSetChanged();

            }
        });

        return itemView;
    }

    private class Holder {

        private TextView tvTitle;
        private TextView tvNewsDesc;
        private ImageView ivNewsFrom;
        private TextView ivSiteClass;
        private ImageButton btnSub;

    }

}
