package com.mobile.weather.forecast.widget.hourly.local.daily.temperature.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.gms.ads.AdRequest;
import com.mobile.weather.forecast.widget.hourly.local.daily.temperature.R;
import com.mobile.weather.forecast.widget.hourly.local.daily.temperature.databinding.ActivityFirstAdBinding;
import com.mobile.weather.forecast.widget.hourly.local.daily.temperature.ads.InterstitialAdImpl;

public class FirstAdActivity extends AppCompatActivity {
    private AdRequest adRequest;
    private ActivityFirstAdBinding binding;
    private InterstitialAdImpl interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_first_ad);

        interstitialAd = new InterstitialAdImpl();
        interstitialAd.loadInterstitialAd(this);
        adRequest = new AdRequest.Builder().build();
        binding.firstAdView.loadAd(adRequest);

        binding.btnChangeCity.setOnClickListener(view -> {
            Intent intent = new Intent(this, CitySearchActivity.class);
            this.startActivity(intent);
            interstitialAd.showAds(this);
        });

        binding.firstInnerToolbar.setNavigationOnClickListener(view -> {
            onBackPressed();
        });
    }
}