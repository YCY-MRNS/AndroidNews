package com.example.administrator.traing.page.TabDetailPage;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;

import com.example.administrator.traing.R;
import com.example.administrator.traing.activity.ReadActivity;
import com.example.administrator.traing.adapter.TabCommonAdapter;
import com.example.administrator.traing.base.MenuDetailBasePage;
import com.example.administrator.traing.db.NewsHistory;
import com.example.administrator.traing.db.NewsHistoryDao;
import com.example.administrator.traing.domain.Common;
import com.example.administrator.traing.utlis.CacheUtils;
import com.example.administrator.traing.utlis.Constants;
import com.example.administrator.traing.utlis.SnackBarUtil;
import com.example.administrator.traing.view.RefreshListView;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by Administrator on 2017/7/23.
 */

public class CommercePager extends MenuDetailBasePage {
    @ViewInject(R.id.rv)
    private RefreshListView mLV;

    @ViewInject(R.id.swipe_container)
    private SwipeRefreshLayout refreshLayout;

    private TabCommonAdapter tabCommonAdapter;
    private View mRootView;

    public CommercePager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        mRootView = View.inflate(context, R.layout.tab_detatil, null);
        x.view().inject(CommercePager.this, mRootView);
        newsHistoryDao = new NewsHistoryDao(context);

        return mRootView;
    }

    @Override
    public void initData() {
        super.initData();
        String cacheJson = CacheUtils.getString(context, Constants.NEWS_EXPLORE_COMMERCE);

        if (!TextUtils.isEmpty(cacheJson)) {
            processData(cacheJson);
        }

        getDataFromNet();

        refreshLayout.setOnRefreshListener(new MyOnRefreshListener());
        refreshLayout.setColorSchemeResources(R.color.Primary, R.color.colorPrimary_light);

        getMore();

    }

    private void getDataFromNet() {
        RequestParams param = new RequestParams(Constants.NEWS_EXPLORE_COMMERCE);
        x.http().get(param, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                Logger.d("使用XUtils3 联网成功！！");

                //CACHE DATA
                CacheUtils.putString(context, Constants.NEWS_EXPLORE_COMMERCE, result);

                processData(result);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Logger.d("使用XUtils3 联网成功！！" + ex.getMessage());
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

    private void processData(String result) {

        Common choice = parsedJson(result);

        if (!isLoadMore) {
            list = choice.getData().getList();
            tabCommonAdapter = new TabCommonAdapter(context, list);
            mLV.setAdapter(tabCommonAdapter);
            tabCommonAdapter.notifyDataSetChanged();

        } else {

            isLoadMore = false;
            list.addAll(choice.getData().getList());
            tabCommonAdapter.notifyDataSetChanged();

        }


        mLV.setOnItemClickListener(new MyOnItemClickListener());


    }

    private Common parsedJson(String result) {

        return new Gson().fromJson(result, Common.class);

    }

    private class MyOnItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


            String origin_url = list.get(position).getOrigin_url();
            String brief = list.get(position).getSite_info().getBrief();
            String pic = list.get(position).getSite_info().getPic();
            String name = list.get(position).getSite_info().getName();
            String title = list.get(position).getTitle();


            NewsHistory history = new NewsHistory();
            history.setTitle(title);
            history.setFrom(name);
            history.setImg(pic);
            history.setUrl(origin_url);
            newsHistoryDao.addNewsHistory(history);

            Intent intent = new Intent(context, ReadActivity.class);
            intent.putExtra(INTENT_KEY_URL, origin_url);
            intent.putExtra(INTENT_FROM_DESC, brief);
            intent.putExtra(INTENT_FROM_IMG, pic);
            intent.putExtra(INTENT_FROM_NAME, name);
            intent.putExtra(INTENT_NEWS_TITLE, title);
            intent.putExtra(INTENT_TYPE, "detail");

            context.startActivity(intent);

        }

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

                        getDataFromNet();
                        SnackBarUtil.showSubTSnack("已经刷新", mRootView);


                        isRefresh = false;

                    }
                }, 2000);

            }
        }
    }

    private void getMore() {
        mLV.setOnRefresh(new RefreshListView.OnRefresh() {
            @Override
            public void onLoadMore() {
                getMoreData();
            }
        });
    }

    private void getMoreData() {
        index++;
        RequestParams p2 = new RequestParams(Constants.NEWS_EXPLORE_COMMERCE + "&offset=" + index);
        x.http().get(p2, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                isLoadMore = true;
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
}





















