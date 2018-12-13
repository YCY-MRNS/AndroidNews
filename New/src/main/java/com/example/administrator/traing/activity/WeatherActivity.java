package com.example.administrator.traing.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.administrator.traing.R;
import com.example.administrator.traing.adapter.WeatherAdapter;
import com.example.administrator.traing.domain.Weather;
import com.example.administrator.traing.service.NewsService;
import com.example.administrator.traing.utlis.CacheUtils;
import com.example.administrator.traing.utlis.Constants;
import com.example.administrator.traing.utlis.Utils;
import com.example.administrator.traing.view.FullyLinearLayoutManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.BlurTransformation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherActivity extends AppCompatActivity {

    @BindView(R.id.rv_weather)
    RecyclerView mRv;

    @BindView(R.id.iv_day)
    ImageView mIvDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        Utils.stateTransparent(this);

        ButterKnife.bind(this);

        getWeatherData();

        Glide.with(this)
                .load(CacheUtils.getString(this, "pic"))
                .centerCrop()
                .crossFade()
                .bitmapTransform(new BlurTransformation(this, 8, 3))
                .error(R.mipmap.h)
                .into(mIvDay);

    }

    private void getWeatherData() {

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.WEATHER_BASE)
                .build();

        NewsService service = retrofit.create(NewsService.class);
        Call<Weather> call = service.getData("成都");

        call.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {

                List<Weather.ResultBean.FutureBean> result = response.body().getResult().get(0).getFuture();

                FullyLinearLayoutManager manager = new FullyLinearLayoutManager(WeatherActivity.this);
                mRv.setLayoutManager(manager);
                mRv.setAdapter(new WeatherAdapter(WeatherActivity.this, result));

            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {

            }
        });


    }


}
