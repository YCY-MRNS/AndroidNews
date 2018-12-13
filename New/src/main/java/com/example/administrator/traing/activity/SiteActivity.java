package com.example.administrator.traing.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.traing.R;
import com.example.administrator.traing.adapter.TabCommonAdapter;
import com.example.administrator.traing.db.NewsHistory;
import com.example.administrator.traing.db.NewsHistoryDao;
import com.example.administrator.traing.domain.Common;
import com.example.administrator.traing.page.SubscriptionPage;
import com.example.administrator.traing.utlis.Constants;
import com.example.administrator.traing.utlis.SnackBarUtil;
import com.example.administrator.traing.utlis.StatusBarUtil;
import com.example.administrator.traing.utlis.Utils;
import com.example.administrator.traing.view.GlideCircleTransform;
import com.example.administrator.traing.view.RefreshListView;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SiteActivity extends AppCompatActivity {

    @BindView(R.id.iv_news_from)
    ImageView ivNewsFrom;
    @BindView(R.id.tv_title_recommend)
    TextView tvTitleRecommend;

    @BindView(R.id.tv_news_desc)
    TextView tvNewsDesc;

    @BindView(R.id.srl)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.rl_subscription)
    RefreshListView lvSubscription;


    private NewsHistoryDao dao;

    public String id;

    public int index = 0;
    public boolean isRefresh = false;
    public boolean isLoadMore = false;
    private List<Common.DataBean.ListBean> list;

    private TabCommonAdapter tabCommonAdapter;

    public static final String INTENT_KEY_URL = "intent_key_url";
    public static final String INTENT_NEWS_TITLE = "intent_news_title";
    public static final String INTENT_FROM_DESC = "intent_from_desc";
    public static final String INTENT_FROM_IMG = "intent_from_img";
    public static final String INTENT_FROM_NAME = "intent_from_name";
    public static final String INTENT_TYPE = "intent_type";
    private static final String INTENT_KEY_SUBSCRIPTION_ID = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription);
        Utils.stateTransparent(this);
        StatusBarUtil.StatusBarLightMode(this);
        ButterKnife.bind(SiteActivity.this);
        dao = new NewsHistoryDao(this);

        switch (getIntent().getStringExtra(INTENT_KEY_SUBSCRIPTION_ID)) {

            case "s":
                id = getIntent().getStringExtra(SearchActivity.INTENT_KEY_SUBSCRIPTION_SEARCH);

                break;

            case "site":

                id = getIntent().getStringExtra(SubscriptionPage.INTENT_KEY_SUBSCRIPTION_SITE);

                break;

            case "all":

                id = getIntent().getStringExtra(SubAndSiteActivity.INTENT_KEY_SUBSCRIPTION_SITE);

                break;

            case "my":

                id = getIntent().getStringExtra(SubAndSiteActivity.INTENT_KEY_SUBSCRIPTION_SITE);


                break;

        }


        getDataFromList();

        setSwipeRefresh();

    }

    private void setSwipeRefresh() {

        refreshLayout.setOnRefreshListener(new MyOnRefreshListener());
        refreshLayout.setColorSchemeResources(R.color.Primary, R.color.colorPrimary_light);

        lvSubscription.setOnRefresh(new RefreshListView.OnRefresh() {
            @Override
            public void onLoadMore() {
                getMore();
            }
        });

    }

    private void getMore() {

        index++;

        RequestParams params = new RequestParams(Constants.NEWS_SUB_LIST_IN_HEADER + id + Constants.NEWS_SUB_LIST_IN_FOOTER + index);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Logger.d("使用XUtils3 联网成功！！");

                isLoadMore = true;
                processData(result);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Logger.d("使用XUtils3 联网失败！！" + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Logger.d("使用XUtils3 onCancelled!!!" + cex.getMessage());
            }

            @Override
            public void onFinished() {
                Logger.d("使用XUtils3 onFinished!!!");
            }
        });


    }

    private void getDataFromList() {

        RequestParams params = new RequestParams(Constants.NEWS_SUB_LIST_IN_HEADER + id + Constants.NEWS_SUB_LIST_IN_FOOTER);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                processData(result);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });


    }

    private void processData(String result) {

        Common choice = parsedJson(result);

        if (!isLoadMore) {

            list = choice.getData().getList();
            tabCommonAdapter = new TabCommonAdapter(this, list);
            lvSubscription.setAdapter(tabCommonAdapter);
            tabCommonAdapter.notifyDataSetChanged();

            tvNewsDesc.setText(list.get(1).getSite_info().getBrief());
            tvTitleRecommend.setText(list.get(1).getSite_info().getName());

            Glide.with(this)
                    .load(list.get(1).getSite_info().getPic())
                    .centerCrop()
                    .crossFade()
                    .transform(new GlideCircleTransform(this))
                    .into(ivNewsFrom);

        } else {

            isLoadMore = false;
            List<Common.DataBean.ListBean> lists = choice.getData().getList();
            list.addAll(lists);
            tabCommonAdapter.notifyDataSetChanged();

        }


        lvSubscription.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String origin_url = list.get(position).getOrigin_url();
                String brief = list.get(position).getSite_info().getBrief();
                String pic = list.get(position).getSite_info().getPic();
                String name = list.get(position).getSite_info().getName();

                NewsHistory history = new NewsHistory();
                history.setImg(pic);
                history.setUrl(origin_url);
                history.setTitle(list.get(position).getTitle());
                history.setFrom(name);

                dao.addNewsHistory(history);

                Intent intent = new Intent(SiteActivity.this, ReadActivity.class);
                intent.putExtra(INTENT_KEY_URL, origin_url);
                intent.putExtra(INTENT_FROM_DESC, brief);
                intent.putExtra(INTENT_FROM_IMG, pic);
                intent.putExtra(INTENT_FROM_NAME, name);
                intent.putExtra(INTENT_NEWS_TITLE, list.get(position).getTitle());
                intent.putExtra(INTENT_TYPE, "sub");

                startActivity(intent);

            }
        });


    }

    private Common parsedJson(String result) {

        return new Gson().fromJson(result, Common.class);

    }

    private class MyOnRefreshListener implements SwipeRefreshLayout.OnRefreshListener {
        @Override
        public void onRefresh() {
            if (!isRefresh) {
                isRefresh = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        refreshLayout.setRefreshing(false);

                        getDataFromList();

                        SnackBarUtil.showTSnack("已经刷新", getWindow().getDecorView(), R.drawable.ic_info_black_24dp, Color.parseColor("#fafafa"));

                        isRefresh = false;

                    }
                }, 2000);

            }
        }
    }

}
