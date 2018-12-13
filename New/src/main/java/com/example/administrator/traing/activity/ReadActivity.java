package com.example.administrator.traing.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidadvance.topsnackbar.TSnackbar;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.administrator.traing.R;
import com.example.administrator.traing.base.MenuDetailBasePage;
import com.example.administrator.traing.db.LoginCollect;
import com.example.administrator.traing.db.LoginCollectDao;
import com.example.administrator.traing.db.NotLoginCollect;
import com.example.administrator.traing.db.NotLoginCollectDao;
import com.example.administrator.traing.domain.EvenBusEven;
import com.example.administrator.traing.page.HomePage;
import com.example.administrator.traing.utlis.CacheUtils;
import com.example.administrator.traing.utlis.StatusBarUtil;
import com.example.administrator.traing.utlis.Utils;
import com.example.administrator.traing.view.GlideCircleTransform;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import cn.sharesdk.onekeyshare.OnekeyShare;

public class ReadActivity extends AppCompatActivity implements View.OnClickListener {

    private static int TAG = 4;
    private ImageButton ibBack;
    private ImageButton ibShare;
    private ImageButton ibCollection;
    private WebView webView;
    private View view;
    private String url;

    private ImageView ivNewsFrom;
    private TextView tvTitleRecommend;
    private TextView tvNewsDesc;
    private TextView tvTitle;

    private String name;
    private String img;
    private String desc;
    private String title;
    private boolean isCollect = false;
    private NotLoginCollectDao notLoginCollectDao;
    private GestureDetector mGestureDetector;
    private LoginCollectDao loginCollectDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        Utils.stateTransparent(this);
        StatusBarUtil.StatusBarLightMode(this);

