package com.example.administrator.traing.utlis;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.androidadvance.topsnackbar.TSnackbar;
import com.example.administrator.traing.R;

/**
 * Created by Administrator on 2017/9/12.
 */

public class SnackBarUtil {

    public static void showTSnack(String s, View view, int iconLeft, int bgColor) {

        TSnackbar tSnackbar = TSnackbar.make(view, s, TSnackbar.LENGTH_SHORT);
        View mSnackView = tSnackbar.getView();
        mSnackView.setPadding(0, 40, 0, 5);
        tSnackbar.setIconRight(R.drawable.ic_done_black_24dp, 20);
        tSnackbar.setIconLeft(iconLeft, 24);
        mSnackView.setBackgroundColor(bgColor);
        TextView textView = (TextView) mSnackView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
        textView.setTextColor(Color.parseColor("#000000"));
        textView.setTextSize(16);
        tSnackbar.show();

    }

    public static void showSubTSnack(String s, View view) {

        TSnackbar tSnackbar = TSnackbar.make(view, s, TSnackbar.LENGTH_SHORT);
        View mSnackView = tSnackbar.getView();
        tSnackbar.setIconRight(R.drawable.ic_done_black_24dp, 20);
        tSnackbar.setIconLeft(R.drawable.ic_info_black_24dp, 24);
        mSnackView.setBackgroundColor(Color.parseColor("#fafafa"));
        TextView textView = (TextView) mSnackView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
        textView.setTextColor(Color.parseColor("#757575"));
        textView.setTextSize(14);
        tSnackbar.show();

    }

}
