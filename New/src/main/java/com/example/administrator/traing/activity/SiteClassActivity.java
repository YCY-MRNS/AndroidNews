package com.example.administrator.traing.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.administrator.traing.R;
import com.example.administrator.traing.adapter.SubscriptionAdapter;
import com.example.administrator.traing.domain.RecommendList;
import com.example.administrator.traing.page.SubscriptionPage;
import com.example.administrator.traing.utlis.CacheUtils;
import com.example.administrator.traing.utlis.Constants;
import com.example.administrator.traing.utlis.StatusBarUtil;
import com.example.administrator.traing.utlis.Utils;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

import static com.example.administrator.traing.page.SubscriptionPage.INTENT_KEY_SUBSCRIPTION_SITE;

public class SiteClassActivity extends AppCompatActivity {

    @ViewInject(R.id.rl_subscription)
    private RecyclerView mRV;

    @ViewInject(R.id.collapsing_toolbar_layout)
    private CollapsingToolbarLayout mCollapsingToolbarLayout;

    @ViewInject(R.id.iv_top)
    private ImageView imageView;

    private List<RecommendList.DataBean.ListBean> list;

    private static final String INTENT_KEY_SUBSCRIPTION_ID = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site);
        Utils.stateTransparent(this);
        StatusBarUtil.StatusBarLightMode(SiteClassActivity.this);
        x.view().inject(this);

        String cacheList = CacheUtils.getString(this, Constants.NEWS_SUB_LIST);

        if (!TextUtils.isEmpty(cacheList)) {

            parsedJson(cacheList);

        }

        getDataFromNet();

        setTopStyle();

    }

    private void setTopStyle() {

        String Str = getIntent().getStringExtra(SubscriptionPage.INTENT_KEY_SUBSCRIPTION_NAME);

        imageView.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);

        switch (Str) {
            case "新闻":

                Glide.with(this)
                        .load(R.drawable.news)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .crossFade()
                        .centerCrop()
                        .into(imageView);

                break;
            case "视频":
                Glide.with(this)
                        .load(R.drawable.video)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .crossFade()
                        .centerCrop()
                        .into(imageView);
                break;
            case "文艺":
                Glide.with(this)
                        .load(R.drawable.book)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .crossFade()
                        .centerCrop()
                        .into(imageView);
                break;
            case "商业":
                Glide.with(this)
                        .load(R.drawable.business)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .crossFade()
                        .centerCrop()
                        .into(imageView);
                break;
            case "科技":
                Glide.with(this)
                        .load(R.drawable.tec)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .crossFade()
                        .centerCrop()
                        .into(imageView);
                break;
            case "生活":
                Glide.with(this)
                        .load(R.drawable.life)
                        .crossFade()
                        .centerCrop()
                        .into(imageView);
                break;
            case "科学":
                Glide.with(this)
                        .load(R.drawable.science)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .crossFade()
                        .centerCrop()
                        .into(imageView);
                break;
            case "非虚构":
                Glide.with(this)
                        .load(R.drawable.real)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .crossFade()
                        .centerCrop()
                        .into(imageView);
                break;
        }


        mCollapsingToolbarLayout.setTitle(Str);
        mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);//设置还没收缩时状态下字体颜色
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.parseColor("#757575"));//设置收缩后Toolbar上字体的颜色

    }

    private void getDataFromNet() {

        String id = getIntent().getStringExtra(INTENT_KEY_SUBSCRIPTION_SITE);

        RequestParams param = new RequestParams(Constants.SITE_BASE + id);
        x.http().get(param, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                //CACHE DATA
                CacheUtils.putString(SiteClassActivity.this, Constants.NEWS_SUB_LIST, result);

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

        RecommendList recommendList = parsedJson(result);

        list = recommendList.getData().getList();

        SubscriptionAdapter adapter = new SubscriptionAdapter(SiteClassActivity.this, list);

        mRV.setAdapter(adapter);

        adapter.setOnItemClickListener(new SubscriptionAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Intent intent = new Intent(SiteClassActivity.this, SiteActivity.class);
                intent.putExtra(INTENT_KEY_SUBSCRIPTION_SITE, list.get(position).getId());
                intent.putExtra(INTENT_KEY_SUBSCRIPTION_ID, "site");
                startActivity(intent);

            }
        });

    }

    private RecommendList parsedJson(String result) {

        return new Gson().fromJson(result, RecommendList.class);

    }

    public void back(View view) {

        finish();
    }

}
