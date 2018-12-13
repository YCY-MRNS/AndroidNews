package com.example.administrator.traing.page;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.administrator.traing.R;
import com.example.administrator.traing.activity.ReadActivity;
import com.example.administrator.traing.activity.WeatherActivity;
import com.example.administrator.traing.adapter.HomeMiddleAdapter;
import com.example.administrator.traing.adapter.HomeTopAdapter;
import com.example.administrator.traing.base.BasePager;
import com.example.administrator.traing.db.LoginCollect;
import com.example.administrator.traing.db.LoginCollectDao;
import com.example.administrator.traing.db.NotLoginCollect;
import com.example.administrator.traing.db.NotLoginCollectDao;
import com.example.administrator.traing.db.NewsHistory;
import com.example.administrator.traing.db.NewsHistoryDao;
import com.example.administrator.traing.domain.Common;
import com.example.administrator.traing.domain.DayImg;
import com.example.administrator.traing.domain.HotNews;
import com.example.administrator.traing.domain.Weather;
import com.example.administrator.traing.utlis.CacheUtils;
import com.example.administrator.traing.utlis.Constants;
import com.example.administrator.traing.utlis.SnackBarUtil;
import com.example.administrator.traing.utlis.Utils;
import com.example.administrator.traing.view.LoadMoreView;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.onekeyshare.OnekeyShare;

import static com.example.administrator.traing.R.id.sv;

/**
 * Created by Administrator on 2017/6/27.
 */

public class HomePage extends BasePager {

    @ViewInject(R.id.lv_recommend_page_middle)
    private ListView mLvMiddle;

    @ViewInject(R.id.lv_recommend_page_top)
    private ListView mLvTop;

    @ViewInject(R.id.pb_recommend)
    private ProgressBar progressBar;

    @ViewInject(R.id.vp_top)
    private ImageView topPhoto;

    @ViewInject(sv)
    private LoadMoreView loadMoreView;

    @ViewInject(R.id.ll_load_more)
    private LinearLayout llLoadMore;

    @ViewInject(R.id.swipe_container)
    private SwipeRefreshLayout refreshLayout;

    @ViewInject(R.id.tv_weather_city)
    private TextView mTvCity;

    @ViewInject(R.id.tv_weather_temperature)
    private TextView mTvTemperature;

    @ViewInject(R.id.tv_week)
    private TextView mTvWeek;

    @ViewInject(R.id.tv_date)
    private TextView mTvDate;

    @ViewInject(R.id.tv_weather_dayTime)
    private TextView mTvDayTime;

    @ViewInject(R.id.ll_w)
    private LinearLayout mLw;

    private NewsHistoryDao newsHistoryDao;
    private NotLoginCollectDao notLoginCollectDao;
    private LoginCollectDao loginCollectDao;

    private List<HotNews.DataBean.ListBean> hotList = new ArrayList<>();
    private List<Common.DataBean.ListBean> recommendList = new ArrayList<>();

    private int index = 2;
    private boolean isLoadMore = false;

    public static final String INTENT_URL_KEY = "intent_url_key";
    public static final String INTENT_FROM_DESC = "intent_from_desc";
    public static final String INTENT_FROM_IMG = "intent_from_img";
    public static final String INTENT_FROM_NAME = "intent_from_name";
    public static final String INTENT_NEWS_TITLE = "intent_news_title";
    public static final String INTENT_TYPE = "intent_type";

    public static final String CACHE_HOT_NEWS = "cache_hot_news";
    public static final String CACHE_TOP_DAY_IMG = "cache_top_day_img";
    public static final String CACHE_RECOMMEND_NEWS = "cache_recommend_news";

    private HomeMiddleAdapter homeMiddleAdapter;
    private boolean isCollect = false;
    private boolean isRefresh = false;
    private View mRootView;

    public HomePage() {
    }

    @SuppressLint({"NewApi", "ValidFragment"})
    public HomePage(Context context) {
        super(context);
    }

    @Override
    public View initView() {

        mRootView = View.inflate(context, R.layout.fragment_recommend, null);

        x.view().inject(HomePage.this, mRootView);

        loadMoreView.smoothScrollBy(0, 0);
        newsHistoryDao = new NewsHistoryDao(context);
        loginCollectDao = new LoginCollectDao(context);
        notLoginCollectDao = new NotLoginCollectDao(context);

        return mRootView;

    }

