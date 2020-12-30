package com.snail.xx_annotion.EnjoyRetrofit.api;

import com.snail.xx_annotion.EnjoyRetrofit.annotation.Field;
import com.snail.xx_annotion.EnjoyRetrofit.annotation.GET;
import com.snail.xx_annotion.EnjoyRetrofit.annotation.POST;
import com.snail.xx_annotion.EnjoyRetrofit.annotation.Query;

import okhttp3.Call;

public interface EnjoyWeatherApi {

    @POST("/v3/weather/weatherInfo")
    Call postWeather(@Field("city") String city, @Field("key") String key);

    @GET("/v3/weather/weatherInfo")
    Call getWeather(@Query("city") String city, @Query("key") String key);

    //https://restapi.amap.com/v3/weather/weatherInfo?city=110101&key=ae6c53e2186f33bbf240a12d80672d1b
}
