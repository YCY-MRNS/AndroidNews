package com.example.administrator.traing.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.administrator.traing.R;
import com.example.administrator.traing.adapter.LoginSubListAdapter;
import com.example.administrator.traing.adapter.MyAllSubSiteAdapter;
import com.example.administrator.traing.adapter.NotLoginSubListAdapter;
import com.example.administrator.traing.db.AccountSub;
import com.example.administrator.traing.db.AccountSubDao;
import com.example.administrator.traing.db.AllSiteDao;
import com.example.administrator.traing.db.AllSiteInfo;
import com.example.administrator.traing.db.LoginSiteDao;
import com.example.administrator.traing.db.LoginSiteSub;
import com.example.administrator.traing.db.NewsSub;
import com.example.administrator.traing.db.NewsSubDao;
import com.example.administrator.traing.db.NotLoginSub;
import com.example.administrator.traing.db.NotLoginSubDao;
import com.example.administrator.traing.domain.RecommendList;
import com.example.administrator.traing.utlis.CacheUtils;
import com.example.administrator.traing.utlis.Constants;
import com.example.administrator.traing.utlis.SnackBarUtil;
import com.example.administrator.traing.utlis.StatusBarUtil;
import com.example.administrator.traing.utlis.Utils;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SubAndSiteActivity extends AppCompatActivity {

    @BindView(R.id.tools_bar_title_name)
    TextView title;

    @BindView(R.id.lv_list)
    ListView sml;

    @BindView(R.id.ib_back)
    ImageButton mIbBack;

    @BindView(R.id.ib_delete)
    ImageButton mIbDelete;

    @BindView(R.id.sfl)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.iv_history)
    ImageView iv_;

    @BindView(R.id.tv_hint)
    TextView tv_;

    @BindView(R.id.tv_hint_)
    TextView tv_hint_;

    @BindView(R.id.pb_sub)
    ProgressBar mPbSub;

    private boolean isRefresh = false;

    private NewsSubDao newsSubDao;
    private AllSiteDao allSiteDao;
    private NotLoginSubDao notLoginSubDao;
    private AccountSubDao accountSubDao;
    private LoginSiteDao loginSiteDao;

    public static final String INTENT_TYPE = "intent_type";
    public static final String INTENT_KEY_SUBSCRIPTION_ID = "1";
    public static final String INTENT_KEY_URL = "intent_key_url";
    public static final String INTENT_FROM_IMG = "intent_from_img";
    public static final String INTENT_FROM_DESC = "intent_from_desc";
    public static final String INTENT_FROM_NAME = "intent_from_name";
    public static final String INTENT_NEWS_TITLE = "intent_news_title";
    public static final String INTENT_KEY_SUBSCRIPTION_SITE = "SubAndSiteActivity";

    private List<RecommendList.DataBean.ListBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myself);
        ButterKnife.bind(this);
        Utils.stateTransparent(this);
        StatusBarUtil.StatusBarLightMode(SubAndSiteActivity.this);

        allSiteDao = new AllSiteDao(this);
        newsSubDao = new NewsSubDao(this);
        notLoginSubDao = new NotLoginSubDao(this);
        accountSubDao = new AccountSubDao(this);
        loginSiteDao = new LoginSiteDao(this);

        refreshLayout.setOnRefreshListener(new MyOnRefreshListener());
        refreshLayout.setColorSchemeResources(R.color.Primary, R.color.colorPrimary_light);

        mIbBack.setOnClickListener(new MyIbBack());

        String type = getIntent().getStringExtra("1");

        title.setText(type);


        switch (type) {

            case "全部站点":

                setMySubscription();

                break;

            case "订阅中心":

                setSuCenter();

                break;

        }

    }

    private void setSuCenter() {

        mPbSub.setVisibility(View.GONE);
        mIbDelete.setVisibility(View.GONE);


        if (CacheUtils.getString(SubAndSiteActivity.this, "login_type").equals("1")) {

            notLoginSub();


        } else if (CacheUtils.getString(SubAndSiteActivity.this, "login_type").equals("2")) {

            loginSub();

        }

    }

    private void loginSub() {

        List<AllSiteInfo> accountSubs = new ArrayList<>();

        List<AllSiteInfo> loginSiteInfo;

        //获得登录账号的site Ids
        List<AccountSub> accountSubsList = accountSubDao.querySub(CacheUtils.getString(SubAndSiteActivity.this, "login"));

        //通过siteDao 查询到 AllSiteInfo Ids 的全部信息
        for (int i = 0; i < accountSubsList.size(); i++) {

            loginSiteInfo = allSiteDao.queryById(accountSubsList.get(i).getSite_id());
            accountSubs.addAll(loginSiteInfo);

        }

        //存到已登录账号订阅的数据库中
        for (int i = 0; i < accountSubs.size(); i++) {

            if (loginSiteDao.whereAdd(accountSubs.get(i).getName(), CacheUtils.getString(SubAndSiteActivity.this, "login"))) {

                LoginSiteSub loginSiteSub = new LoginSiteSub();
                loginSiteSub.setSite_id(accountSubs.get(i).getSite_id());
                loginSiteSub.setType(accountSubs.get(i).getType());
                loginSiteSub.setName(accountSubs.get(i).getName());
                loginSiteSub.setDescription(accountSubs.get(i).getDescription());
                loginSiteSub.setPic(accountSubs.get(i).getPic());
                loginSiteSub.setAccount(CacheUtils.getString(SubAndSiteActivity.this, "login"));

                loginSiteDao.add(loginSiteSub);
                hintVisibility();
            }

        }

        final List<LoginSiteSub> loginSiteSubs = loginSiteDao.queryByAccount(CacheUtils.getString(SubAndSiteActivity.this, "login"));

        LoginSubListAdapter loginSubListAdapter = new LoginSubListAdapter(SubAndSiteActivity.this, loginSiteSubs);
        sml.setAdapter(loginSubListAdapter);
        loginSubListAdapter.notifyDataSetChanged();

        sml.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(SubAndSiteActivity.this, SiteActivity.class);
                intent.putExtra(INTENT_KEY_SUBSCRIPTION_SITE, loginSiteSubs.get(position).getSite_id());
                intent.putExtra(INTENT_KEY_SUBSCRIPTION_ID, "my");
                startActivity(intent);

            }
        });


    }

    private void notLoginSub() {

        List<AllSiteInfo> subs = new ArrayList<>();

        //获得非账号登录的订阅站点
        List<NewsSub> newsSubs = newsSubDao.querySubSite();

        //通过siteDao查询到这些站点的全部信息
        for (int i = 0; i < newsSubs.size(); i++) {

            List<AllSiteInfo> allSiteInfos = allSiteDao.queryById(newsSubs.get(i).getSite());

            subs.addAll(allSiteInfos);

        }

        //添加到非账号订阅的数据库中
        for (int i = 0; i < subs.size(); i++) {

            if (notLoginSubDao.orNotAdd(subs.get(i).getName())) {

                NotLoginSub notLoginSub = new NotLoginSub();

                notLoginSub.setSite_id(subs.get(i).getSite_id());
                notLoginSub.setType(subs.get(i).getType());
                notLoginSub.setName(subs.get(i).getName());
                notLoginSub.setDescription(subs.get(i).getDescription());
                notLoginSub.setPic(subs.get(i).getPic());

                notLoginSubDao.add(notLoginSub);

                hintVisibility();

            }


        }

        //在非账号订阅的数据库中查询全部信息
        final List<NotLoginSub> notLoginSubs = notLoginSubDao.queryAll();

        //设置Adapter 显示ListView
        NotLoginSubListAdapter notLoginSubListAdapter = new NotLoginSubListAdapter(SubAndSiteActivity.this, notLoginSubs);

        sml.setAdapter(notLoginSubListAdapter);

        notLoginSubListAdapter.notifyDataSetChanged();

        sml.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(SubAndSiteActivity.this, SiteActivity.class);
                intent.putExtra(INTENT_KEY_SUBSCRIPTION_SITE, notLoginSubs.get(position).getSite_id());
                intent.putExtra(INTENT_KEY_SUBSCRIPTION_ID, "my");
                startActivity(intent);

            }
        });
    }

    private void hintVisibility() {
        tv_hint_.setVisibility(View.GONE);
        tv_.setVisibility(View.GONE);
        iv_.setVisibility(View.GONE);
    }

    private void setMySubscription() {

        mIbDelete.setVisibility(View.GONE);

        String cache = CacheUtils.getString(SubAndSiteActivity.this, Constants.NEWS_SUB_LIST_ALL);

        if (!TextUtils.isEmpty(cache)) {

            progress(cache);

        }

        getSubListAllData();

    }

    private void getSubListAllData() {

        hintVisibility();

        RequestParams p = new RequestParams(Constants.NEWS_SUB_LIST_ALL);

        x.http().get(p, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                CacheUtils.putString(SubAndSiteActivity.this, Constants.NEWS_SUB_LIST_ALL, result);
                progress(result);

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

    private void progress(String result) {

        RecommendList recommendList = JsonSub(result);

        list = recommendList.getData().getList();

        for (int i = 0; i < list.size(); i++) {


            if (allSiteDao.whereAdd(list.get(i).getName())) {

                AllSiteInfo allSiteInfo = new AllSiteInfo();
                allSiteInfo.setName(list.get(i).getName());
                allSiteInfo.setPic(list.get(i).getPic());
                allSiteInfo.setDescription(list.get(i).getBrief());
                allSiteInfo.setSite_id(list.get(i).getId());

                if (i == 0) {

                    allSiteInfo.setType("科技");

                } else {

                    allSiteInfo.setType(list.get(i).getCate_info().get(0).getName());

                }

                allSiteDao.add(allSiteInfo);

            }

        }

        MyAllSubSiteAdapter myAllSubSiteAdapter = new MyAllSubSiteAdapter(this, list);
        sml.setAdapter(myAllSubSiteAdapter);
        myAllSubSiteAdapter.notifyDataSetChanged();

        sml.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(SubAndSiteActivity.this, SiteActivity.class);
                intent.putExtra(INTENT_KEY_SUBSCRIPTION_SITE, list.get(position).getId());
                intent.putExtra(INTENT_KEY_SUBSCRIPTION_ID, "all");
                startActivity(intent);

            }
        });

    }

    private RecommendList JsonSub(String result) {
        return new Gson().fromJson(result, RecommendList.class);
    }

    private class MyIbBack implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            finish();
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

                        SnackBarUtil.showTSnack("已经刷新", getWindow().getDecorView(), R.drawable.ic_info_black_24dp, Color.parseColor("#fafafa"));
                        isRefresh = false;

                    }
                }, 1000);

            }
        }
    }

}
