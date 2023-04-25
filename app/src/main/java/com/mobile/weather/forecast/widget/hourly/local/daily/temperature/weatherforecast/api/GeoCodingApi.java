package com.mobile.weather.forecast.widget.hourly.local.daily.temperature.weatherforecast.api;


import com.mobile.weather.forecast.widget.hourly.local.daily.temperature.weatherforecast.model.GeoCodeModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeoCodingApi {

    @GET("geo/1.0/direct")
    Call<List<GeoCodeModel>> getCityByName(
            @Query("q") String q,
            @Query("limit") String limit,
            @Query("appid") String id);
}
