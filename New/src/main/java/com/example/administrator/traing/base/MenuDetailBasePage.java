package com.example.administrator.traing.base;

import android.content.Context;
import android.view.View;

import com.example.administrator.traing.db.NewsHistoryDao;
import com.example.administrator.traing.domain.Common;

import java.util.List;

/**
 * Created by Administrator on 2017/7/24.
 */

public abstract class MenuDetailBasePage {

    public NewsHistoryDao newsHistoryDao;
    public boolean isRefresh = false;
    public int index = 0;

    public boolean isLoadMore = false;
    public Context context;

    public List<Common.DataBean.ListBean> list;

    public static final String INTENT_KEY_URL = "intent_key_url";
    public static final String INTENT_FROM_DESC = "intent_from_desc";
    public static final String INTENT_FROM_IMG = "intent_from_img";
    public static final String INTENT_FROM_NAME = "intent_from_name";
    public static final String INTENT_NEWS_TITLE = "intent_news_title";
    public static final String INTENT_TYPE = "intent_type";

    public MenuDetailBasePage(Context context) {
        this.context = context;
        rootView = initView();
    }

    public View rootView;

    public abstract View initView();

    public void initData() {
    }



}
