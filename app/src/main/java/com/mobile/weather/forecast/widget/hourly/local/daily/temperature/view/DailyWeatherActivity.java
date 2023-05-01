package com.mobile.weather.forecast.widget.hourly.local.daily.temperature.view;


import static com.mobile.weather.forecast.widget.hourly.local.daily.temperature.Util.DAY_FULL_MONTH_NAME;
import static com.mobile.weather.forecast.widget.hourly.local.daily.temperature.Util.HOUR_DOUBLE_DOT_MINUTE;
import static com.mobile.weather.forecast.widget.hourly.local.daily.temperature.Util.provideIcon;
import static com.mobile.weather.forecast.widget.hourly.local.daily.temperature.Util.toDateFormatOf;
import static com.mobile.weather.forecast.widget.hourly.local.daily.temperature.Util.toDegree;
import static com.mobile.weather.forecast.widget.hourly.local.daily.temperature.Util.toPercentStringWO;
import static com.mobile.weather.forecast.widget.hourly.local.daily.temperature.Util.toPressure;
import static com.mobile.weather.forecast.widget.hourly.local.daily.temperature.Util.toWindSpeed;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.gms.ads.AdRequest;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.mobile.weather.forecast.widget.hourly.local.daily.temperature.R;
import com.mobile.weather.forecast.widget.hourly.local.daily.temperature.databinding.ActivityDailyWeatherBinding;
import com.mobile.weather.forecast.widget.hourly.local.daily.temperature.adapter.DailyWeatherAdapter;
import com.mobile.weather.forecast.widget.hourly.local.daily.temperature.ads.InterstitialAdImpl;
import com.mobile.weather.forecast.widget.hourly.local.daily.temperature.model.DailyWeatherModel;

import java.util.List;

public class DailyWeatherActivity extends AppCompatActivity {
    private AdRequest adRequest;
    private ActivityDailyWeatherBinding binding;
    private DailyWeatherAdapter adapter;
    private LinearLayoutManager layoutManager;
    private List<DailyWeatherModel> dailyWeatherModels;
    private BottomSheetBehavior bottomSheetBehavior;
    private InterstitialAdImpl interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_daily_weather);

        interstitialAd = new InterstitialAdImpl();
        interstitialAd.loadInterstitialAd(this);

        bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheet);
        adRequest = new AdRequest.Builder().build();
        binding.adView.loadAd(adRequest);
        Intent intent = getIntent();
        dailyWeatherModels = (List<DailyWeatherModel>) intent.getSerializableExtra("Daily");

        adapter = new DailyWeatherAdapter(this, dailyWeatherModels);
        layoutManager = new LinearLayoutManager(this);
        binding.dailyRecyclerView.setAdapter(adapter);
        binding.dailyRecyclerView.setLayoutManager(layoutManager);

        binding.dailyInnerToolbar.setNavigationOnClickListener(view -> {
            onBackPressed();
        });

    }

    public void fillDayInfo(int position) {
        binding.hintText.setVisibility(View.INVISIBLE);
        binding.bottomSheet.setVisibility(View.VISIBLE);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

        binding.dayInfo.dayDate.setText(
                toDateFormatOf(dailyWeatherModels.get(position).getDt(), DAY_FULL_MONTH_NAME));
        binding.dayInfo.dayTemp.setText(
                toDegree(dailyWeatherModels.get(position).getTemp().getAverage()));
        binding.dayInfo.dayIcon.setImageResource(
                provideIcon(dailyWeatherModels.get(position).getWeather().get(0).getIcon()));

        binding.dayInfo.dayMornTemp.setText(
                toDegree(dailyWeatherModels.get(position).getTemp().getMorn()));
        binding.dayInfo.dayMornFl.setText(
                toDegree(dailyWeatherModels.get(position).getFeels_like().getMorn()));

        binding.dayInfo.dayDailyTemp.setText(
                toDegree(dailyWeatherModels.get(position).getTemp().getDay()));
        binding.dayInfo.dayDailyFl.setText(
                toDegree(dailyWeatherModels.get(position).getFeels_like().getDay()));

        binding.dayInfo.dayEveTemp.setText(
                toDegree(dailyWeatherModels.get(position).getTemp().getEve()));
        binding.dayInfo.dayEveFl.setText(
                toDegree(dailyWeatherModels.get(position).getFeels_like().getEve()));

        binding.dayInfo.dayNightTemp.setText(
                toDegree(dailyWeatherModels.get(position).getTemp().getNight()));
        binding.dayInfo.dayNightFl.setText(
                toDegree(dailyWeatherModels.get(position).getFeels_like().getNight()));

        binding.dayInfo.dayHumidity.setText(
                toPercentStringWO(dailyWeatherModels.get(position).getHumidity()));

        binding.dayInfo.dayPressure.setText(
                toPressure(dailyWeatherModels.get(position).getPressure(), this)
        );

        binding.dayInfo.dayWindSpeed.setText(
                toWindSpeed(dailyWeatherModels.get(position).getWind_speed(), this)
        );

        binding.dayInfo.daySunrise.setText(
                toDateFormatOf(dailyWeatherModels.get(position).getSunrise(), HOUR_DOUBLE_DOT_MINUTE));
        binding.dayInfo.daySunset.setText(
                toDateFormatOf(dailyWeatherModels.get(position).getSunset(), HOUR_DOUBLE_DOT_MINUTE));

    }
}