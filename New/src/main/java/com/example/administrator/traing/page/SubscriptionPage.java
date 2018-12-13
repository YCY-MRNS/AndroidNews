package com.example.administrator.traing.page;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.example.administrator.traing.R;
import com.example.administrator.traing.activity.SearchActivity;
import com.example.administrator.traing.activity.SiteClassActivity;
import com.example.administrator.traing.adapter.SubHomeAdapter;
import com.example.administrator.traing.base.BasePager;
import com.example.administrator.traing.domain.SubBanner;
import com.example.administrator.traing.utlis.GridSpacingItemDecoration;
import com.example.administrator.traing.utlis.NetMethod;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;
import rx.Subscriber;

/**
 * Created by Administrator on 2017/7/24.
 */

public class SubscriptionPage extends BasePager {

    @BindView(R.id.rv_subscription)
    RecyclerView recyclerView;

    @BindView(R.id.et_search)
    EditText editText;

    public static final String INTENT_KEY_SUBSCRIPTION_SITE = "subscription_site";
    public static final String INTENT_KEY_SUBSCRIPTION_NAME = "subscription_name";

    @SuppressLint({"NewApi", "ValidFragment"})
    public SubscriptionPage(Context context) {
        super(context);
    }

    public SubscriptionPage() {
    }

    @Override
    public View initView() {

        View view = View.inflate(context, R.layout.fragment_subscription, null);

        ButterKnife.bind(SubscriptionPage.this, view);

        return view;

    }

    @Override
    public void initData() {
        super.initData();

        getAllSite();

        OverScrollDecoratorHelper.setUpOverScroll(recyclerView, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);

        editText.setOnClickListener(new MyEditListener());

    }

    private void getAllSite() {


        Subscriber<SubBanner> subscriber = new Subscriber<SubBanner>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(SubBanner subBanner) {

                final List<SubBanner.DataBean.CateBean> cate = subBanner.getData().getCate();
                SubHomeAdapter subHomeAdapter = new SubHomeAdapter(context, cate);

                recyclerView.setAdapter(subHomeAdapter);

                recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, 20, true));

                subHomeAdapter.setOnItemClickListener(new SubHomeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        Intent intent = new Intent(context, SiteClassActivity.class);
                        intent.putExtra(INTENT_KEY_SUBSCRIPTION_SITE, cate.get(position).getId());
                        intent.putExtra(INTENT_KEY_SUBSCRIPTION_NAME, cate.get(position).getName());
                        context.startActivity(intent);

                    }
                });

            }
        };


        NetMethod.getInstance().getNewsSite(0, subscriber);


    }

    private class MyEditListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            context.startActivity(new Intent(context, SearchActivity.class));

        }
    }

}
