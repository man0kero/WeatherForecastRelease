package com.mobile.weather.forecast.widget.hourly.local.daily.temperature.view;

import static android.content.ContentValues.TAG;
import static com.mobile.weather.forecast.widget.hourly.local.daily.temperature.Util.HOUR_DOUBLE_DOT_MINUTE;
import static com.mobile.weather.forecast.widget.hourly.local.daily.temperature.Util.provideIcon;
import static com.mobile.weather.forecast.widget.hourly.local.daily.temperature.Util.toDateFormatOf;
import static com.mobile.weather.forecast.widget.hourly.local.daily.temperature.Util.toDegree;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.gms.ads.AdRequest;
import com.mobile.weather.forecast.widget.hourly.local.daily.temperature.R;
import com.mobile.weather.forecast.widget.hourly.local.daily.temperature.ads.AdOpen;
import com.mobile.weather.forecast.widget.hourly.local.daily.temperature.ads.InterstitialAdImpl;
import com.mobile.weather.forecast.widget.hourly.local.daily.temperature.databinding.ActivityMainBinding;
import com.mobile.weather.forecast.widget.hourly.local.daily.temperature.model.DailyWeatherModel;
import com.mobile.weather.forecast.widget.hourly.local.daily.temperature.model.HourlyWeatherModel;
import com.mobile.weather.forecast.widget.hourly.local.daily.temperature.model.WeatherDataModel;
import com.mobile.weather.forecast.widget.hourly.local.daily.temperature.viewModel.MainActivityViewModel;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences preferences;
    private boolean flag;
    private boolean isLoaded = false;

    private MainActivityViewModel mainActivityViewModel;
    private ActivityMainBinding binding;
    private WeatherDataModel resultWeatherDataModel;
    private List<HourlyWeatherModel> hourlyWeatherModels;
    private List<DailyWeatherModel> dailyWeatherModels;

    private InterstitialAdImpl interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainActivityViewModel = new ViewModelProvider
                .AndroidViewModelFactory(getApplication())
                .create(MainActivityViewModel.class);
        binding.swiper.setColorSchemeResources(R.color.black);
        binding.swiper.setProgressViewOffset(true, -50, 120);
        preferences = getSharedPreferences(this.getString(R.string.prefs_name), MODE_PRIVATE);
        flag = preferences.contains(this.getString(R.string.key_is_started_up));

        loadAds();
        checkIsStartedUp();
        startingOtherActivities();
        getWeatherData();
    }

    private void getWeatherData() {
        interstitialAd.showAds(this);

        if (preferences.contains("lat") && preferences.contains("lon") && flag) {
            String lat = preferences.getString("lat", "");
            String lon = preferences.getString("lon", "");

            mainActivityViewModel.getWeather(lat, lon).observe(this, new Observer<WeatherDataModel>() {
                @Override
                public void onChanged(WeatherDataModel weatherDataModel) {
                    resultWeatherDataModel = weatherDataModel;

                    if (resultWeatherDataModel != null) {
                        hourlyWeatherModels = resultWeatherDataModel.getHourly();
                        dailyWeatherModels = resultWeatherDataModel.getDaily();

                        binding.currentTemp.setText(
                                toDegree(resultWeatherDataModel.getCurrent().getTemp()));
                        binding.currentWeatherIcon.setImageResource(
                                provideIcon(resultWeatherDataModel.getCurrent().getWeather().get(0).getIcon()));
                        binding.updatedDt.setText(" " +
                                toDateFormatOf(resultWeatherDataModel.getCurrent().getDt(),
                                        HOUR_DOUBLE_DOT_MINUTE));

                        binding.mainTemp.setVisibility(View.VISIBLE);
                        binding.progressLayout.setVisibility(View.INVISIBLE);
                        binding.updatedLayout.setVisibility(View.VISIBLE);
                        isLoaded = true;
                    }
                }
            });
            binding.swiper.setRefreshing(false);
        }
    }

    private void startingOtherActivities() {
        binding.btnCurrentWeather.setOnClickListener(view -> {
            if (preferences.contains("lat") && preferences.contains("lon") && isLoaded) {
                Intent intent = new Intent(this, CurrentWeatherActivity.class);
                intent.putExtra("Current", resultWeatherDataModel);
                this.startActivity(intent);
            } else if (!isLoaded && preferences.contains("lat") && preferences.contains("lon")) {
                Toast.makeText(this, this.getString(R.string.onWait_toast), Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(this, FirstAdActivity.class);
                this.startActivity(intent);
            }
            interstitialAd.showAds(this);
        });

        binding.btnHourlyWeather.setOnClickListener(view -> {
            if (preferences.contains("lat") && preferences.contains("lon") && isLoaded) {
                Intent intent = new Intent(this, HourlyWeatherActivity.class);
                intent.putExtra("Hourly", (Serializable) hourlyWeatherModels);
                this.startActivity(intent);
            } else if (!isLoaded && preferences.contains("lat") && preferences.contains("lon")) {
                Toast.makeText(this, this.getString(R.string.onWait_toast), Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(this, FirstAdActivity.class);
                this.startActivity(intent);
            }
            interstitialAd.showAds(this);
        });

        binding.btnDailyWeather.setOnClickListener(view -> {
            if (preferences.contains("lat") && preferences.contains("lon") && isLoaded) {
                Intent intent = new Intent(this, DailyWeatherActivity.class);
                intent.putExtra("Daily", (Serializable) dailyWeatherModels);
                this.startActivity(intent);
            } else if (!isLoaded && preferences.contains("lat") && preferences.contains("lon")) {
                Toast.makeText(this, this.getString(R.string.onWait_toast), Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(this, FirstAdActivity.class);
                this.startActivity(intent);
            }
            interstitialAd.showAds(this);
        });

        binding.btnChangeCity.setOnClickListener(view -> {
            Intent intent = new Intent(this, CitySearchActivity.class);
            this.startActivity(intent);
            interstitialAd.showAds(this);
        });

        if (preferences.contains("lat") && preferences.contains("lon") && flag) {
            binding.swiper.setEnabled(true);
            binding.swiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    getWeatherData();
                    binding.mainTemp.setVisibility(View.INVISIBLE);
                    binding.progressLayout.setVisibility(View.VISIBLE);
                    binding.updatedLayout.setVisibility(View.INVISIBLE);
                }
            });
        }
    }

    private void loadAds() {
        Application application = getApplication();
        ((AdOpen) application).showAdIfAvailable(MainActivity.this,
                new AdOpen.OnShowAdCompleteListener() {
                    @Override
                    public void onShowAdComplete() {
                        Log.d(TAG, "onShowAdComplete: ");
                    }
                });

        AdRequest adRequest = new AdRequest.Builder().build();
        interstitialAd = new InterstitialAdImpl();
        interstitialAd.loadInterstitialAd(this);
        binding.adView.loadAd(adRequest);
    }

    private void checkIsStartedUp() {
        if (preferences.contains("lat") && preferences.contains("lon") && flag) {
            binding.hintText.setVisibility(View.INVISIBLE);
            binding.mainTemp.setVisibility(View.VISIBLE);
            binding.textCity.setText(preferences.getString("City_name", ""));
        } else {
            binding.progressLayout.setVisibility(View.INVISIBLE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(this.getString(R.string.key_is_started_up), true);
            editor.apply();
        }
    }
}