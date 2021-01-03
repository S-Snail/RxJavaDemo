package com.snail.cusretrofit.api;


import com.snail.cusretrofit.annotation.Field;
import com.snail.cusretrofit.annotation.GET;
import com.snail.cusretrofit.annotation.POST;
import com.snail.cusretrofit.annotation.Query;

import okhttp3.Call;

public interface IWeatherApi {

    String path = "/v3/weather/weatherInfo";

    @POST("/v3/weather/weatherInfo")
    Call postWeather(@Field("city") String city, @Field("key") String key);

    @GET("/v3/weather/weatherInfo")
    Call getWeather(@Query("city") String city, @Query("key") String key);

}
