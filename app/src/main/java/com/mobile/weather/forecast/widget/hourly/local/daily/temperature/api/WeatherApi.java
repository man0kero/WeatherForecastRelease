package com.mobile.weather.forecast.widget.hourly.local.daily.temperature.api;


import com.mobile.weather.forecast.widget.hourly.local.daily.temperature.model.WeatherDataModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {
    @GET("data/3.0/onecall?")
    Call<WeatherDataModel> getWeatherForecast(
            @Query("lat") String lat,
            @Query("lon") String lon,
            @Query("exclude") String exclude,
            @Query("appid") String apiid,
            @Query("lang") String lang);
}
