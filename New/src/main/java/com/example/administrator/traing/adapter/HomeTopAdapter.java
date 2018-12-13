package com.example.administrator.traing.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.traing.R;
import com.example.administrator.traing.db.LoginCollectDao;
import com.example.administrator.traing.db.NotLoginCollectDao;
import com.example.administrator.traing.domain.HotNews;
import com.example.administrator.traing.utlis.CacheUtils;
import com.example.administrator.traing.utlis.DensityUtils;
import com.example.administrator.traing.view.GlideCircleTransform;

import java.util.List;

/**
 * Created by Administrator on 2017/8/20.
 */

public class HomeTopAdapter extends BaseAdapter implements View.OnClickListener {
    private final LayoutInflater inflater;
    private Context context;
    private HotNews.DataBean.ListBean bean;
    private NotLoginCollectDao dao;
    private LoginCollectDao loginCollectDao;
    private List<HotNews.DataBean.ListBean> beanList;

    public HomeTopAdapter(Context context, List<HotNews.DataBean.ListBean> beanList) {
        this.context = context;
        this.beanList = beanList;
        inflater = LayoutInflater.from(context);
        dao = new NotLoginCollectDao(context);
        loginCollectDao = new LoginCollectDao(context);

    }

    @Override
    public void onClick(View v) {
        if (mClick != null) {
            mClick.click(v, (Integer) v.getTag());
        }
    }

    public interface OnItemLClick {

        void click(View view, int position);

    }

    private OnItemLClick mClick;

    public void setClick(OnItemLClick mClick) {
        this.mClick = mClick;
    }

    @Override
    public int getCount() {
        return beanList.size();
    }

    @Override
    public Object getItem(int position) {
        return beanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        bean = beanList.get(position);

        if (convertView == null) {

            holder = new Holder();

            convertView = inflater.inflate(R.layout.adapter_recommend_top, parent, false);

            holder.mNewsName = (TextView) convertView.findViewById(R.id.tv_title_recommend);
            holder.mNewsImg = (ImageView) convertView.findViewById(R.id.iv_recommend);
            holder.mNewsFrom = (TextView) convertView.findViewById(R.id.tv_news_from);
            holder.mNewsDesc = (TextView) convertView.findViewById(R.id.tv_news_desc);
            holder.mFromImg = (ImageView) convertView.findViewById(R.id.iv_author_img);
            holder.mNewsNum = (TextView) convertView.findViewById(R.id.tv_news_num);

            holder.ibCollection = (ImageButton) convertView.findViewById(R.id.ib_collection);
            holder.ibShare = (ImageButton) convertView.findViewById(R.id.ib_share);
            holder.ibMore = (ImageButton) convertView.findViewById(R.id.ib_more);

            convertView.setTag(holder);

        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.mNewsNum.setText(position + 1 + ".");
        holder.mNewsName.setText(bean.getTitle());
        holder.mNewsFrom.setText(bean.getSite_info().getName());
        holder.mNewsDesc.setText(bean.getBrief());

        Glide.with(context)
                .load(bean.getPrepic2())
                .override(DensityUtils.dip2px(context, 70), DensityUtils.dip2px(context, 70))
                .into(holder.mNewsImg);

        Glide.with(context)
                .load(bean.getSite_info().getPic())
                .crossFade()
                .centerCrop()
                .transform(new GlideCircleTransform(context))
                .into(holder.mFromImg);


        holder.ibCollection.setTag(position);
        holder.ibShare.setTag(position);
        holder.ibMore.setTag(position);

        holder.ibCollection.setOnClickListener(this);
        holder.ibShare.setOnClickListener(this);
        holder.ibMore.setOnClickListener(this);

        isCollect(holder);

        return convertView;

    }

    private void isCollect(Holder holder) {

        if (CacheUtils.getString(context, "login_type").equals("1")) {


            if (dao.queryListSizeByTitle(bean.getTitle()) > 0) {

                holder.ibCollection.setBackground(context.getResources().getDrawable(R.drawable.ic_bookmark_black_24dp));

            } else {

                holder.ibCollection.setBackground(context.getResources().getDrawable(R.drawable.ic_bookmark_border_black_18dp));

            }

        } else if (CacheUtils.getString(context, "login_type").equals("2")) {

            if (loginCollectDao.queryListSizeByjTitle(bean.getTitle(), CacheUtils.getString(context, "login"))) {

                holder.ibCollection.setBackground(context.getResources().getDrawable(R.drawable.ic_bookmark_black_24dp));

            } else {

                holder.ibCollection.setBackground(context.getResources().getDrawable(R.drawable.ic_bookmark_border_black_18dp));

            }

        }

    }

    private static class Holder {

        private ImageView mNewsImg;
        private ImageView mFromImg;
        private TextView mNewsName;
        private TextView mNewsFrom;
        private TextView mNewsDesc;
        private TextView mNewsNum;

        private ImageButton ibCollection;
        private ImageButton ibShare;
        private ImageButton ibMore;

    }
}
