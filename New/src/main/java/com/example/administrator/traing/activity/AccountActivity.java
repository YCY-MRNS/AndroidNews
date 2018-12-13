package com.example.administrator.traing.activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.administrator.traing.R;
import com.example.administrator.traing.db.Account;
import com.example.administrator.traing.db.AccountCacheDao;
import com.example.administrator.traing.db.AccountDao;
import com.example.administrator.traing.utlis.CacheUtils;
import com.example.administrator.traing.utlis.SnackBarUtil;
import com.example.administrator.traing.utlis.StatusBarUtil;
import com.example.administrator.traing.utlis.Utils;
import com.example.administrator.traing.view.GlideCircleTransform;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AccountActivity extends AppCompatActivity {

    @BindView(R.id.tools_bar_title_name)
    TextView title;

    @BindView(R.id.tools_bar_title_cancel)
    TextView cancel;

    @BindView(R.id.tools_bar_title_save)
    TextView save;

    @BindView(R.id.btn_cancel)
    Button btn_cancel;

    @BindView(R.id.et_name)
    EditText mEtName;

    @BindView(R.id.et_des)
    EditText mEtDes;

    @BindView(R.id.iv_head)
    ImageView mIbHead;

    private AccountDao accountDao;
    private AccountCacheDao accountCacheDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        ButterKnife.bind(this);
        Utils.stateTransparent(this);
        StatusBarUtil.StatusBarLightMode(this);

        accountCacheDao = new AccountCacheDao(this);
        accountDao = new AccountDao(this);

        init();

    }

    private void init() {

        save.setVisibility(View.VISIBLE);
        title.setText("我的");
        cancel.setText("取消");
        save.setText("保存");

        mEtName.setOnClickListener(new MyNameListener());
        mEtDes.setOnClickListener(new MyDesListener());

        save.setOnClickListener(new MySaveListener());
        mIbHead.setOnClickListener(new MyHeadChanger());

        mEtName.setHint(CacheUtils.getString(AccountActivity.this, "hint_name"));

        List<Account> accountList = accountDao.queryId(CacheUtils.getString(AccountActivity.this, "hint_name"));

        for (Account a : accountList) {

            mEtDes.setHint(a.getDescription());

            Glide.with(AccountActivity.this)
                    .load(a.getPic())
                    .crossFade()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .transform(new GlideCircleTransform(AccountActivity.this))
                    .error(R.drawable.people)
                    .into(mIbHead);

        }

        btn_cancel.setOnClickListener(new MyCancelListener());
        cancel.setOnClickListener(new MyCallBackListener());

    }

    private class MyCancelListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            CacheUtils.putString(AccountActivity.this, "exit", "1");

            accountCacheDao.deleteByName(CacheUtils.getString(AccountActivity.this, "login"));

            finish();

        }
    }

    private class MyCallBackListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            finish();
        }
    }

    private class MyNameListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            mEtName.setHint("");
            mEtName.setCursorVisible(true);
        }
    }

    private class MyDesListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            mEtDes.setHint("");
            mEtDes.setCursorVisible(true);
        }
    }

    private class MySaveListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            List<Account> accountList = accountDao.queryId(CacheUtils.getString(AccountActivity.this, "hint_name"));


            if (TextUtils.isEmpty(mEtName.getText()) && !TextUtils.isEmpty(mEtDes.getText())) {

                for (Account a : accountList) {

                    accountDao.update(a.getId(), a.getName(), a.getPw(), mEtDes.getText().toString(), a.getPic());

                }

                finish();


            } else if (!TextUtils.isEmpty(mEtName.getText()) && !TextUtils.isEmpty(mEtDes.getText())) {

                for (Account a : accountList) {

                    accountDao.update(a.getId(), mEtName.getText().toString(), a.getPw(), mEtDes.getText().toString(), a.getPic());

                }

                CacheUtils.putString(AccountActivity.this, "login", mEtName.getText().toString());

                finish();


            } else if (!TextUtils.isEmpty(mEtName.getText()) && TextUtils.isEmpty(mEtDes.getText())) {

                for (Account a : accountList) {

                    accountDao.update(a.getId(), mEtName.getText().toString(), a.getPw(), a.getDescription(), a.getPic());

                }

                finish();

            } else {

                SnackBarUtil.showTSnack("无需保存", getWindow().getDecorView(), R.drawable.ic_info_black_24dp, Color.parseColor("#fafafa"));

            }

        }
    }

    private class MyHeadChanger implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, 1);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {

            Uri uri = data.getData();
            String img_url = uri.getPath();

            List<Account> accountList = accountDao.queryId(CacheUtils.getString(AccountActivity.this, "hint_name"));

            for (Account account : accountList) {

                accountDao.update(account.getId(), account.getName(), account.getPw(), account.getDescription(), uri.toString());

            }

            Glide.with(AccountActivity.this)
                    .load(uri.toString())
                    .crossFade()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .transform(new GlideCircleTransform(AccountActivity.this))
                    .into(mIbHead);

            super.onActivityResult(requestCode, resultCode, data);

        }
    }

}
