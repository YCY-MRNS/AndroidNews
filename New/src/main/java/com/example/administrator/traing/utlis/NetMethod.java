package com.example.administrator.traing.utlis;

import com.example.administrator.traing.domain.RecommendList;
import com.example.administrator.traing.domain.SubBanner;
import com.example.administrator.traing.service.NewsService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/8/29.
 */


public class NetMethod {

    private static final int TIMEOUT = 5;
    private NewsService newsService;


    private NetMethod() {

        OkHttpClient.Builder okHttp = new OkHttpClient.Builder();
        okHttp.connectTimeout(TIMEOUT, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttp.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Constants.BASE)
                .build();

        newsService = retrofit.create(NewsService.class);

    }

    private static class SingleInstance {
        private static final NetMethod INSTANCE = new NetMethod();
    }


    public static NetMethod getInstance() {

        return SingleInstance.INSTANCE;

    }


    public void getNewList(int offset, int page, Subscriber<RecommendList> subscriber) {
        newsService.getData(offset, page)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


    public void getNewsSite(int id, Subscriber<SubBanner> subscriber) {
        newsService.getSite(0)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


}
