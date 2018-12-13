package com.example.administrator.traing.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.traing.R;
import com.example.administrator.traing.db.AccountSub;
import com.example.administrator.traing.db.AccountSubDao;
import com.example.administrator.traing.db.NewsSub;
import com.example.administrator.traing.db.NewsSubDao;
import com.example.administrator.traing.domain.RecommendList;
import com.example.administrator.traing.utlis.CacheUtils;
import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * Created by Administrator on 2017/8/28.
 */

public class SubscriptionAdapter extends RecyclerView.Adapter<SubscriptionAdapter.Holder> implements View.OnClickListener {

    private final LayoutInflater inflater;
    private Context context;
    private List<RecommendList.DataBean.ListBean> list;
    private OnItemClickListener mOnItemClickListener = null;
    private NewsSubDao newsSubDao;

    private boolean isSub = false;
    private View mRootView;
    private RecommendList.DataBean.ListBean listBean;
    private AccountSubDao accountSubDao;
    private AccountSub accountCollect;

    public SubscriptionAdapter(Context context, List<RecommendList.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
        newsSubDao = new NewsSubDao(context);
        accountSubDao = new AccountSubDao(context);

    }

    @Override
    public SubscriptionAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        mRootView = inflater.inflate(R.layout.adapter_subscription, parent, false);
        Holder holder = new Holder(mRootView);
        mRootView.setOnClickListener(this);
        return holder;

    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
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
    public void onBindViewHolder(final SubscriptionAdapter.Holder holder, final int position) {

        isSub = false;

        final RecommendList.DataBean.ListBean listBean = list.get(position);

        holder.tvTitle.setText(listBean.getName());
        holder.tvNewsDesc.setText(listBean.getBrief());

        Glide.with(context)
                .load(listBean.getPic())
                .crossFade()
                .centerCrop()
                .into(holder.ivNewsFrom);
        holder.add.setBackground(context.getResources().getDrawable(R.drawable.ic_add_circle_outline_black_48dp));


        showSub(holder, listBean);


        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isSub = holder.add.getBackground().getCurrent().getConstantState() == context.getResources().getDrawable(R.drawable.ic_add_circle_black_48dp).getConstantState();

                if (!isSub) {

                    isSub = true;

                    Toast.makeText(context, "订阅成功！", Toast.LENGTH_SHORT).show();

                    switch (CacheUtils.getString(context, "login_type")) {

                        case "1":

                            if (newsSubDao.whereAdd(list.get(position).getId())) {

                                NewsSub sub = new NewsSub();
                                sub.setSite(list.get(position).getId());
                                newsSubDao.addMySub(sub);

                            }

                            break;


                        case "2":

                            if (accountSubDao.whereAdd(list.get(position).getId(), CacheUtils.getString(context, "login"))) {

                                accountCollect = new AccountSub();
                                accountCollect.setName(CacheUtils.getString(context, "login"));
                                accountCollect.setSite_id(list.get(position).getId());
                                accountSubDao.addMyAccount(accountCollect);

                            }

                            break;

                    }


                    holder.add.setBackground(context.getResources().getDrawable(R.drawable.ic_add_circle_black_48dp));

                } else {


                    Logger.d("--------" + CacheUtils.getString(context, "login_type"));
                    Logger.d("--------" + CacheUtils.getString(context, "login"));

                    isSub = false;

                    if (CacheUtils.getString(context, "login_type").equals("1")) {

                        newsSubDao.deleteByTitle(listBean.getId());
                        holder.add.setBackground(context.getResources().getDrawable(R.drawable.ic_add_circle_outline_black_48dp));

                    } else if (CacheUtils.getString(context, "login_type").equals("2")) {

                        accountSubDao.deleteBySiteId(listBean.getId(), CacheUtils.getString(context, "login"));
                        holder.add.setBackground(context.getResources().getDrawable(R.drawable.ic_add_circle_outline_black_48dp));

                    }
                    Toast.makeText(context, "取消订阅 !", Toast.LENGTH_SHORT).show();

                }

            }
        });


        holder.itemView.setTag(position);

    }

    private void showSub(Holder holder, RecommendList.DataBean.ListBean listBean) {

        switch (CacheUtils.getString(context, "login_type")) {

            case "1":

                if (newsSubDao.querySubSite(listBean.getId()).size() > 0) {

                    isSub = true;
                    holder.add.setBackground(context.getResources().getDrawable(R.drawable.ic_add_circle_black_48dp));

                } else {

                    isSub = false;
                    holder.add.setBackground(context.getResources().getDrawable(R.drawable.ic_add_circle_outline_black_48dp));

                }

                break;

            case "2":

                if (accountSubDao.querySubSite(listBean.getId(), CacheUtils.getString(context, "login")).size() > 0) {

                    isSub = true;
                    holder.add.setBackground(context.getResources().getDrawable(R.drawable.ic_add_circle_black_48dp));

                } else {

                    isSub = false;
                    holder.add.setBackground(context.getResources().getDrawable(R.drawable.ic_add_circle_outline_black_48dp));

                }

                break;

        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private TextView tvNewsDesc;
        private ImageView ivNewsFrom;
        private ImageButton add;

        Holder(View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.tv_title_recommend);
            tvNewsDesc = (TextView) itemView.findViewById(R.id.tv_news_desc);
            ivNewsFrom = (ImageView) itemView.findViewById(R.id.iv_news_from);
            add = (ImageButton) itemView.findViewById(R.id.ib_add);

        }
    }


}
