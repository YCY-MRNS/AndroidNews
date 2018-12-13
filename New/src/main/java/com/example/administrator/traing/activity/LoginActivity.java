package com.example.administrator.traing.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.traing.R;
import com.example.administrator.traing.db.Account;
import com.example.administrator.traing.db.AccountCache;
import com.example.administrator.traing.db.AccountCacheDao;
import com.example.administrator.traing.db.AccountDao;
import com.example.administrator.traing.utlis.CacheUtils;
import com.example.administrator.traing.utlis.SnackBarUtil;
import com.example.administrator.traing.utlis.StatusBarUtil;
import com.example.administrator.traing.utlis.Utils;
import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.tools_bar_title_name)
    TextView title;

    @BindView(R.id.tools_bar_title_cancel)
    TextView cancel;

    @BindView(R.id.et_name)
    EditText mEtName;

    @BindView(R.id.et_pw)
    EditText mEtPw;

    @BindView(R.id.btn_register)
    Button mBtRegister;

    @BindView(R.id.btn_login)
    Button mBtLogin;

    private AccountCacheDao accountCacheDao;
    private AccountDao accountDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (getIntent().getIntExtra("type", 0) == 2) {

            Intent intent = new Intent(LoginActivity.this, AccountActivity.class);
            intent.putExtra("name", CacheUtils.getString(LoginActivity.this, CacheUtils.getString(LoginActivity.this, "login")));
            startActivity(intent);

            finish();
            return;

        }

        Utils.stateTransparent(this);
        StatusBarUtil.StatusBarLightMode(this);
        ButterKnife.bind(this);
        accountCacheDao = new AccountCacheDao(this);
        accountDao = new AccountDao(this);

        init();

    }

    private void init() {

        title.setText("我的");
        cancel.setText("取消");

        cancel.setOnClickListener(new CancelListener());
        mBtRegister.setOnClickListener(new MyRegisterListener());
        mBtLogin.setOnClickListener(new MyLoginListener());


    }

    private class MyRegisterListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            CacheUtils.putString(LoginActivity.this, "type", "1");

            if (TextUtils.isEmpty(mEtName.getText().toString()) || TextUtils.isEmpty((mEtPw.getText().toString()))) {

                SnackBarUtil.showTSnack("账号或者密码不能为空！", getWindow().getDecorView(), R.drawable.ic_info_black_24dp, Color.parseColor("#fafafa"));

            } else if (TextUtils.isEmpty(mEtName.getText().toString()) && TextUtils.isEmpty((mEtPw.getText().toString()))) {

                SnackBarUtil.showTSnack("账号和密码不能为空！", getWindow().getDecorView(), R.drawable.ic_info_black_24dp, Color.parseColor("#fafafa"));

            } else {

                if (accountDao.queryId(mEtName.getText().toString()).size() < 1) {

                    Account account = new Account();
                    account.setName(mEtName.getText().toString());
                    account.setPw(mEtPw.getText().toString());
                    account.setPic("");
                    account.setDescription("精致阅读，简单生活");
                    accountDao.addMyAccount(account);

                    AccountCache accountCache = new AccountCache();
                    accountCache.setName(mEtName.getText().toString());
                    accountCache.setPw(mEtPw.getText().toString());
                    accountCache.setDescription("精致阅读，简单生活");
                    accountCacheDao.addMyAccount(accountCache);

                    CacheUtils.putString(LoginActivity.this, "login", mEtName.getText().toString());

                    Intent intent = new Intent(new Intent(LoginActivity.this, MainActivity.class));
                    intent.putExtra("login", "1");
                    startActivity(intent);

                    finish();

                } else {

                    SnackBarUtil.showTSnack("已存在账号，请直接登录！", getWindow().getDecorView(), R.drawable.ic_info_black_24dp, Color.parseColor("#fafafa"));

                }
            }

        }
    }

    private class MyLoginListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            CacheUtils.putString(LoginActivity.this, "type", "2");

            List<Account> accountList = accountDao.queryId(mEtName.getText().toString());

            if (accountList != null && accountList.size() > 0) {

                CacheUtils.putString(LoginActivity.this, "login", accountList.get(0).getName());

                if (!accountList.get(0).getName().equals(mEtName.getText().toString()) || !accountList.get(0).getPw().equals(mEtPw.getText().toString())) {

                    SnackBarUtil.showTSnack("账号或者密码错误！", getWindow().getDecorView(), R.drawable.ic_info_black_24dp, Color.parseColor("#fafafa"));

                } else {

                    CacheUtils.putString(LoginActivity.this, "login", accountList.get(0).getName());
                    CacheUtils.putString(LoginActivity.this, "type", "1");

                    Logger.d("name ------------->" + CacheUtils.getString(LoginActivity.this, "login"));

                    Intent intent = new Intent(new Intent(LoginActivity.this, MainActivity.class));
                    intent.putExtra("login", "1");
                    startActivity(intent);

                    finish();

                }

            } else {

                SnackBarUtil.showTSnack("无效账号！", getWindow().getDecorView(), R.drawable.ic_info_black_24dp, Color.parseColor("#fafafa"));

            }

        }
    }

    private class CancelListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            finish();
        }
    }


}
