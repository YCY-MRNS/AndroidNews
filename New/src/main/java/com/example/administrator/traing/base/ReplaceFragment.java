package com.example.administrator.traing.base;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/6/27.
 */

public class ReplaceFragment extends Fragment {
    private BasePager basePager;

    public ReplaceFragment() {
    }

    @SuppressLint({"NewApi", "ValidFragment"})
    public ReplaceFragment(BasePager basePager) {
        this.basePager = basePager;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return basePager.rootView;
    }
}