        initView();
        setWebView();

    }

    private void initView() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_web);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");

        view = findViewById(R.id.view);
        webView = (WebView) findViewById(R.id.wv);
        ibBack = (ImageButton) findViewById(R.id.ib_back);
        ibShare = (ImageButton) findViewById(R.id.ib_share);
        tvNewsDesc = (TextView) findViewById(R.id.tv_news_desc);
        tvTitle = ((TextView) findViewById(R.id.tv_title_read));
        ivNewsFrom = (ImageView) findViewById(R.id.iv_news_from);
        ibCollection = (ImageButton) findViewById(R.id.ib_collection);
        tvTitleRecommend = (TextView) findViewById(R.id.tv_title_recommend);

        ibBack.setOnClickListener(this);
        EventBus.getDefault().register(this);
        loginCollectDao = new LoginCollectDao(this);
        notLoginCollectDao = new NotLoginCollectDao(ReadActivity.this);

        webView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return mGestureDetector.onTouchEvent(event);
            }
        });

        mGestureDetector = new GestureDetector(this, new MyGestureListener());

    }

    @Override
    public void onClick(View v) {
        if (v == ibBack) {

            finish();

        } else if (v == ibShare) {

            showShare();


        } else if (v == ibCollection) {

            if (!isCollect) {

                isCollects();

                showCollect();

            } else {

                notCollects();

                showCancel();

            }
        }
    }

    private void notCollects() {

        isCollect = false;
        ibCollection.setBackground(getResources().getDrawable(R.drawable.ic_bookmark_border_black_24dp));

        if (CacheUtils.getString(ReadActivity.this, "login_type").equals("1")) {
            notLoginCollectDao.deleteByTitle(title);
        } else {
            loginCollectDao.deleteByTitle(title, CacheUtils.getString(ReadActivity.this, "login"));
        }

    }

    private void isCollects() {

        isCollect = true;
        ibCollection.setBackground(getResources().getDrawable(R.drawable.ic_bookmark_black_24dp));
        if (CacheUtils.getString(ReadActivity.this, "login_type").equals("1")) {

            NotLoginCollect collect = new NotLoginCollect();
            collect.setUrl(url);
            collect.setFrom(name);
            collect.setImg(img);
            collect.setTitle(title);
            notLoginCollectDao.addMyCollect(collect);

        } else if (CacheUtils.getString(ReadActivity.this, "login_type").equals("2")) {

            if (loginCollectDao.whereAdd(title, CacheUtils.getString(ReadActivity.this, "login"))) {

                LoginCollect collect = new LoginCollect();
                collect.setUrl(url);
                collect.setTitle(title);
                collect.setFrom(name);
                collect.setImg(img);
                collect.setName(CacheUtils.getString(ReadActivity.this, "login"));

                loginCollectDao.addMyCollect(collect);

            }

        }


    }

    private void showCancel() {

        TSnackbar tSnackbar = TSnackbar.make(getWindow().getDecorView(), "取消收藏", TSnackbar.LENGTH_SHORT).setAction("撤销", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCollects();
            }
        });

        tSnackbar.setActionTextColor(Color.parseColor("#9C27B0"));
        View mSnackView = tSnackbar.getView();
        mSnackView.setPadding(0, 40, 0, 5);
        tSnackbar.setIconLeft(R.drawable.ic_bookmark_border_black_24dp, 24);
        mSnackView.setBackgroundColor(Color.parseColor("#fafafa"));
        TextView textView = (TextView) mSnackView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
        textView.setTextColor(Color.parseColor("#000000"));
        textView.setTextSize(16);
        tSnackbar.show();

    }

    private void showCollect() {


        TSnackbar tSnackbar = TSnackbar.make(getWindow().getDecorView(), "添加收藏", TSnackbar.LENGTH_SHORT).setAction("撤销", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notCollects();
            }
        });

        tSnackbar.setActionTextColor(Color.parseColor("#9C27B0"));
        View mSnackView = tSnackbar.getView();
        mSnackView.setPadding(0, 40, 0, 5);
        tSnackbar.setIconLeft(R.drawable.ic_bookmark_black_24dp, 24);
        mSnackView.setBackgroundColor(Color.parseColor("#fafafa"));
        TextView textView = (TextView) mSnackView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
        textView.setTextColor(Color.parseColor("#000000"));
        textView.setTextSize(16);
        tSnackbar.show();


    }

    private void setWebView() {

        switch (getIntent().getStringExtra("intent_type")) {
            case "recommend":

                url = getIntent().getStringExtra(HomePage.INTENT_URL_KEY);
                name = getIntent().getStringExtra(HomePage.INTENT_FROM_NAME);
                img = getIntent().getStringExtra(HomePage.INTENT_FROM_IMG);
                desc = getIntent().getStringExtra(HomePage.INTENT_FROM_DESC);
                title = getIntent().getStringExtra(HomePage.INTENT_NEWS_TITLE);

                break;
            case "sub":

                url = getIntent().getStringExtra(SiteActivity.INTENT_KEY_URL);
                name = getIntent().getStringExtra(SiteActivity.INTENT_FROM_NAME);
                img = getIntent().getStringExtra(SiteActivity.INTENT_FROM_IMG);
                desc = getIntent().getStringExtra(SiteActivity.INTENT_FROM_DESC);
                title = getIntent().getStringExtra(SiteActivity.INTENT_NEWS_TITLE);


                break;
            case "detail":

                url = getIntent().getStringExtra(MenuDetailBasePage.INTENT_KEY_URL);
                name = getIntent().getStringExtra(MenuDetailBasePage.INTENT_FROM_NAME);
                img = getIntent().getStringExtra(MenuDetailBasePage.INTENT_FROM_IMG);
                desc = getIntent().getStringExtra(MenuDetailBasePage.INTENT_FROM_DESC);
                title = getIntent().getStringExtra(MenuDetailBasePage.INTENT_NEWS_TITLE);

                break;

            case "history":

                url = getIntent().getStringExtra(SubAndSiteActivity.INTENT_KEY_URL);
                name = getIntent().getStringExtra(SubAndSiteActivity.INTENT_FROM_NAME);
                img = getIntent().getStringExtra(SubAndSiteActivity.INTENT_FROM_IMG);
                title = getIntent().getStringExtra(SubAndSiteActivity.INTENT_NEWS_TITLE);

                break;

            case "collect":

                url = getIntent().getStringExtra(SubAndSiteActivity.INTENT_KEY_URL);
                name = getIntent().getStringExtra(SubAndSiteActivity.INTENT_FROM_NAME);
                img = getIntent().getStringExtra(SubAndSiteActivity.INTENT_FROM_IMG);
                title = getIntent().getStringExtra(SubAndSiteActivity.INTENT_NEWS_TITLE);

                break;
            case "notification":

                url = getIntent().getStringExtra("url");
                name = getIntent().getStringExtra("name");
                img = getIntent().getStringExtra("img");
                desc = getIntent().getStringExtra("des");
                title = getIntent().getStringExtra("title");

                break;

        }


        ibShare.setOnClickListener(this);
        ibCollection.setOnClickListener(this);


        if (CacheUtils.getString(ReadActivity.this, "login_type").equals("1")) {

            if (notLoginCollectDao.queryListSizeByTitle(title) != 0) {

                isCollect = true;
                ibCollection.setBackground(getResources().getDrawable(R.drawable.ic_bookmark_black_24dp));

            } else {

                isCollect = false;
                ibCollection.setBackground(getResources().getDrawable(R.drawable.ic_bookmark_border_black_24dp));

            }

        } else if (CacheUtils.getString(ReadActivity.this, "login_type").equals("2")) {

            if (loginCollectDao.queryListSizeByjTitle(title, CacheUtils.getString(ReadActivity.this, "login"))) {

                isCollect = true;
                ibCollection.setBackground(getResources().getDrawable(R.drawable.ic_bookmark_black_24dp));

            } else {

                isCollect = false;
                ibCollection.setBackground(getResources().getDrawable(R.drawable.ic_bookmark_border_black_24dp));

            }

        }


        tvTitle.setText(name);
        tvTitleRecommend.setText(name);
        tvNewsDesc.setText(desc);

        Glide.with(this)
                .load(img)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .centerCrop()
                .transform(new GlideCircleTransform(this))
                .into(ivNewsFrom);

        webView.loadUrl(url);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBlockNetworkImage(false);
        webView.getSettings().setTextSize(WebSettings.TextSize.NORMAL);


        final String javascript = "javascript:function hideOther() {" +
                "document.getElementsByClassName('footer')[0].remove();" +
                "var divs = document.getElementsByTagName('div');" +
                "var lastDiv = divs[divs.length-1];" +
                "lastDiv.remove();" +
                "document.getElementsByClassName('download-wrapper')[0].remove();" +
                "document.getElementsByClassName('intro')[1].remove();}";


        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                view.loadUrl(javascript);
                view.loadUrl("javascript:hideOther();");

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        EventBus.getDefault().post(new EvenBusEven(2));
                    }
                }, 1000);


            }

        });


    }

    private void showShare() {

        OnekeyShare oks = new OnekeyShare();
        oks.disableSSOWhenAuthorize();
        oks.setTitle(title);
        oks.setTitleUrl(url);
        oks.setText(desc);
        oks.setImageUrl(img);
        oks.setUrl(url);
        oks.setComment("我是测试评论文本");
        oks.setSite("ShareSDK");
        oks.setSiteUrl(url);

        oks.show(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setVisibility(EvenBusEven even) {
        if (even.getEven() == 2) {

            view.setVisibility(View.GONE);

        }

    }

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDoubleTap(MotionEvent e) {

            if (TAG % 2 == 0) {

                if (CacheUtils.getString(ReadActivity.this, "switch").equals("yes")) {

                    TAG = 4;
                    finish();

                }

                TAG++;

            } else {

                TAG++;
            }
            return super.onDoubleTap(e);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
