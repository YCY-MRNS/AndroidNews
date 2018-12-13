package com.example.administrator.traing.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by Administrator on 2017/6/27.
 */

public abstract class BasePager extends Fragment {
    public Context context;

    public View rootView;
    public boolean isInitData = false;

    @SuppressLint({"NewApi", "ValidFragment"})
    public BasePager(Context context) {
        this.context = context;
        rootView = initView();
    }

    protected BasePager() {
    }


    public abstract View initView();

    public void initData() {

    }
}
