package com.example.administrator.traing.page;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;

import com.example.administrator.traing.R;
import com.example.administrator.traing.activity.SearchActivity;
import com.example.administrator.traing.adapter.TabsViewPageAdapter;
import com.example.administrator.traing.base.BasePager;
import com.example.administrator.traing.base.MenuDetailBasePage;
import com.example.administrator.traing.page.TabDetailPage.ArtPager;
import com.example.administrator.traing.page.TabDetailPage.ChoicePager;
import com.example.administrator.traing.page.TabDetailPage.CommercePager;
import com.example.administrator.traing.page.TabDetailPage.LifePager;
import com.example.administrator.traing.page.TabDetailPage.NewsPager;
import com.example.administrator.traing.page.TabDetailPage.RealPager;
import com.example.administrator.traing.page.TabDetailPage.TechnologyPager;
import com.example.administrator.traing.page.TabDetailPage.VideoPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/27.
 */

public class ExplorePage extends BasePager {

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @BindView(R.id.vp)
    ViewPager vp;

    @BindView(R.id.ib_tools_bar_search)
    ImageButton imageButton;

    public ExplorePage() {

    }

    @SuppressLint({"NewApi", "ValidFragment"})
    public ExplorePage(Context context) {
        super(context);
    }

    @Override
    public View initView() {

        View view = View.inflate(context, R.layout.fragment_explore, null);
        //xUtils initialize
        ButterKnife.bind(ExplorePage.this, view);

        return view;

    }

    @Override
    public void initData() {
        super.initData();

        List<MenuDetailBasePage> newsPages = new ArrayList<>();
        newsPages.add(new ChoicePager(context));
        newsPages.add(new CommercePager(context));
        newsPages.add(new NewsPager(context));
        newsPages.add(new VideoPager(context));
        newsPages.add(new ArtPager(context));
        newsPages.add(new TechnologyPager(context));
        newsPages.add(new LifePager(context));
        newsPages.add(new RealPager(context));

        TabsViewPageAdapter tabsViewPageAdapter = new TabsViewPageAdapter(context, newsPages);
        vp.setAdapter(tabsViewPageAdapter);

        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(vp);
            }
        });

        tabsViewPageAdapter.notifyDataSetChanged();

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, SearchActivity.class));
            }
        });

    }


}






