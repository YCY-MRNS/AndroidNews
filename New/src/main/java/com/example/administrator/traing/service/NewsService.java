package com.example.administrator.traing.service;

import com.example.administrator.traing.domain.Common;
import com.example.administrator.traing.domain.RecommendList;
import com.example.administrator.traing.domain.SubBanner;
import com.example.administrator.traing.domain.SubCenterList;
import com.example.administrator.traing.domain.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/8/29.
 */

public interface NewsService {


    @GET("/index.php?r=myCenter/recommendList")
    Observable<RecommendList> getData(

            @Query("offset") int offset,
            @Query("page_size") int page_size

    );

    @GET("/index.php?r=myCenter/show")
    Observable<SubBanner> getSite(

            @Query("site_ids") int site_ids

    );

    @GET("/v1/weather/query?key=appkey&")
    Call<Weather> getData(

            @Query("city") String city

    );

    @GET("/index.php?r=article/getList")
    Call<Common> getData(

            @Query("site_id") int site_id,
            @Query("page_size") int page_size,
            @Query("offset") int offset

    );

    @GET("/index.php?r=article/getList")
    Call<SubCenterList> getDatas(

            @Query("site_id") int site_id,
            @Query("page_size") int page_size,
            @Query("offset") int offset

    );


}
