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
import com.example.administrator.traing.db.AccountSub;
import com.example.administrator.traing.db.AccountSubDao;
import com.example.administrator.traing.db.NewsSub;
import com.example.administrator.traing.db.NewsSubDao;
import com.example.administrator.traing.domain.RecommendList;
import com.example.administrator.traing.utlis.CacheUtils;
import com.example.administrator.traing.utlis.SnackBarUtil;
import com.example.administrator.traing.view.GlideCircleTransform;

import java.util.List;

/**
 * Created by Administrator on 2017/9/14.
 */

public class MyAllSubSiteAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private Context context;
    private List<RecommendList.DataBean.ListBean> list;
    private boolean isSub = false;
    private NewsSubDao newsSubDao;
    private AccountSubDao accountSubDao;
    private AccountSub accountCollect;
    private RecommendList.DataBean.ListBean listBean;

    public MyAllSubSiteAdapter(Context context, List<RecommendList.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
        newsSubDao = new NewsSubDao(context);
        accountSubDao = new AccountSubDao(context);
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
    public View getView(final int position, View itemView, ViewGroup parent) {

        Holder holder = null;

        if (itemView == null) {

            holder = new Holder();
            itemView = inflater.inflate(R.layout.adapter_sub_all, parent, false);
            holder.tvTitle = (TextView) itemView.findViewById(R.id.tv_title_recommend);
            holder.tvNewsDesc = (TextView) itemView.findViewById(R.id.tv_news_desc);
            holder.ivNewsFrom = (ImageView) itemView.findViewById(R.id.iv_news_from);
            holder.btnSub = (ImageButton) itemView.findViewById(R.id.btn_sub);
            holder.tv_title_class = (TextView) itemView.findViewById(R.id.tv_title_class);

            itemView.setTag(holder);
        } else {

            holder = (Holder) itemView.getTag();

        }

        final RecommendList.DataBean.ListBean listBean = list.get(position);

        holder.tvTitle.setText(listBean.getName());
        holder.tvNewsDesc.setText(listBean.getBrief());

        if (position == 0) {

            holder.tv_title_class.setText("精选");

        } else {

            holder.tv_title_class.setText(listBean.getCate_info().get(0).getName());

        }

        Glide.with(context)
                .load(listBean.getPic())
                .crossFade()
                .centerCrop()
                .transform(new GlideCircleTransform(context))
                .into(holder.ivNewsFrom);

        holder.btnSub.setBackground(context.getResources().getDrawable(R.drawable.ic_add_circle_outline_black_48dp));

        showSub(holder, listBean);

        final View finalItemView = itemView;
        holder.btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSub = v.getBackground().getCurrent().getConstantState() == context.getResources().getDrawable(R.drawable.ic_add_circle_black_48dp).getConstantState();

                if (!isSub) {

                    isSub = true;

                    SnackBarUtil.showTSnack("订阅成功", finalItemView, R.drawable.ic_info_black_24dp, Color.parseColor("#fafafa"));

                    if (CacheUtils.getString(context, "login_type").equals("1")) {

                        if (newsSubDao.whereAdd(list.get(position).getId())) {

                            NewsSub sub = new NewsSub();
                            sub.setSite(list.get(position).getId());
                            newsSubDao.addMySub(sub);

                        }

                    } else if (CacheUtils.getString(context, "login_type").equals("2")) {

                        if (accountSubDao.whereAdd(list.get(position).getId(), CacheUtils.getString(context, "login"))) {

                            accountCollect = new AccountSub();
                            accountCollect.setName(CacheUtils.getString(context, "login"));
                            accountCollect.setSite_id(list.get(position).getId());
                            accountSubDao.addMyAccount(accountCollect);

                        }

                    }

                    v.setBackground(context.getResources().getDrawable(R.drawable.ic_add_circle_black_48dp));


                } else {

                    isSub = false;

                    if (CacheUtils.getString(context, "login_type").equals("1")) {

                        newsSubDao.deleteByTitle(listBean.getId());
                        v.setBackground(context.getResources().getDrawable(R.drawable.ic_add_circle_outline_black_48dp));

                    } else if (CacheUtils.getString(context, "login_type").equals("2")) {

                        accountSubDao.deleteBySiteId(listBean.getId(), CacheUtils.getString(context, "login"));
                        v.setBackground(context.getResources().getDrawable(R.drawable.ic_add_circle_outline_black_48dp));

                    }

                    SnackBarUtil.showTSnack("取消订阅", finalItemView, R.drawable.ic_info_black_24dp, Color.parseColor("#fafafa"));

                }

            }
        });

        return itemView;
    }

    private void showSub(Holder holder, RecommendList.DataBean.ListBean listBean) {
        switch (CacheUtils.getString(context, "login_type")) {

            case "1":

                if (newsSubDao.querySubSite(listBean.getId()).size() > 0) {

                    isSub = true;
                    holder.btnSub.setBackground(context.getResources().getDrawable(R.drawable.ic_add_circle_black_48dp));

                } else {

                    isSub = false;
                    holder.btnSub.setBackground(context.getResources().getDrawable(R.drawable.ic_add_circle_outline_black_48dp));

                }

                break;

            case "2":

                if (accountSubDao.querySubSite(listBean.getId(), CacheUtils.getString(context, "login")).size() > 0) {

                    isSub = true;
                    holder.btnSub.setBackground(context.getResources().getDrawable(R.drawable.ic_add_circle_black_48dp));

                } else {

                    isSub = false;
                    holder.btnSub.setBackground(context.getResources().getDrawable(R.drawable.ic_add_circle_outline_black_48dp));

                }

                break;

        }


    }

    private class Holder {

        private TextView tvTitle;
        private TextView tvNewsDesc;
        private ImageView ivNewsFrom;
        private ImageButton btnSub;
        private TextView tv_title_class;

    }
}
