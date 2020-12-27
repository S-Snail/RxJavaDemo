package com.snail.xx_annotion.EnjoyRetrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.snail.xx_annotion.EnjoyRetrofit.api.EnjoyWeatherApi;
import com.snail.xx_annotion.R;

import okhttp3.HttpUrl;

public class EnjoyRetrofitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enjoy_retrofit);

        EnjoyRetrofit enjoyRetrofit = new EnjoyRetrofit.Builder().baseUrl("https://restapi.amap.com").build();
        //通过动态代理，获取EnjoyRetrofit的实例
        EnjoyWeatherApi weatherApi = enjoyRetrofit.create(EnjoyWeatherApi.class);

    }
}