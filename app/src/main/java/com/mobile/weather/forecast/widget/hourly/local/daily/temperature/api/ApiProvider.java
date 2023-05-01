package com.mobile.weather.forecast.widget.hourly.local.daily.temperature.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiProvider {

    private static final String baseURL = "https://api.openweathermap.org/";
    private static Retrofit retrofit = null;

    public static GeoCodingApi provideGeoCodingApi() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(GeoCodingApi.class);
    }

    public static WeatherApi provideWeatherApi() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(WeatherApi.class);
    }

}
