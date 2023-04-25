package com.mobile.weather.forecast.widget.hourly.local.daily.temperature.weatherforecast.view;


import static com.mobile.weather.forecast.widget.hourly.local.daily.temperature.weatherforecast.Util.provideIcon;
import static com.mobile.weather.forecast.widget.hourly.local.daily.temperature.weatherforecast.Util.toDegree;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.gms.ads.AdRequest;
import com.mobile.weather.forecast.widget.hourly.local.daily.temperature.weatherforecast.R;
import com.mobile.weather.forecast.widget.hourly.local.daily.temperature.weatherforecast.ads.InterstitialAdImpl;
import com.mobile.weather.forecast.widget.hourly.local.daily.temperature.weatherforecast.databinding.ActivityCurrentWeatherBinding;
import com.mobile.weather.forecast.widget.hourly.local.daily.temperature.weatherforecast.model.WeatherDataModel;

import java.util.ArrayList;

public class CurrentWeatherActivity extends AppCompatActivity {
    private AdRequest adRequest;
    private ActivityCurrentWeatherBinding binding;
    private WeatherDataModel weatherDataModel;
    private InterstitialAdImpl interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_current_weather);

        interstitialAd = new InterstitialAdImpl();
        interstitialAd.loadInterstitialAd(this);

        binding.currentInnerToolbar.setNavigationOnClickListener(view -> {
            interstitialAd.showAds(this);
            onBackPressed();
        });

        adRequest = new AdRequest.Builder().build();
        binding.adView.loadAd(adRequest);

        Intent intent = getIntent();
        weatherDataModel = (WeatherDataModel) intent.getSerializableExtra("Current");

        binding.currentTemp.setText(
                toDegree(weatherDataModel.getCurrent().getTemp()));
        binding.currentMinT.setText(
                toDegree(weatherDataModel.getDaily().get(0).getTemp().getMin()));
        binding.currentFeelsT.setText(
                toDegree(weatherDataModel.getCurrent().getFeels_like()));
        binding.currentMaxT.setText(
                toDegree(weatherDataModel.getDaily().get(0).getTemp().getMax()));
        binding.currentWeatherIcon.setImageResource(
                provideIcon(weatherDataModel.getCurrent().getWeather().get(0).getIcon()));
        String info = weatherDataModel.getCurrent().getWeather().get(0).getDescription()
                .substring(0, 1).toUpperCase() +
                weatherDataModel.getCurrent().getWeather().get(0).getDescription()
                        .substring(1);

        binding.currentWeatherInfo.setText(info);
    }

    @Override
    public void onBackPressed() {
        interstitialAd.showAds(this);
        super.onBackPressed();
    }
}