    @Override
    public void initData() {
        super.initData();

        refreshLayout.setOnRefreshListener(new MyOnRefreshListener());
        refreshLayout.setColorSchemeResources(R.color.Primary, R.color.colorPrimary_light);
        loadMoreView.smoothScrollBy(0, 0);

        String cacheHot = CacheUtils.getString(context, CACHE_HOT_NEWS);
        String cacheRecommend = CacheUtils.getString(context, CACHE_RECOMMEND_NEWS);
        String cacheTopDayImg = CacheUtils.getString(context, CACHE_TOP_DAY_IMG);
        String cacheWeather = CacheUtils.getString(context, Constants.NEWS_HOME_W);

        if (!TextUtils.isEmpty(cacheHot) && !TextUtils.isEmpty(cacheRecommend) && !TextUtils.isEmpty(cacheTopDayImg) && !TextUtils.isEmpty(cacheWeather)) {

            progressJsonHOT(cacheHot);
            progressJsonRecommend(cacheRecommend);
            progressJsonDayImg(cacheTopDayImg);
            progressJsonWeather(cacheWeather);

        }

        getWeather();
        getJsonFromNet();
        getRecommendFromNet();
        getDayImg();

        setListener();

    }

    private void getWeather() {

        RequestParams p = new RequestParams(Constants.NEWS_HOME_W + "重庆");
        x.http().get(p, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                CacheUtils.putString(context, Constants.NEWS_HOME_W, result);
                progressJsonWeather(result);

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

    private void progressJsonWeather(String result) {

        Weather weather = jsonWeather(result);
        List<Weather.ResultBean> weatherResult = weather.getResult();

        if (weatherResult != null) {

            mTvCity.setText(weatherResult.get(0).getCity());
            mTvDayTime.setText(weatherResult.get(0).getFuture().get(0).getNight());
            mTvTemperature.setText(weatherResult.get(0).getFuture().get(0).getTemperature());
            mTvDate.setText(weatherResult.get(0).getDate());
            mTvWeek.setText(weatherResult.get(0).getWeek());

        }


    }

    private Weather jsonWeather(String result) {
        return new Gson().fromJson(result, Weather.class);
    }

    private void getDayImg() {
        RequestParams params4 = new RequestParams(Constants.NEWS_HOME_PIC);

        x.http().get(params4, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                CacheUtils.putString(context, CACHE_TOP_DAY_IMG, result);
                progressJsonDayImg(result);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(context, "网络连接失败！请检查网络", Toast.LENGTH_SHORT).show();

                Logger.d("onError   params4" + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Logger.d("onCancelled   params4" + cex.getMessage());
            }

            @Override
            public void onFinished() {

            }
        });

    }

    private void progressJsonDayImg(String result) {

        DayImg img = parsedImg(result);

        final List<DayImg.ImagesBean> images = img.getImages();

        CacheUtils.putString(context, "pic", Constants.NEWS_HOME_PIC_BASIC + images.get(0).getUrl());

        Glide.with(context)
                .load(Constants.NEWS_HOME_PIC_BASIC + images.get(0).getUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(topPhoto);

    }

    private DayImg parsedImg(String result) {

        return new Gson().fromJson(result, DayImg.class);

    }

    private void setListener() {


        mLvTop.setOnItemClickListener(new MyTopItemClickListener());
        mLvMiddle.setOnItemClickListener(new MyRecommendItemClickListener());
        loadMoreView.setOnRefreshListener(new MyOnRefreshListener());
        mLw.setOnClickListener(new MyWListener());


    }

    private void getJsonFromNet() {

        RequestParams params = new RequestParams(Constants.NEWS_HOME_HOT_NEWS);

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                Logger.d("onSuccess params1");
                CacheUtils.putString(context, CACHE_HOT_NEWS, result);
                progressJsonHOT(result);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(context, "网络连接失败！请检查网络", Toast.LENGTH_SHORT).show();

                Logger.d("onError   params1" + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Logger.d("onCancelled   params2" + cex.getMessage());

            }

            @Override
            public void onFinished() {

            }

        });

    }

