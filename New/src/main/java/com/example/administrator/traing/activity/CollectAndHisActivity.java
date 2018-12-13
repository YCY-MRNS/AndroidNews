package com.example.administrator.traing.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.administrator.traing.R;
import com.example.administrator.traing.adapter.LoginCollectAdapter;
import com.example.administrator.traing.adapter.MyselfHistoryAdapter;
import com.example.administrator.traing.adapter.NotCollectAdapter;
import com.example.administrator.traing.db.LoginCollect;
import com.example.administrator.traing.db.LoginCollectDao;
import com.example.administrator.traing.db.NewsHistory;
import com.example.administrator.traing.db.NewsHistoryDao;
import com.example.administrator.traing.db.NotLoginCollect;
import com.example.administrator.traing.db.NotLoginCollectDao;
import com.example.administrator.traing.utlis.CacheUtils;
import com.example.administrator.traing.utlis.DensityUtils;
import com.example.administrator.traing.utlis.StatusBarUtil;
import com.example.administrator.traing.utlis.Utils;
import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CollectAndHisActivity extends AppCompatActivity {

    @BindView(R.id.tools_bar_title_name)
    TextView title;

    @BindView(R.id.lv_list)
    SwipeMenuListView sml;

    @BindView(R.id.ib_back)
    ImageButton mIbBack;

    @BindView(R.id.ib_delete)
    ImageButton mIbDelete;

    @BindView(R.id.iv_history)
    ImageView iv_;

    @BindView(R.id.tv_hint)
    TextView tv_;

    @BindView(R.id.tv_hint_)
    TextView tv_hint_;

    private NewsHistoryDao newsHistoryDao;
    private LoginCollectDao loginCollectDao;
    private NotLoginCollectDao notLoginCollectDao;

    private List<NotLoginCollect> notLoginCollects;
    private List<LoginCollect> loginCollects;
    private List<NewsHistory> newsHistories;

    private NotCollectAdapter notCollectAdapter;
    private MyselfHistoryAdapter historyAdapter;
    private LoginCollectAdapter loginCollectAdapter;

    public static final String INTENT_TYPE = "intent_type";
    public static final String INTENT_KEY_URL = "intent_key_url";
    public static final String INTENT_FROM_IMG = "intent_from_img";
    public static final String INTENT_FROM_NAME = "intent_from_name";
    public static final String INTENT_FROM_DESC = "intent_from_desc";
    public static final String INTENT_NEWS_TITLE = "intent_news_title";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_and_his);

        ButterKnife.bind(this);
        Utils.stateTransparent(this);
        StatusBarUtil.StatusBarLightMode(CollectAndHisActivity.this);

        newsHistoryDao = new NewsHistoryDao(this);
        notLoginCollectDao = new NotLoginCollectDao(this);
        loginCollectDao = new LoginCollectDao(this);

        String type = getIntent().getStringExtra("1");

        title.setText(type);

        switch (type) {

            case "阅读历史":

                setHistoryList();

                break;

            case "我的收藏":

                setMyCollect();

                break;


        }

        Logger.d("login_type---------->" + CacheUtils.getString(this, "login_type"));

        mIbBack.setOnClickListener(new MyIbBack());
        mIbDelete.setOnClickListener(new MyIbDelete());

    }

    private void setHistoryList() {

        newsHistories = newsHistoryDao.listAll();

        if (newsHistories.size() <= 0) {

            tv_hint_.setText("你的记录会出现在这");

        } else {

            hintVisibility();

            historyAdapter = new MyselfHistoryAdapter(CollectAndHisActivity.this, newsHistories);
            sml.setAdapter(historyAdapter);
            historyAdapter.notifyDataSetChanged();

            sml.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent intent = new Intent(CollectAndHisActivity.this, ReadActivity.class);
                    intent.putExtra(INTENT_KEY_URL, newsHistories.get(position).getUrl());
                    intent.putExtra(INTENT_FROM_IMG, newsHistories.get(position).getImg());
                    intent.putExtra(INTENT_FROM_NAME, newsHistories.get(position).getFrom());
                    intent.putExtra(INTENT_NEWS_TITLE, newsHistories.get(position).getTitle());
                    intent.putExtra(INTENT_TYPE, "history");

                    startActivity(intent);

                }
            });
        }

    }

    private void hintVisibility() {

        tv_hint_.setVisibility(View.GONE);
        tv_.setVisibility(View.GONE);
        iv_.setVisibility(View.GONE);

    }

    private void setMyCollect() {

        collectShow();

        setSwipeMenuCreator();

        if (CacheUtils.getString(CollectAndHisActivity.this, "login_type").equals("1")) {

            notLoginCollects = notLoginCollectDao.queryByDistinctTitle();

            notCollectAdapter = new NotCollectAdapter(CollectAndHisActivity.this, notLoginCollects);

            this.sml.setAdapter(notCollectAdapter);

            sml.setOnMenuItemClickListener(new MyOnMenuItemClickListener());

            notCollectAdapter.notifyDataSetChanged();

            sml.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent intent = new Intent(CollectAndHisActivity.this, ReadActivity.class);
                    intent.putExtra(INTENT_KEY_URL, notLoginCollects.get(position).getUrl());
                    intent.putExtra(INTENT_FROM_IMG, notLoginCollects.get(position).getImg());
                    intent.putExtra(INTENT_FROM_NAME, notLoginCollects.get(position).getFrom());
                    intent.putExtra(INTENT_NEWS_TITLE, notLoginCollects.get(position).getTitle());
                    intent.putExtra(INTENT_TYPE, "collect");
                    startActivity(intent);
                }
            });

        } else if (CacheUtils.getString(CollectAndHisActivity.this, "login_type").equals("2")) {

            loginCollects = loginCollectDao.queryByName(CacheUtils.getString(CollectAndHisActivity.this, "login"));
            loginCollectAdapter = new LoginCollectAdapter(CollectAndHisActivity.this, loginCollects);
            sml.setAdapter(loginCollectAdapter);

            sml.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent intent = new Intent(CollectAndHisActivity.this, ReadActivity.class);
                    intent.putExtra(INTENT_KEY_URL, loginCollects.get(position).getUrl());
                    intent.putExtra(INTENT_FROM_IMG, loginCollects.get(position).getImg());
                    intent.putExtra(INTENT_FROM_NAME, loginCollects.get(position).getFrom());
                    intent.putExtra(INTENT_NEWS_TITLE, loginCollects.get(position).getTitle());
                    intent.putExtra(INTENT_TYPE, "collect");
                    startActivity(intent);

                }
            });

        }

        sml.setOnMenuItemClickListener(new MyOnMenuItemClickListener());


    }

    private void collectShow() {
        mIbDelete.setVisibility(View.GONE);

        tv_hint_.setText("你的收藏会在这里出现");
        iv_.setImageResource(R.drawable.hint_collect);


        if (notLoginCollectDao.queryByDistinctTitle().size() > 0) {

            hintVisibility();

        }

        if (loginCollectDao.queryByName(CacheUtils.getString(CollectAndHisActivity.this, "login")).size() > 0) {

            hintVisibility();

        }
    }

    private void setSwipeMenuCreator() {

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {

                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());

                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));

                deleteItem.setWidth(DensityUtils.dip2px(CollectAndHisActivity.this, 80));

                deleteItem.setTitle("移除");

                deleteItem.setTitleSize(18);

                deleteItem.setTitleColor(Color.WHITE);

                menu.addMenuItem(deleteItem);

            }
        };

        sml.setMenuCreator(creator);

    }

    private class MyIbBack implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            finish();
        }
    }

    private class MyIbDelete implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            newsHistoryDao.deleteByList(newsHistories);
            newsHistories.clear();
            historyAdapter.notifyDataSetChanged();

        }
    }

    private class MyOnMenuItemClickListener implements SwipeMenuListView.OnMenuItemClickListener {
        @Override
        public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {

            switch (index) {
                case 0:
                    if (CacheUtils.getString(CollectAndHisActivity.this, "login_type").equals("1")) {

                        notLoginCollectDao.deleteByTitle(notLoginCollects.get(position).getTitle());

                        notLoginCollects.remove(position);

                        notCollectAdapter.notifyDataSetChanged();

                    } else if (CacheUtils.getString(CollectAndHisActivity.this, "login_type").equals("2")) {

                        loginCollectDao.deleteByTitle(loginCollects.get(position).getTitle(), CacheUtils.getString(CollectAndHisActivity.this, "login"));
                        loginCollects.remove(position);
                        loginCollectAdapter.notifyDataSetChanged();

                    }


                    break;

            }

            return false;
        }
    }

}
