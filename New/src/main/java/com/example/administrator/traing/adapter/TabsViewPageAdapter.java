package com.example.administrator.traing.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.traing.base.MenuDetailBasePage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/23.
 */

public class TabsViewPageAdapter extends PagerAdapter {
    private Context context;
    private List<MenuDetailBasePage> newsPages = new ArrayList<>();
    private String[] tabStr = {"精选", "商业", "新闻", "视频", "文艺", "科技", "生活", "非虚构"};

    public TabsViewPageAdapter(Context context, List<MenuDetailBasePage> newsPages) {
        this.context = context;
        this.newsPages = newsPages;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        MenuDetailBasePage newsPage = newsPages.get(position);
        View view = newsPage.rootView;

        newsPage.initData();
        container.addView(view);

        return view;

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabStr[position];
    }

    @Override
    public int getCount() {
        return newsPages.size();
    }
}
