package com.example.administrator.traing.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.administrator.traing.R;
import com.example.administrator.traing.base.BasePager;
import com.example.administrator.traing.base.ReplaceFragment;
import com.example.administrator.traing.page.ExplorePage;
import com.example.administrator.traing.page.MyselfPage;
import com.example.administrator.traing.page.HomePage;
import com.example.administrator.traing.page.SubscriptionPage;
import com.example.administrator.traing.utlis.StatusBarUtil;
import com.example.administrator.traing.utlis.Utils;

import java.util.ArrayList;
import java.util.List;

/*flash 页面 */

public class MainActivity extends AppCompatActivity {

    private static final String CURRENT_FRAGMENT = "STATE_FRAGMENT_SHOW";
    private BottomNavigationBar mBottomNavigationBar;
    private int position;
    private List<BasePager> pages;
    private FrameLayout frameLayout;
    private FragmentManager fragmentManager;
    private FragmentTransaction ft;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Utils.stateTransparent(MainActivity.this);
        StatusBarUtil.StatusBarLightMode(MainActivity.this);


        findView();

        bottomStyle();

        if (getIntent().getStringExtra("login") != null) {

            if (getIntent().getStringExtra("login").equals("1")) {

                mBottomNavigationBar.selectTab(3);
                MainActivity.this.position = 3;

                StatusBarUtil.StatusBarLightMode(MainActivity.this);
                setFrameLayout();

            } else {

                setFrameLayout();

            }
        }

        setFrameLayout();

    }


    private void dismissBar() {
        View decorView = getWindow().getDecorView();

        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;

        decorView.setSystemUiVisibility(uiOptions);
    }


    private void findView() {

        mBottomNavigationBar = ((BottomNavigationBar) findViewById(R.id.bottomNavigationBar));
        frameLayout = (FrameLayout) findViewById(R.id.fl_content);

    }

    private void setFrameLayout() {

        pages = new ArrayList<>();
        pages.add(new HomePage(this));
        pages.add(new ExplorePage(this));
        pages.add(new SubscriptionPage(this));
        pages.add(new MyselfPage(this));

        fragmentManager = getSupportFragmentManager();
        ft = fragmentManager.beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

        ft.replace(R.id.fl_content, new ReplaceFragment(getBasePager()));
        ft.commit();

    }

    private BasePager getBasePager() {

        BasePager basePager = pages.get(position);
        if (basePager != null && !basePager.isInitData) {
            basePager.initData();
            basePager.isInitData = true;
        }
        return basePager;

    }

    private void bottomStyle() {

        mBottomNavigationBar.setTabSelectedListener(new MyTabSelectedListener());
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mBottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_fiber_new_black_48dp, R.string.tab_recommend).setActiveColorResource(R.color.secondary_text))
                .addItem(new BottomNavigationItem(R.drawable.ic_explore_black_48dp, R.string.tab_explore).setActiveColorResource(R.color.secondary_text))
                .addItem(new BottomNavigationItem(R.drawable.ic_dashboard_black_48dp, R.string.tab_subscription).setActiveColorResource(R.color.secondary_text))
                .addItem(new BottomNavigationItem(R.drawable.user1, R.string.tab_myself).setActiveColorResource(R.color.secondary_text))
                .setBarBackgroundColor(R.color.icons)
                .initialise();

    }

    private class MyTabSelectedListener implements BottomNavigationBar.OnTabSelectedListener {

        @Override
        public void onTabSelected(int position) {
            switch (position) {
                default:
                    MainActivity.this.position = 0;
                    break;
                case 1:
                    MainActivity.this.position = 1;
                    StatusBarUtil.StatusBarLightMode(MainActivity.this);
                    break;
                case 2:
                    MainActivity.this.position = 2;
                    StatusBarUtil.StatusBarLightMode(MainActivity.this);
                    break;
                case 3:
                    MainActivity.this.position = 3;
                    StatusBarUtil.StatusBarLightMode(MainActivity.this);
                    break;
            }

            setFrameLayout();
        }

        @Override
        public void onTabUnselected(int position) {

        }

        @Override
        public void onTabReselected(int position) {

        }
    }


}
