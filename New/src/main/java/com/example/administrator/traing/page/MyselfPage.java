package com.example.administrator.traing.page;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.administrator.traing.R;
import com.example.administrator.traing.activity.CollectAndHisActivity;
import com.example.administrator.traing.activity.LoginActivity;
import com.example.administrator.traing.activity.SubAndSiteActivity;
import com.example.administrator.traing.base.BasePager;
import com.example.administrator.traing.db.Account;
import com.example.administrator.traing.db.AccountCache;
import com.example.administrator.traing.db.AccountCacheDao;
import com.example.administrator.traing.db.AccountDao;
import com.example.administrator.traing.db.AccountSubDao;
import com.example.administrator.traing.db.LoginCollectDao;
import com.example.administrator.traing.db.NewsHistoryDao;
import com.example.administrator.traing.db.NewsSubDao;
import com.example.administrator.traing.db.NotLoginCollectDao;
import com.example.administrator.traing.utlis.CacheDataManager;
import com.example.administrator.traing.utlis.CacheUtils;
import com.example.administrator.traing.utlis.SnackBarUtil;
import com.example.administrator.traing.view.GlideCircleTransform;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sharesdk.onekeyshare.OnekeyShare;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

import static com.example.administrator.traing.R.id.rl_center;

/**
 * Created by Administrator on 2017/7/24.
 */

public class MyselfPage extends BasePager {

    @BindView(R.id.rl_read_list)
    RelativeLayout mReadList;

    @BindView(R.id.rl_my_collect)
    RelativeLayout mMyCollect;

    @BindView(R.id.iv_my_site)
    ImageView mMySite;

    @BindView(R.id.iv_my_sub)
    ImageView mMySub;

    @BindView(R.id.iv_my_collect)
    ImageView mIvMyCollect;

    @BindView(R.id.iv_my_history)
    ImageView mMyHis;

    @BindView(R.id.rl_read_history)
    RelativeLayout mReadHistory;

    @BindView(R.id.rl_sub)
    RelativeLayout mSubList;

    @BindView(R.id.iv_user)
    ImageView mIvUser;

    @BindView(R.id.tv_cache)
    TextView cache;

    @BindView(R.id.rl_clear)
    RelativeLayout mClear;

    @BindView(R.id.rl_share)
    RelativeLayout mShare;

    @BindView(R.id.tv_collect_num)
    TextView mTvCollectNum;

    @BindView(R.id.tv_his_num)
    TextView mTvCollectHis;

    @BindView(R.id.s_double)
    Switch aSwitch;

    @BindView(R.id.tv_user_name)
    TextView mTvLogin;

    @BindView(R.id.tv_user_des)
    TextView mTvUserDes;

    @BindView(rl_center)
    LinearLayout mCenter;

    @BindView(R.id.tv_sub_num)
    TextView mSunNum;

    @BindView(R.id.sv_my)
    ScrollView scrollView;

    private View mRootView;
    private NotLoginCollectDao notLoginCollectDao;
    private LoginCollectDao loginCollectDao;
    private NewsHistoryDao newsHistoryDao;
    private AccountCacheDao accountCacheDao;
    private AccountDao accountDao;
    private NewsSubDao newsSubDao;
    private AccountSubDao accountSubDao;

    public MyselfPage() {

    }

    @SuppressLint({"NewApi", "ValidFragment"})
    public MyselfPage(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        mRootView = View.inflate(context, R.layout.fragment_myself2, null);
        ButterKnife.bind(this, mRootView);

        notLoginCollectDao = new NotLoginCollectDao(context);
        newsHistoryDao = new NewsHistoryDao(context);
        accountDao = new AccountDao(context);
        newsSubDao = new NewsSubDao(context);
        loginCollectDao = new LoginCollectDao(context);
        accountCacheDao = new AccountCacheDao(context);
        accountSubDao = new AccountSubDao(context);

        return mRootView;

    }

    @Override
    public void initData() {
        super.initData();

        OverScrollDecoratorHelper.setUpOverScroll(scrollView);
        mTvCollectNum.setText(notLoginCollectDao.queryByDistinctTitle().size() + "");
        mTvCollectHis.setText(newsHistoryDao.listAll().size() + "");
        mSunNum.setText(newsSubDao.querySubSite().size() + "");

        mMySite.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
        mMyHis.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
        mMySub.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
        mIvMyCollect.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);

        new Thread(runnable).start();

        setSwitch();

        mReadList.setOnClickListener(new MyReadListClick());
        mMyCollect.setOnClickListener(new MyCollectClick());
        mReadHistory.setOnClickListener(new MyReadStoryClick());
        mSubList.setOnClickListener(new MySubListListener());
        mClear.setOnClickListener(new MyCacheClear());
        mShare.setOnClickListener(new MyShare());
        mCenter.setOnClickListener(new MyLogin());


