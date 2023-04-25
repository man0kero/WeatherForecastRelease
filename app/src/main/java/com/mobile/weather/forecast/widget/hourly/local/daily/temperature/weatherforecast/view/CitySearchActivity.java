package com.mobile.weather.forecast.widget.hourly.local.daily.temperature.weatherforecast.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.gms.ads.AdRequest;
import com.mobile.weather.forecast.widget.hourly.local.daily.temperature.weatherforecast.R;
import com.mobile.weather.forecast.widget.hourly.local.daily.temperature.weatherforecast.adapter.CityListAdapter;
import com.mobile.weather.forecast.widget.hourly.local.daily.temperature.weatherforecast.ads.InterstitialAdImpl;
import com.mobile.weather.forecast.widget.hourly.local.daily.temperature.weatherforecast.databinding.ActivityCitySearchBinding;
import com.mobile.weather.forecast.widget.hourly.local.daily.temperature.weatherforecast.model.GeoCodeModel;
import com.mobile.weather.forecast.widget.hourly.local.daily.temperature.weatherforecast.viewModel.CitySearchActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class CitySearchActivity extends AppCompatActivity {
    private AdRequest adRequest;
    private ActivityCitySearchBinding binding;
    private CityListAdapter adapter;
    private LinearLayoutManager layoutManager;
    private CitySearchActivityViewModel citySearchActivityViewModel;
    private ArrayList<GeoCodeModel> results;
    private InputMethodManager keyboardManager;
    private InterstitialAdImpl interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_city_search);
        adRequest = new AdRequest.Builder().build();
        binding.adView.loadAd(adRequest);
        citySearchActivityViewModel = new ViewModelProvider
                .AndroidViewModelFactory(getApplication())
                .create(CitySearchActivityViewModel.class);

        interstitialAd = new InterstitialAdImpl();
        interstitialAd.loadInterstitialAd(this);


        binding.cityInnerToolbar.setNavigationOnClickListener(view -> {
            interstitialAd.showAds(this);
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);
        });

        binding.btnCitySearch.setOnClickListener(view -> {
            citySearch();
        });

        binding.citySearchField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    citySearch();
                    return true;
                }
                return false;
            }
        });

    }

    private void citySearch() {
        keyboardManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        keyboardManager.hideSoftInputFromWindow(binding.btnCitySearch.getWindowToken(), 0);
        String cityName = binding.citySearchField.getText().toString().trim();
        if (!cityName.isEmpty()) {
            getCities(cityName);
        }
    }

    private void getCities(String city) {
        citySearchActivityViewModel.getAllCities(city).observe(this, new Observer<List<GeoCodeModel>>() {
            @Override
            public void onChanged(List<GeoCodeModel> resultsList) {
                results = (ArrayList<GeoCodeModel>) resultsList;
                fillRecyclerView();
            }
        });
    }

    private void fillRecyclerView() {
        adapter = new CityListAdapter(this, results);
        layoutManager = new LinearLayoutManager(this);
        binding.cityResultRecyclerView.setAdapter(adapter);
        binding.cityResultRecyclerView.setLayoutManager(layoutManager);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        interstitialAd.showAds(this);
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
    }
}