package com.example.administrator.traing.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.traing.R;
import com.example.administrator.traing.db.LoginCollect;
import com.example.administrator.traing.view.GlideCircleTransform;

import java.util.List;

/**
 * Created by Administrator on 2017/9/1.
 */

public class LoginCollectAdapter extends BaseAdapter {

    private Context context;
    private List<LoginCollect> loginCollectList;

    public LoginCollectAdapter(Context context, List<LoginCollect> loginCollectList) {
        this.context = context;
        this.loginCollectList = loginCollectList;
    }

    @Override

    public int getCount() {
        return loginCollectList.size();
    }

    @Override
    public Object getItem(int position) {
        return loginCollectList.get(position);
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

            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_myself, parent, false);

            holder.mTvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            holder.mIvForm = (ImageView) convertView.findViewById(R.id.iv_form);
            holder.mTvForm = (TextView) convertView.findViewById(R.id.tv_form);

            convertView.setTag(holder);

        } else {

            holder = (Holder) convertView.getTag();
        }

        LoginCollect history = loginCollectList.get(position);
        holder.mTvForm.setText(history.getFrom());
        holder.mTvTitle.setText(history.getTitle());

        Glide.with(context)
                .load(history.getImg())
                .centerCrop()
                .crossFade()
                .transform(new GlideCircleTransform(context))
                .into(holder.mIvForm);

        return convertView;
    }

    private static class Holder {

        private TextView mTvForm;
        private TextView mTvTitle;
        private ImageView mIvForm;

    }

}
