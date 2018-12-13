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
import com.example.administrator.traing.db.AccountSubDao;
import com.example.administrator.traing.db.LoginSiteDao;
import com.example.administrator.traing.db.LoginSiteSub;
import com.example.administrator.traing.db.NewsSubDao;
import com.example.administrator.traing.utlis.CacheUtils;
import com.example.administrator.traing.utlis.SnackBarUtil;
import com.example.administrator.traing.view.GlideCircleTransform;

import java.util.List;

/**
 * Created by Administrator on 2017/9/17.
 */

public class LoginSubListAdapter extends BaseAdapter {
    private final LayoutInflater inflater;
    private final String login;
    private Context context;
    private List<LoginSiteSub> listBeen;
    private NewsSubDao newsSubDao;
    private AccountSubDao accountSubDao;
    private LoginSiteDao loginSiteDao;


    public LoginSubListAdapter(Context context, List<LoginSiteSub> listBeen) {

        this.context = context;
        this.listBeen = listBeen;
        inflater = LayoutInflater.from(context);
        newsSubDao = new NewsSubDao(context);
        accountSubDao = new AccountSubDao(context);
        loginSiteDao = new LoginSiteDao(context);
        login = CacheUtils.getString(context, "login");

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

        final LoginSiteSub loginSiteSub = listBeen.get(position);


        holder.tvTitle.setText(loginSiteSub.getName());
        holder.tvNewsDesc.setText(loginSiteSub.getDescription());

        if (loginSiteSub.getType() != null) {

            holder.ivSiteClass.setText(loginSiteSub.getType());

        }

        Glide.with(context)
                .load(loginSiteSub.getPic())
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

                loginSiteDao.deleteBySiteId(loginSiteSub.getSite_id(), login);

                accountSubDao.deleteBySiteId(loginSiteSub.getSite_id(), login);

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