        try {
            cache.setText(CacheDataManager.getTotalCacheSize(context));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void setSwitch() {
        if (CacheUtils.getString(context, "switch").equals("yes")) {

            aSwitch.setChecked(true);
        } else {

            aSwitch.setChecked(false);
        }


        aSwitch.setOnCheckedChangeListener(new MySwitch());
    }

    private class MyReadListClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, SubAndSiteActivity.class);
            intent.putExtra("1", "全部站点");
            context.startActivity(intent);
        }
    }

    private class MyCollectClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, CollectAndHisActivity.class);
            intent.putExtra("1", "我的收藏");
            context.startActivity(intent);
        }
    }

    private class MyReadStoryClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, CollectAndHisActivity.class);
            intent.putExtra("1", "阅读历史");
            context.startActivity(intent);
        }
    }

    private class MyCacheClear implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            SnackBarUtil.showTSnack("正在清理", mRootView, R.drawable.ic_delete_black_24dp, Color.parseColor("#FAFAFA"));

            new Thread(new Runnable() {
                @Override
                public void run() {

                    CacheDataManager.clearAllCache(context);

                    try {

                        Thread.sleep(2000);

                        if (CacheDataManager.getTotalCacheSize(context).startsWith("0")) {

                            handler.sendEmptyMessage(0);

                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        }

    }

    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        oks.setTitle("ReadingStyle");
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl("");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我正在使用 ReadingStyle 精品阅读 ， 推荐你使用！");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("ShareSDK");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("");

// 启动分享GUI
        oks.show(context);
    }

    public void account() {

        mTvCollectNum.setText(notLoginCollectDao.queryByDistinctTitle().size() + "");


        if (CacheUtils.getString(context, "login_type").equals("1")) {

            mTvCollectHis.setText(newsHistoryDao.listAll().size() + "");
            mSunNum.setText(newsSubDao.querySubSite().size() + "");
            mTvCollectNum.setText(notLoginCollectDao.queryByDistinctTitle().size() + "");


        } else {

            mSunNum.setText(accountSubDao.querySubsDistinct(CacheUtils.getString(context, "login")).size() + "");
            mTvCollectNum.setText(loginCollectDao.queryByName(CacheUtils.getString(context, "login")).size() + "");

        }


        if (CacheUtils.getString(context, "exit").equals("1")) {

            mTvLogin.setText("登录账号");
            CacheUtils.putString(context, "exit", "2");

            if (CacheUtils.getString(context, "type").equals("1")) {

                setAccount();
                CacheUtils.putString(context, "type", "2");

            }

        } else if (CacheUtils.getString(context, "type").equals("1")) {

            setAccount();


        } else {

            List<AccountCache> accountCacheList = accountCacheDao.queryId(CacheUtils.getString(context, "login"));

            if (!TextUtils.isEmpty(CacheUtils.getString(context, "login")) && accountCacheList.size() > 0) {

                if (!TextUtils.isEmpty(accountCacheList.get(0).getName())) {

                    setAccount();


                } else {

                    reset();

                }

            } else {

                reset();

            }
        }


    }

    private void setAccount() {

        List<Account> accountList = accountDao.queryId(CacheUtils.getString(context, "login"));

        for (Account account : accountList) {

            if (account.getPic() != null) {

                Glide.with(context.getApplicationContext())
                        .load(account.getPic())
                        .crossFade()
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .transform(new GlideCircleTransform(context))
                        .error(R.drawable.people)
                        .into(mIvUser);

            }


            mTvUserDes.setText(account.getDescription());
            mTvLogin.setText(account.getName());
            mTvUserDes.setVisibility(View.VISIBLE);

        }
    }

    private void reset() {
        mTvLogin.setText("登录账号");
        mTvUserDes.setVisibility(View.VISIBLE);

        Glide.with(context.getApplicationContext())
                .load(R.drawable.people)
                .crossFade()
                .centerCrop()
                .error(R.drawable.people)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transform(new GlideCircleTransform(context))
                .into(mIvUser);

    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {

            while (true) {

                try {

                    Thread.sleep(800);
                    handler.sendEmptyMessage(2);

                } catch (InterruptedException e) {

                    e.printStackTrace();

                }
            }

        }
    };


    private Handler handler = new Handler() {

        public void handleMessage(android.os.Message msg) {

            switch (msg.what) {

                case 0:

                    SnackBarUtil.showTSnack("清理完成", mRootView, R.drawable.ic_delete_black_24dp, Color.parseColor("#FAFAFA"));

                    try {

                        cache.setText(CacheDataManager.getTotalCacheSize(context));

                    } catch (Exception e) {

                        e.printStackTrace();

                    }

                    break;

                case 2:

                    account();

                    loginType();

                    break;

            }

        }


    };

    private void loginType() {


        if (mTvLogin.getText().equals("登录账号")) {

            CacheUtils.putString(context, "login_type", "1");

        } else {

            CacheUtils.putString(context, "login_type", "2");

        }


    }

    private class MyShare implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            showShare();


        }

    }

    private class MySwitch implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                // 开启switch，设置提示信息
                SnackBarUtil.showTSnack("开启双击！", mRootView, R.drawable.ic_info_black_24dp, Color.parseColor("#fafafa"));
                CacheUtils.putString(context, "switch", "yes");

            } else {
                // 关闭swtich，设置提示信息
                SnackBarUtil.showTSnack("关闭双击！", mRootView, R.drawable.ic_info_black_24dp, Color.parseColor("#fafafa"));
                CacheUtils.putString(context, "switch", "no");
            }
        }
    }

    private class MyLogin implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            if (mTvLogin.getText().equals("登录账号")) {

                Intent intent = new Intent(context, LoginActivity.class);
                intent.putExtra("type", 1);
                context.startActivity(intent);

            } else {

                Intent intent = new Intent(context, LoginActivity.class);
                intent.putExtra("type", 2);
                CacheUtils.putString(context, "hint_name", mTvLogin.getText().toString());
                context.startActivity(intent);

            }

        }

    }

    private class MySubListListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, SubAndSiteActivity.class);
            intent.putExtra("1", "订阅中心");
            context.startActivity(intent);

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}













