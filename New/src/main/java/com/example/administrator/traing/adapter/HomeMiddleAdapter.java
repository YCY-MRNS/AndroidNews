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
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.administrator.traing.R;
import com.example.administrator.traing.db.LoginCollectDao;
import com.example.administrator.traing.db.NotLoginCollectDao;
import com.example.administrator.traing.domain.Common;
import com.example.administrator.traing.utlis.CacheUtils;
import com.example.administrator.traing.utlis.DensityUtils;
import com.example.administrator.traing.view.GlideCircleTransform;

import java.util.List;

import static com.example.administrator.traing.R.id.ib_collection;

/**
 * Created by Administrator on 2017/8/17.
 */

public class HomeMiddleAdapter extends BaseAdapter implements View.OnClickListener {

    private final LayoutInflater inflater;
    private Context context;
    private List<Common.DataBean.ListBean> list;
    private OnClick onClick;
    private LoginCollectDao loginCollectDao;

    private NotLoginCollectDao dao;

    public static interface OnClick {
        void click(View view, int position);
    }

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }

    public HomeMiddleAdapter(Context context, List<Common.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
        dao = new NotLoginCollectDao(context);
        loginCollectDao = new LoginCollectDao(context);
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
        Holder holder = null;

        if (convertView == null) {

            holder = new Holder();

            convertView = inflater.inflate(R.layout.adapter_recommend_middle, parent, false);

            holder.mNewsName = (TextView) convertView.findViewById(R.id.tv_title_recommend);
            holder.mNewsImg = (ImageView) convertView.findViewById(R.id.iv_recommend);
            holder.mNewsFrom = (TextView) convertView.findViewById(R.id.tv_news_from);
            holder.mNewsDesc = (TextView) convertView.findViewById(R.id.tv_news_desc);
            holder.mFromImg = (ImageView) convertView.findViewById(R.id.iv_author_img);


            holder.ibCollection = (ImageButton) convertView.findViewById(ib_collection);
            holder.ibShare = (ImageButton) convertView.findViewById(R.id.ib_share);
            holder.ibMore = (ImageButton) convertView.findViewById(R.id.ib_more);

            convertView.setTag(holder);

        } else {
            holder = (Holder) convertView.getTag();
        }

        Common.DataBean.ListBean bean = list.get(position);

        holder.mNewsName.setText(bean.getTitle());
        holder.mNewsFrom.setText(bean.getSite_info().getName());
        holder.mNewsDesc.setText(bean.getBrief());

        Glide.with(context)
                .load(bean.getSite_info().getPic())
                .centerCrop()
                .crossFade()
                .transform(new GlideCircleTransform(context))
                .into(holder.mFromImg);


        if (bean.getHeadpic().equals("")) {

            ViewGroup.LayoutParams img = holder.mNewsImg.getLayoutParams();
            img.height = 0;
            holder.mNewsImg.setLayoutParams(img);

        } else {

            ViewGroup.LayoutParams img = holder.mNewsImg.getLayoutParams();
            img.height = DensityUtils.dip2px(context, 180);
            holder.mNewsImg.setLayoutParams(img);
            Glide.with(context).load(bean.getHeadpic())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .crossFade()
                    .into(holder.mNewsImg);

        }

        holder.ibCollection.setTag(position);
        holder.ibShare.setTag(position);
        holder.ibMore.setTag(position);

        holder.ibCollection.setOnClickListener(this);
        holder.ibShare.setOnClickListener(this);
        holder.ibMore.setOnClickListener(this);

        setShow(holder, bean);


        return convertView;
    }

    private void setShow(Holder holder, Common.DataBean.ListBean bean) {

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

    @Override
    public void onClick(View v) {

        if (onClick != null) {

            onClick.click(v, (Integer) v.getTag());

        }

    }

    private static class Holder {

        private ImageView mFromImg;
        private ImageView mNewsImg;
        private TextView mNewsName;
        private TextView mNewsFrom;
        private TextView mNewsDesc;


        private ImageButton ibCollection;
        private ImageButton ibShare;
        private ImageButton ibMore;

    }

}
