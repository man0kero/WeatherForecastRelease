package com.mobile.weather.forecast.widget.hourly.local.daily.temperature.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.gms.ads.AdRequest;
import com.mobile.weather.forecast.widget.hourly.local.daily.temperature.weatherforecast.R;
import com.mobile.weather.forecast.widget.hourly.local.daily.temperature.adapter.HourlyWeatherAdapter;
import com.mobile.weather.forecast.widget.hourly.local.daily.temperature.ads.InterstitialAdImpl;
import com.mobile.weather.forecast.widget.hourly.local.daily.temperature.weatherforecast.databinding.ActivityHourlyWeatherBinding;
import com.mobile.weather.forecast.widget.hourly.local.daily.temperature.model.HourlyWeatherModel;

import java.util.List;

public class HourlyWeatherActivity extends AppCompatActivity {
    private AdRequest adRequest;
    private ActivityHourlyWeatherBinding binding;
    private HourlyWeatherAdapter adapter;
    private LinearLayoutManager layoutManager;
    private List<HourlyWeatherModel> result;
    private InterstitialAdImpl interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_hourly_weather);

        interstitialAd = new InterstitialAdImpl();
        interstitialAd.loadInterstitialAd(this);

        Intent intent = getIntent();
        result = (List<HourlyWeatherModel>) intent.getSerializableExtra("Hourly");

        adRequest = new AdRequest.Builder().build();
        binding.adView.loadAd(adRequest);

        adapter = new HourlyWeatherAdapter(this, result);
        layoutManager = new LinearLayoutManager(this);
        binding.hourlyRecyclerView.setAdapter(adapter);
        binding.hourlyRecyclerView.setLayoutManager(layoutManager);

        binding.hourlyInnerToolbar.setNavigationOnClickListener(view -> {
            onBackPressed();
        });
    }
}