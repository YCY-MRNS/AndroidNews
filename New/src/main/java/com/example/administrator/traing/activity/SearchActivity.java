package com.example.administrator.traing.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.administrator.traing.R;
import com.example.administrator.traing.adapter.SubscriptionAdapter;
import com.example.administrator.traing.domain.RecommendList;
import com.example.administrator.traing.utlis.CacheUtils;
import com.example.administrator.traing.utlis.Constants;
import com.example.administrator.traing.utlis.StatusBarUtil;
import com.example.administrator.traing.utlis.Utils;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class SearchActivity extends AppCompatActivity {

    @ViewInject(R.id.rv_search)
    private RecyclerView recyclerView;

    @ViewInject(R.id.et_)
    private EditText editText;

    @ViewInject(R.id.tv_hint)
    private TextView tvHint;

    @ViewInject(R.id.pb_wait)
    private ProgressBar bar;

    private List<RecommendList.DataBean.ListBean> list;

    private static final String INTENT_KEY_SUBSCRIPTION_ID = "1";
    public static final String INTENT_KEY_SUBSCRIPTION_SEARCH = "subscription_search";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serach_activiy);
        Utils.stateTransparent(this);
        StatusBarUtil.StatusBarLightMode(SearchActivity.this);
        x.view().inject(this);

        OverScrollDecoratorHelper.setUpOverScroll(recyclerView, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);
        CacheUtils.getString(SearchActivity.this, Constants.SEARCH_RECOMMEND);

        getDataFromNet();

        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Drawable drawable = editText.getCompoundDrawables()[2];
                if (drawable == null)
                    return false;

                if (event.getX() > editText.getWidth()
                        - editText.getPaddingRight()
                        - drawable.getIntrinsicWidth()) {

                    search();

                    tvHint.setText("搜索结果");

                }

                return false;

            }
        });


        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    search();

                    tvHint.setText("搜索结果");

                }
                return false;
            }
        });


    }

    private void search() {


        String editStr = editText.getText().toString();
        RequestParams param = new RequestParams(Constants.SEARCH + editStr);
        x.http().get(param, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                Logger.d("使用XUtils3 联网成功！！");

                //CACHE DATA
                processData(result);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Logger.d("使用XUtils3 联网成功！！" + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Logger.d("使用XUtils3 onCancelled!!!" + cex.getMessage());
            }

            @Override
            public void onFinished() {
                Logger.d("使用XUtils3 onFinished!!!");
            }
        });


    }

    private void getDataFromNet() {
        RequestParams param = new RequestParams(Constants.SEARCH_RECOMMEND);
        x.http().get(param, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                Logger.d("使用XUtils3 联网成功！！");

                //CACHE DATA
                CacheUtils.putString(SearchActivity.this, Constants.SEARCH_RECOMMEND, result);

                processData(result);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Logger.d("使用XUtils3 联网成功！！" + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Logger.d("使用XUtils3 onCancelled!!!" + cex.getMessage());
            }

            @Override
            public void onFinished() {
                Logger.d("使用XUtils3 onFinished!!!");
            }
        });
    }

    private void processData(String result) {

        RecommendList recommendList = parsedJson(result);
        list = recommendList.getData().getList();

        if (list.size() > 0) {

            SubscriptionAdapter subscriptionAdapter = new SubscriptionAdapter(SearchActivity.this, list);
            recyclerView.setAdapter(subscriptionAdapter);
            subscriptionAdapter.notifyDataSetChanged();

            subscriptionAdapter.setOnItemClickListener(new SubscriptionAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {

                    Intent intent = new Intent(SearchActivity.this, SiteActivity.class);
                    intent.putExtra(INTENT_KEY_SUBSCRIPTION_SEARCH, list.get(position).getId());
                    intent.putExtra(INTENT_KEY_SUBSCRIPTION_ID, "s");
                    startActivity(intent);

                }
            });

        }

        bar.setVisibility(View.GONE);

    }

    private RecommendList parsedJson(String result) {

        return new Gson().fromJson(result, RecommendList.class);

    }

}