    private void getRecommendFromNet() {

        RequestParams params2 = new RequestParams(Constants.NEWS_HOME_RECOMMEND + "0");

        x.http().get(params2, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                CacheUtils.putString(context, CACHE_RECOMMEND_NEWS, result);
                progressJsonRecommend(result);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(context, "网络连接失败！请检查网络", Toast.LENGTH_SHORT).show();

                Logger.d("onError   params2" + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Logger.d("onCancelled   params2" + cex.getMessage());
            }

            @Override
            public void onFinished() {

            }
        });

    }

    private void progressJsonRecommend(String result) {

        Common common = parseRecommendJson(result);

        if (!isLoadMore) {

            recommendList = common.getData().getList();
            homeMiddleAdapter = new HomeMiddleAdapter(context, recommendList);

            mLvMiddle.setAdapter(homeMiddleAdapter);
            homeMiddleAdapter.notifyDataSetChanged();
            Utils.setDynamicHeight(mLvMiddle);
            homeMiddleAdapter.setOnClick(new MyMiddleCollect());

            progressBar.setVisibility(View.GONE);

        } else {

            isLoadMore = false;
            recommendList.addAll(common.getData().getList());
            homeMiddleAdapter.notifyDataSetChanged();
            Utils.setDynamicHeight(mLvMiddle);

        }

    }

    private void progressJsonHOT(String result) {

        HotNews hotNewsS = parseJson(result);
        this.hotList = hotNewsS.getData().getList();

        if (hotList.size() > 0) {

            HomeTopAdapter homeTopAdapter = new HomeTopAdapter(context, hotList);
            mLvTop.setAdapter(homeTopAdapter);
            Utils.setDynamicHeight(mLvTop);
            homeTopAdapter.notifyDataSetChanged();

            homeTopAdapter.setClick(new MyTopCollect());

        }

        progressBar.setVisibility(View.GONE);

    }

    private HotNews parseJson(String result) {

        return new Gson().fromJson(result, HotNews.class);

    }

    private Common parseRecommendJson(String result) {

        return new Gson().fromJson(result, Common.class);

    }

    private void getMoreFromNet() {

        if (index >= 4) {
            Toast.makeText(context, "没有更多数据", Toast.LENGTH_SHORT).show();
            llLoadMore.setVisibility(View.GONE);
            return;
        }

        RequestParams params3 = new RequestParams(Constants.NEWS_HOME_RECOMMEND + index);
        x.http().get(params3, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Logger.d("onSuccess   params3");

                isLoadMore = true;
                progressJsonRecommend(result);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                Toast.makeText(context, "网络连接失败！请检查网络", Toast.LENGTH_SHORT).show();
                Logger.d("onError   params3" + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Logger.d("onCancelled   params3" + cex.getMessage());
            }

            @Override
            public void onFinished() {

            }
        });

    }

    private class MyTopItemClickListener implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


            NewsHistory history = new NewsHistory();
            history.setUrl(hotList.get(position).getOrigin_url());
            history.setTitle(hotList.get(position).getTitle());
            history.setFrom(hotList.get(position).getSite_info().getName());
            history.setImg(hotList.get(position).getSite_info().getPic());

            newsHistoryDao.addNewsHistory(history);

            Intent intent = new Intent(context, ReadActivity.class);
            intent.putExtra(INTENT_URL_KEY, hotList.get(position).getOrigin_url());
            intent.putExtra(INTENT_FROM_DESC, hotList.get(position).getSite_info().getBrief());
            intent.putExtra(INTENT_FROM_IMG, hotList.get(position).getSite_info().getPic());
            intent.putExtra(INTENT_FROM_NAME, hotList.get(position).getSite_info().getName());
            intent.putExtra(INTENT_NEWS_TITLE, hotList.get(position).getTitle());
            intent.putExtra(INTENT_TYPE, "recommend");
            context.startActivity(intent);


        }

    }

    private class MyRecommendItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            NewsHistory history = new NewsHistory();
            history.setUrl(recommendList.get(position).getOrigin_url());
            history.setTitle(recommendList.get(position).getTitle());
            history.setFrom(recommendList.get(position).getSite_info().getName());
            history.setImg(recommendList.get(position).getSite_info().getPic());
            newsHistoryDao.addNewsHistory(history);


            Intent intent = new Intent(context, ReadActivity.class);
            intent.putExtra(INTENT_URL_KEY, recommendList.get(position).getOrigin_url());
            intent.putExtra(INTENT_FROM_DESC, recommendList.get(position).getSite_info().getBrief());
            intent.putExtra(INTENT_FROM_IMG, recommendList.get(position).getSite_info().getPic());
            intent.putExtra(INTENT_FROM_NAME, recommendList.get(position).getSite_info().getName());
            intent.putExtra(INTENT_NEWS_TITLE, recommendList.get(position).getTitle());
            intent.putExtra(INTENT_TYPE, "recommend");
            context.startActivity(intent);

        }
    }

    private class MyOnRefreshListener implements LoadMoreView.OnRefreshListener, SwipeRefreshLayout.OnRefreshListener {

        @Override
        public void onLoadMore() {

            getMoreFromNet();
            index++;

        }

        @Override
        public void onRefresh() {
            if (!isRefresh) {
                isRefresh = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);

                        getJsonFromNet();
                        getRecommendFromNet();
                        getDayImg();

                        SnackBarUtil.showTSnack("已经是最新的内容！", mRootView, R.drawable.ic_info_black_24dp, Color.parseColor("#FAFAFA"));

                        isRefresh = false;

                    }
                }, 2000);

            }
        }
    }

    private class MyMiddleCollect implements HomeMiddleAdapter.OnClick {
        @Override
        public void click(View view, int position) {

            String origin_url = recommendList.get(position).getOrigin_url();
            String brief = recommendList.get(position).getSite_info().getBrief();
            String pic = recommendList.get(position).getSite_info().getPic();
            String name = recommendList.get(position).getSite_info().getName();
            String title = recommendList.get(position).getTitle();


            switch (view.getId()) {

                case R.id.ib_collection:

                    isOrNotCollect(view, origin_url, pic, name, title);

                    break;

                case R.id.ib_share:

                    showShare(title, origin_url, brief, pic);

                    break;
                case R.id.ib_more:

                    Toast.makeText(context, "More", Toast.LENGTH_SHORT).show();

                    break;

            }

        }
    }

    private class MyTopCollect implements HomeTopAdapter.OnItemLClick {
        @Override
        public void click(View view, int position) {

            String origin_url = hotList.get(position).getOrigin_url();
            String brief = hotList.get(position).getSite_info().getBrief();
            String pic = hotList.get(position).getSite_info().getPic();
            String name = hotList.get(position).getSite_info().getName();
            String topTitle = hotList.get(position).getTitle();

            switch (view.getId()) {

                case R.id.ib_collection:

                    isOrNotCollect(view, origin_url, pic, name, topTitle);

                    break;

                case R.id.ib_share:

                    showShare(topTitle, origin_url, brief, pic);

                    break;
                case R.id.ib_more:

                    break;

            }

        }

    }

    private void isOrNotCollect(View view, String origin_url, String pic, String name, String title) {

        isCollect = view.getBackground().getCurrent().getConstantState() == context.getResources().getDrawable(R.drawable.ic_bookmark_black_24dp).getConstantState();

        if (CacheUtils.getString(context, "login_type").equals("1")) {

            if (!isCollect) {

                isCollect = true;

                NotLoginCollect collect = new NotLoginCollect();
                collect.setUrl(origin_url);
                collect.setTitle(title);
                collect.setFrom(name);
                collect.setImg(pic);

                notLoginCollectDao.addMyCollect(collect);

                view.setBackground(context.getResources().getDrawable(R.drawable.ic_bookmark_black_24dp));

                SnackBarUtil.showTSnack("添加收藏", mRootView, R.drawable.ic_bookmark_black_24dp, Color.parseColor("#ffffff"));

            } else {

                isCollect = false;
                view.setBackground(context.getResources().getDrawable(R.drawable.ic_bookmark_border_black_24dp));
                notLoginCollectDao.deleteByTitle(title);

                SnackBarUtil.showTSnack("取消收藏", mRootView, R.drawable.ic_bookmark_border_black_24dp, Color.parseColor("#ffffff"));

            }


        } else if (CacheUtils.getString(context, "login_type").equals("2")) {

            if (!isCollect) {

                isCollect = true;

                if (loginCollectDao.whereAdd(title, CacheUtils.getString(context, "login"))) {

                    LoginCollect collect = new LoginCollect();
                    collect.setUrl(origin_url);
                    collect.setTitle(title);
                    collect.setFrom(name);
                    collect.setImg(pic);
                    collect.setName(CacheUtils.getString(context, "login"));

                    loginCollectDao.addMyCollect(collect);

                }

                view.setBackground(context.getResources().getDrawable(R.drawable.ic_bookmark_black_24dp));
                SnackBarUtil.showTSnack("添加收藏", mRootView, R.drawable.ic_bookmark_black_24dp, Color.parseColor("#ffffff"));

            } else {

                isCollect = false;
                view.setBackground(context.getResources().getDrawable(R.drawable.ic_bookmark_border_black_24dp));
                loginCollectDao.deleteByTitle(title, CacheUtils.getString(context, "login"));

                SnackBarUtil.showTSnack("取消收藏", mRootView, R.drawable.ic_bookmark_border_black_24dp, Color.parseColor("#ffffff"));

            }


        }


    }

    private void showShare(String title, String url, String desc, String img) {
        OnekeyShare oks = new OnekeyShare();
        oks.disableSSOWhenAuthorize();
        oks.setTitle(title);
        oks.setTitleUrl(url);
        oks.setText(desc);
        oks.setImageUrl(img);
        oks.setUrl(url);
        oks.setComment(desc);
        oks.setSite(title);
        oks.setSiteUrl(url);
        oks.show(context);
    }

    private class MyWListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            context.startActivity(new Intent(context, WeatherActivity.class));
        }
    }

}



















